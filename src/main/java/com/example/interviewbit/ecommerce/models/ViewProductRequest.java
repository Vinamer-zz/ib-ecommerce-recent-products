package com.example.interviewbit.ecommerce.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public class ViewProductRequest {

	private String name;
	private String mobile;
	private List<String> productIds;
}
