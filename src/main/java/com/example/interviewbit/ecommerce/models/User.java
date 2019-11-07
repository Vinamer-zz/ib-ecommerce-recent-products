package com.example.interviewbit.ecommerce.models;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Document(collection = "users")
@JsonIgnoreProperties
public class User {

	@Id
	private String userId;
	
	@NotNull
	private String name;
	
	@Pattern(regexp = "^[0-9]{10}$")
	private String mobile;
	private LinkedList<Product> recentProducts = new LinkedList<>();
	private LocalDateTime createdOn;
	
	public User() {}
	
	public User(String name, String mobile) {
		this.name = name;
		this.mobile = mobile;
	}
	
	/*
	 * public String getUserId() { return userId; } public void setUserId(String
	 * userId) { this.userId = userId; } public String getName() { return name; }
	 * public void setName(String name) { this.name = name; } public List<Product>
	 * getRecentProducts() { return recentProducts; } public void
	 * setRecentProducts(List<Product> recentProducts) { this.recentProducts =
	 * recentProducts; }
	 */
	
}
