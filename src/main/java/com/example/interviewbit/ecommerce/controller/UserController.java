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
import org.springframework.web.server.ResponseStatusException;

import com.example.interviewbit.ecommerce.models.Product;
import com.example.interviewbit.ecommerce.models.User;
import com.example.interviewbit.ecommerce.models.ViewProductRequest;
import com.example.interviewbit.ecommerce.repo.ProductRepo;
import com.example.interviewbit.ecommerce.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	//private static Logger logger = Log

	@Autowired
	private UserRepo userRepo;
	
	@Autowired ProductRepo productRepo;
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
		
		if (userId != null && !userId.equals("")) {
			User user = userRepo.findByUserId(userId);
			if (user != null) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{name}/{mobile}")
	public ResponseEntity<User> getUserByNameAndMobile(@PathVariable("name") String name, @PathVariable("mobile") String mobile) {
		
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(mobile)) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		User user = userRepo.findByNameAndMobile(name, mobile);
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user, BindingResult bindingResult) {
		
		log.info("Found user name: '" + user.getName() + "'");
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		
		User existingUser = userRepo.findByNameAndMobile(user.getName(), user.getMobile());
		
		if (existingUser != null) {
			log.error("User already exists for given credentials.");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Already Exists.");
		}
		
		user.setCreatedOn(LocalDateTime.now());
		User persistedUser = userRepo.save(user);
		return new ResponseEntity<User>(persistedUser, HttpStatus.CREATED);
	}
	
	@PostMapping("/view")
	public ResponseEntity<HttpStatus> viewProducts(@RequestBody ViewProductRequest request) {
		
		User user = userRepo.findByNameAndMobile(request.getName(), request.getMobile());
		
		if (user == null) {
			User newUser = new User(request.getName(), request.getMobile());
			user = userRepo.save(newUser);
		}
		
		if (request.getProductIds() != null) {
			for (String productId : request.getProductIds()) {
				Product product = productRepo.findByProductId(productId);
				if (product != null) {
					user.getRecentProducts().remove(product);
					user.getRecentProducts().addFirst(product);
				}
			}
		}
		
		userRepo.save(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
