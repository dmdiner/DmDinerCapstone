package com.ZooFood.Diner_David_ZooFood_CaseStudy.controller;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.Global.GlobalData;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.CategoryService;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    //Redirects to the home page when requested
    @GetMapping({"/", "/home", "/index"})
    public String home(Model model){
        return "index";
    }

    //Redirects to the shop page
    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("cartCount", GlobalData.cart.size());

        return "shop";
    }

    //Redirects to shop page for specific categories
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        model.addAttribute("cartCount", GlobalData.cart.size());

        return "shop";
    }

    //redirects to product page to view specific product chosen
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable int id){
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
    }


}
