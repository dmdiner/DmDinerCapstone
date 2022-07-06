package com.ZooFood.Diner_David_ZooFood_CaseStudy.Global;

import com.ZooFood.Diner_David_ZooFood_CaseStudy.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Product> cart;
    static{
        cart = new ArrayList<Product>();
    }
}