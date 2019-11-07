package com.example.interviewbit.ecommerce.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Product {
	
	@Id
	private String productId;
	private String name;
	private String url;
	private LocalDateTime createdOn;
	
	
	@Override
	public int hashCode() {
		return this.productId.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		Product p = (Product) o;
		
		if (this.productId.equals(p.productId)) {
			return true;
		} else {
			return false;
		}
	}

}
