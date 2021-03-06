package com.ZooFood.Diner_David_ZooFood_CaseStudy.controller;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.dto.ProductDTO;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.Category;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.Product;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.CategoryService;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    //Redirect to admin login page when URL is followed by /admin
    @GetMapping("/admin")
    public String adminLogin(){
        return "adminLogin";
    }

    //Goes to categories modification page when logged in as admin
    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }

    //Goes to the add categories page when logged in as admin
    @GetMapping("/admin/categories/add")
    public String getAddCategories(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    //Redirects to categories modification page when logged in as admin
    @PostMapping("/admin/categories/add")
    public String postAddCategory(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    //Deletes a category when logged in as admin
    @GetMapping("/admin/categories/delete/{id}")
    public String getDeleteCategory(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    //Goes to the categories add page to modify/update a category when logged in as admin
    @GetMapping("admin/categories/update/{id}")
    public String getUpdateCategory(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        } else{
            return "404";
        }
    }

    /**---------------------------Product Section----------------------------------------------**/


    //Goes to the product modification page when logged in as admin
    @GetMapping("/admin/products")
    public String getProduct(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "products";
    }

    //Goes to the product add page when logged in as admin
    @GetMapping("/admin/products/add")
    public String getAddProduct(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }

    //Redirects and updates the product page when the admin adds a product
    @PostMapping("/admin/products/add")
    public String postAddProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName)  throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());

        } else {
            imageUUID = imgName;
        }

        product.setImageName(imageUUID);
        productService.addProduct(product);
        return "redirect:/admin/products";
    }

    //Goes to the product delete page when logged in as admin
    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    //Goes to the product update page when logged in as admin
    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProduct(@PathVariable long id, Model model){
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setWeight(product.getWeight());
        productDTO.setDescription(product.getDescription());
        productDTO.setImageName(product.getImageName());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }
}
