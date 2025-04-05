package com.n01579298.order_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.n01579298.order_service.model.AccountTransactions;
import com.n01579298.order_service.model.Fees;
import com.n01579298.order_service.model.Order;
import com.n01579298.order_service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	private RestTemplate restTemplate = new RestTemplate();

	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}

	public AccountTransactions callAccountService(Order order) {
		String url = "http://localhost:8088/account/confirmtransaction";

		ResponseEntity<AccountTransactions> response = restTemplate.postForEntity(url, order,
				AccountTransactions.class);
		return response.getBody();
	}

	public Fees callFeeService(Order order) {
		String url = "http://localhost:8084/fees/calculate";

		ResponseEntity<Fees> response = restTemplate.postForEntity(url, order, Fees.class);
		return response.getBody();
	}

	public Fees getFees() {
		String url = "http://localhost:8084/fees";

		ResponseEntity<Fees> response = restTemplate.getForEntity(url, Fees.class);
		return response.getBody();
	}
}