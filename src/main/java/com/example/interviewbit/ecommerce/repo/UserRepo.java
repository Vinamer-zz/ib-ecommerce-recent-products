package com.example.interviewbit.ecommerce.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.interviewbit.ecommerce.models.User;


@Repository
public interface UserRepo extends MongoRepository<User, String> {
	
	User findByUserId(String userId);
	User findByNameAndMobile(String name, String mobile);
}
