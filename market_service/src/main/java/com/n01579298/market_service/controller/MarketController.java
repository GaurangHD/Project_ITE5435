package com.n01579298.market_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.n01579298.market_service.repository.MarketRepository;

@RestController
public class MarketController {

	@Autowired
	private MarketRepository marketRepository;

	public MarketController(MarketRepository marketRepository) {
		this.marketRepository = marketRepository;
	}

}