package com.n01579298.order_service.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.n01579298.order_service.model.Order;
import com.n01579298.order_service.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@GetMapping
	public String home(Model model) {
		Order order = new Order();
		order.setUser(UserController.loginUser);
		model.addAttribute("order", order);
		return "index";
	}
	
	@PostMapping
	public String postOrder(@ModelAttribute Order order,Model model) {
		order.setUser(UserController.loginUser);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.writeValue(new File("target/order.json"), order);
			Order order2 = objectMapper.readValue(new File("target/order.json"), Order.class);
			model.addAttribute(orderService.createOrder(order2));
			return "order";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "index";
		}
	}
}