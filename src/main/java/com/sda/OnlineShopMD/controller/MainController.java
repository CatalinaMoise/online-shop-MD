package com.sda.OnlineShopMD.controller;

import com.sda.OnlineShopMD.dto.*;
import com.sda.OnlineShopMD.dto.UserAccountDTO;
import com.sda.OnlineShopMD.service.CartService;
import com.sda.OnlineShopMD.service.OrderService;
import com.sda.OnlineShopMD.service.UserAccountService;
import com.sda.OnlineShopMD.validator.UserAccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.sda.OnlineShopMD.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
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
    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/addProduct")
    public String addProductGet(Model model) {
        //aici implementam fancy business logic
        //pe scurt prasim

        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);

        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductPost(@ModelAttribute ProductDto productDto, @RequestParam("productImg") MultipartFile multipartFile) {
        System.out.println(productDto);
        log.info("hello!");
        productService.create(productDto, multipartFile);
        return "redirect:/addProduct";
    }

    @GetMapping("/homePage")
    public String homeGet(Model model) {

        List<ProductDto> productDtoList = productService.getAllProductDtoList();
        model.addAttribute("productDtoList", productDtoList);
        System.out.println(productDtoList);
        return "homePage";
    }

    @GetMapping("/product/{productId}")
    //am adaugat id, pentru a avea un url cu id pentru fiecare produs, ca in productdto si productmapper
    public String viewProductGet(Model model, @PathVariable(value = "productId") String productId) {
        System.out.println("am dat click pe produsul cu id-ul" + productId);
        Optional<ProductDto> optionalProductDto = productService.getProductDtoById(productId);
        if (optionalProductDto.isEmpty()) {
            return "error";
        }
        model.addAttribute("productDto", optionalProductDto.get());
        ProductQuantityDto productQuantityDto = new ProductQuantityDto();
        model.addAttribute("productQuantityDto", productQuantityDto);
        return "viewProduct";
    }

    @PostMapping("/product/{productId}")
    public String addToCartPost(@ModelAttribute ProductQuantityDto productQuantityDto,
                                @PathVariable(value = "productId") String productId, Authentication authentication) { //authentification - sa stim cine a adaugat in cart
        System.out.println(productQuantityDto);
        System.out.println("productId este " + productId);
        System.out.println(authentication.getName());
        cartService.addToCart(productId, productQuantityDto, authentication.getName());

        return "redirect:/product/" + productId;
    }

    @GetMapping("/register")
    public String registerGet(Model model) {
        UserAccountDTO userAccountDTO = new UserAccountDTO();
        model.addAttribute("userAccountDTO", userAccountDTO);

        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute UserAccountDTO userAccountDTO, BindingResult bindingResult) {
        System.out.println(userAccountDTO);
        userAccountValidator.validate(userAccountDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "register";
        }
        userAccountService.addUserAccount(userAccountDTO);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginGet(Model model) {
        return "login";
    }

    @GetMapping("/checkout")
    public String checkoutGet(Model model, Authentication authentication) {
//        CheckoutDto checkoutDto = CheckoutDto.builder()
//                .total("455")
//                .shippingFee("9")
//                .subtotal("446")
//                .productCheckoutDtoList(Arrays.asList(
//                        ProductCheckoutDto.builder()
//                                .name("prune")
//                                .quantity("23")
//                                .price("3")
//                                .total("69")
//                                .build(),
//                        ProductCheckoutDto.builder()
//                                .name("mere")
//                                .quantity("30")
//                                .price("2")
//                                .total("60")
//                                .build()
//                )).build();
        CheckoutDto checkoutDto = cartService.getCheckoutDtoByUserEmail(authentication.getName());
        model.addAttribute("checkoutDto", checkoutDto);
        return "checkout";
    }

    @GetMapping("/confirmation")
    public String confirmationGet() {
        return "error";
    }

    @PostMapping("/confirmation")
    public String confirmationPost(Model model, Authentication authentication) {
        orderService.placeOrder(authentication.getName());
        CheckoutDto checkoutDto = cartService.getCheckoutDtoByUserEmail(authentication.getName());
        model.addAttribute("checkoutDto", checkoutDto);
        return "confirmation";
    }
}








