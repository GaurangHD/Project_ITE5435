package com.n01579298.fees_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n01579298.fees_service.model.Fees;
import com.n01579298.fees_service.repository.FeesRepository;

@Service
public class FeesService {
	
	@Autowired
	private FeesRepository feesRepository;
	
	public Fees createFees(Fees fees) {
		return feesRepository.save(fees);
	}
}