package com.example.spring.web;

import com.example.spring.model.binding.ProductAddBindingModel;
import com.example.spring.model.entity.Product;
import com.example.spring.repository.ProductRepository;
import com.example.spring.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("products")
public class ProductsController {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    public ProductsController(ProductRepository productRepository, ModelMapper modelMapper, ProductService productService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @GetMapping("/add")
    public String productAdd(Model model) {
        if(!model.containsAttribute("productAddBindingModel")){
            model.addAttribute("productAddBindingModel", new ProductAddBindingModel());
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("productAddBindingModel")
                             ProductAddBindingModel productAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors() ||
        this.productRepository.findByName(productAddBindingModel.getName()).isPresent()){
            redirectAttributes.addFlashAttribute("productAddBindingModel", productAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindingModel", bindingResult);
            return "redirect:add";
        }

        Product product = this.modelMapper
                .map(productAddBindingModel, Product.class);
        this.productService
                .addProduct(product);

        return "redirect:/";

    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") String id){

        this.productService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/buyAll")
    public String buyAll(){
        this.productRepository
                .deleteAll();
        return "redirect:/";
    }

}
