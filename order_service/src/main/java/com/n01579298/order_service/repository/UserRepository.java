package com.n01579298.order_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.n01579298.order_service.model.User;
import java.util.List;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
	User findByUserName(String userName);
}