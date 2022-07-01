package com.ZooFood.Diner_David_ZooFood_CaseStudy.repository;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory_Id(int id);

}
