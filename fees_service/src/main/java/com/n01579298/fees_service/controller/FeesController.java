package com.n01579298.fees_service.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.n01579298.fees_service.model.Fees;
import com.n01579298.fees_service.model.OrderDTO;
import com.n01579298.fees_service.service.FeesService;

@RestController
@RequestMapping("/fees")
public class FeesController {

	@Autowired
	private FeesService feesService;

	private final ObjectMapper objectMapper;

	public FeesController(FeesService feesService) {
		this.feesService = feesService;
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
	}

	@PostMapping("/calculate")
	public ResponseEntity<Fees> calculateFee(@RequestBody OrderDTO order) {
		Fees fees = new Fees();
		fees.setFeeAmount(order.getTotalAmount() * 0.02);
		fees.setFeeSalesTax(fees.getFeeAmount() * 0.05);
		fees.setFeeDate(LocalDateTime.now());
		fees.setFeeType(order.getOrderType().equalsIgnoreCase("SELL"));
		
		Fees f1 =  feesService.createFees(fees);
		
		try {
			objectMapper.writeValue(new File("target/fees.json"), f1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok(f1);
	}

	@GetMapping
	public ResponseEntity<Fees> showFees() {
		try {
			return ResponseEntity.ok(objectMapper.readValue(new File("target/fees.json"), Fees.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}