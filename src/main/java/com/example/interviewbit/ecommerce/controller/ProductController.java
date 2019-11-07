package com.example.interviewbit.ecommerce.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.interviewbit.ecommerce.models.Product;
import com.example.interviewbit.ecommerce.repo.ProductRepo;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductRepo productRepo;
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductbyId(@PathVariable("productId") String productId) {
		
		if (StringUtils.isEmpty(productId)) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		
		Product product = productRepo.findByProductId(productId);
		if (product == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		
		product.setCreatedOn(LocalDateTime.now());
		Product persistedProduct = productRepo.save(product);
		return new ResponseEntity<Product>(persistedProduct, HttpStatus.CREATED);
	}
}
