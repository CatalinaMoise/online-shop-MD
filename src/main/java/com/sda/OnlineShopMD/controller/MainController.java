package com.sda.OnlineShopMD.controller;

import com.sda.OnlineShopMD.dto.ProductDto;
import com.sda.OnlineShopMD.dto.UserAccountDTO;
import com.sda.OnlineShopMD.dto.UserAccountDTO;
import com.sda.OnlineShopMD.service.UserAccountService;
import com.sda.OnlineShopMD.validator.UserAccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.sda.OnlineShopMD.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


@Controller // ca sa adaugam controller in Spring
@Slf4j
public class MainController {
@Autowired
private ProductService productService;
@Autowired
private UserAccountService userAccountService;

@Autowired
private UserAccountValidator userAccountValidator;

    @GetMapping("/addProduct")
    public String addProductGet(Model model) {
        //aici implementam fancy business logic
        //pe scurt prasim

        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);

        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProductPost(@ModelAttribute ProductDto productDto, @RequestParam("productImg") MultipartFile multipartFile){
        System.out.println(productDto);
        log.info("hello!");
        productService.create(productDto, multipartFile);
        return "redirect:/addProduct";
    }

    @GetMapping("/homePage")
    public String homeGet(Model model){

       List<ProductDto> productDtoList = productService.getAllProductDtoList();
        model.addAttribute("productDtoList", productDtoList);
           System.out.println(productDtoList);
        return "homePage";
    }
    @GetMapping("/product/{productId}")  //am adaugat id, pentru a avea un url cu id pentru fiecare produs, ca in productdto si productmapper
    public String viewProductGet(Model model, @PathVariable(value="productId") String productId){
        System.out.println("am dat click pe produsul cu id-ul"+ productId);
        Optional<ProductDto> optionalProductDto = productService.getProductDtoById(productId);
        if(optionalProductDto.isEmpty()){
            return "error";
        }
        model.addAttribute("productDto", optionalProductDto.get());
        return "viewProduct";
    }
    @GetMapping  ("/register")
    public String registerGet(Model model){
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        model.addAttribute("userAccountDTO", userAccountDTO);

        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute UserAccountDTO userAccountDTO, BindingResult bindingResult){
        System.out.println(userAccountDTO);
        userAccountValidator.validate(userAccountDTO, bindingResult);
        if(bindingResult.hasErrors()){
            return "register";
        }
        userAccountService.addUserAccount(userAccountDTO);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginGet(Model model){
        return "login";
    }
}
