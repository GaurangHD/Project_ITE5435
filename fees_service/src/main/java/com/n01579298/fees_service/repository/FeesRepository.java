package com.n01579298.fees_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.n01579298.fees_service.model.Fees;

@Repository
public interface FeesRepository extends MongoRepository<Fees, String> {

}