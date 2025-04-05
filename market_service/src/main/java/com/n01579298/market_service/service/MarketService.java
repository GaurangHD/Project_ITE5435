package com.n01579298.market_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n01579298.market_service.repository.MarketRepository;

@Service
public class MarketService {
	
	@Autowired
	private MarketRepository marketRepository;

}