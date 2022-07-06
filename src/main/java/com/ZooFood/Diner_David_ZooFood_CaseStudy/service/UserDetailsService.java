package com.ZooFood.Diner_David_ZooFood_CaseStudy.service;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.UserDetails;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.User;
import com.ZooFood.Diner_David_ZooFood_CaseStudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.map(UserDetails::new).get();
    }
}
