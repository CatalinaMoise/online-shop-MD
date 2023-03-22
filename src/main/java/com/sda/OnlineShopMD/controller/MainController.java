package com.sda.OnlineShopMD.controller;

import com.sda.OnlineShopMD.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.sda.OnlineShopMD.service.ProductService;


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

    @GetMapping("/homePage") public String homeGet(){
        return "homePage";
    }
}
