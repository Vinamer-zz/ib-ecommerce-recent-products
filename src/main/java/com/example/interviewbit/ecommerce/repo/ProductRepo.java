package com.example.interviewbit.ecommerce.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.interviewbit.ecommerce.models.Product;

public interface ProductRepo extends MongoRepository<Product, String>{

	Product findByProductId(String productId);
}