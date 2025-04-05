package com.n01579298.market_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.n01579298.market_service.model.Market;

@Repository
public interface MarketRepository extends MongoRepository<Market, String>{

}