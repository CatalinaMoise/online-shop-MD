package com.sda.OnlineShopMD.controller;

import com.sda.OnlineShopMD.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.sda.OnlineShopMD.service.ProductService;

import java.util.List;


@Controller // ca sa adaugam controller in Spring
@Slf4j
public class MainController {
@Autowired
private ProductService productService;
    @GetMapping("/addProduct")
    public String addProductGet(Model model) {
        //aici implementam fancy business logic
        //pe scurt prasim

        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);

        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProductPost(@ModelAttribute ProductDto productDto){
        System.out.println(productDto);
        log.info("hello!");
        productService.create(productDto);
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
        return "viewProduct";
    }

}
