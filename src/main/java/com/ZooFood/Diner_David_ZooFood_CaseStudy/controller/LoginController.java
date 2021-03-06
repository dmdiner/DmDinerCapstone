package com.ZooFood.Diner_David_ZooFood_CaseStudy.controller;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.Global.Data;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.Role;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.User;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.repository.RoleRepository;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(){
        Data.cart.clear();
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    //Creates a user on the register page and ads it to the user repository
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2).get());
        user.setRoles(roles);
        userRepository.save(user);
        request.login(user.getEmail(), password);
        return "redirect:/";

    }

}
