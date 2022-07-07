package com.ZooFood.Diner_David_ZooFood_CaseStudy;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.Global.Data;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.configuration.GoogleOAuth2SuccessHandler;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.dto.ProductDTO;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.*;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.repository.RoleRepository;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.repository.UserRepository;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.CategoryService;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.ProductService;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.service.UserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DinerDavidZooFoodCaseStudyApplicationTests {

	private CategoryService categoryService;
	private ProductService productService;
	private UserDetailsService userDetailsService;
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	private BCryptPasswordEncoder bCryptPasswordEncoder;



	@Test
	void contextLoads() {
	}


	@Test
	void passwordEncoder() {
		ProductDTO productDTO = new ProductDTO();
		Product product = new Product();
		Category category = new Category();

		categoryService.addCategory(category);
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());



		Data.cart.add(productService.getProductById(43).get());


//
//		String password = "password";
//		//user.setPassword("$2a$12$1cmDMP35o5POIUNt36q2r.ZkVlisnosJTkapSv29WL7erGNUbY5S6");
//		request.login("tjones@gmail.com", "password");
//
//
//
//		//List<Role> roles = roleRepository.findAll();
//		List<User> users = userRepository.findAll();
//		System.out.println(users);
//		String password = "password";
//		String encodedPassword = bCryptPasswordEncoder.encode(password);
//		assertEquals(encodedPassword, "$2a$12$1cmDMP35o5POIUNt36q2r.ZkVlisnosJTkapSv29WL7erGNUbY5S6");
	}

	/*@Test
	public void addToCartTest(@ModelAttribute("category") Category category, Model model, @ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
//			user.setPassword("password");
//			List<Role> roles = new ArrayList<>();
//			roles.add(roleRepository.findById(2).get());
//			user.setRoles(roles);
//			userRepository.save(user);
//			request.login("admin@gmail.com", "password");

//
//		model.addAttribute("categories", categoryService.getAllCategory());
//		model.addAttribute("category", new Category());
//		categoryService.addCategory(category);
//		Data.cart.add(productService.getProductById(43).get());
//		Data.cart.size();
//		assertEquals(1, Data.cart.size());


	}
*/


}
