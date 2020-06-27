package com.example.spring.web;

import com.example.spring.service.CategoryService;
import com.example.spring.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public HomeController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @GetMapping("/")
    public ModelAndView index(HttpSession httpSession, ModelAndView modelAndView){
        if(httpSession.getAttribute("user") == null){
            modelAndView.setViewName("index");
        }else{
            modelAndView.addObject("categories", this.categoryService.findAllCategories());
            modelAndView.addObject("totalPrice", this.productService.totalPrice());
            modelAndView.setViewName("home");
        }
        return modelAndView;
    }
}
