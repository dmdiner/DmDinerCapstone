package com.ZooFood.Diner_David_ZooFood_CaseStudy.controller;


import com.ZooFood.Diner_David_ZooFood_CaseStudy.Global.GlobalData;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.Product;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    //Redirects to the shop after adding an item to cart
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    //Reedirects to cart when the button is pressed to view
    @GetMapping("/cart")
    public String getCart(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    //Redirects to cart when an item is removed from the cart
    @GetMapping("/cart/removeItem/{index}")
    public String RemoveFromCart(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    }

    //Redirects to checkout page when checkout is clicked
    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
    }

    //Redirects to the post-shopping page after you check out
    @PostMapping("/orderSent")
    public String orderSent(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        GlobalData.cart.clear();

        return "orderSent";
    }
}