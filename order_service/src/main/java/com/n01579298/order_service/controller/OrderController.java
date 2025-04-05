package com.n01579298.order_service.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.n01579298.order_service.model.AccountTransactions;
import com.n01579298.order_service.model.Fees;
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
		order.setUserId(UserController.loginUser.getUserId());
		model.addAttribute("order", order);
		return "index";
	}
	
	@PostMapping("/buy")
	public String BuyOrder(@ModelAttribute Order order,Model model) {
		order.setTotalAmount(order.getQuantity() * order.getOrderAmt());
		order.setOrderDate(LocalDateTime.now());
		order.setOrderType("BUY");
		
		try {
			Order o1 = orderService.createOrder(order);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.writeValue(new File("target/order.json"), o1);
			AccountTransactions accountTransactions = orderService.callAccountService(o1);
			return "redirect:/order/view";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "index";
		}
	}
	
	@PostMapping("/sell")
	public String SellOrder(@ModelAttribute Order order,Model model) {
		order.setTotalAmount(order.getQuantity() * order.getOrderAmt());
		order.setOrderDate(LocalDateTime.now());
		order.setOrderType("SELL");
		
		Fees fees = orderService.callFeeService(order);
		order.setFeeId(fees.getFeeId());
		
		try {
			
			Order o1 = orderService.createOrder(order);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.writeValue(new File("target/order.json"), o1);
			AccountTransactions accountTransactions = orderService.callAccountService(order);
			return "redirect:/order/view";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "index";
		}
	}
	
	@GetMapping("/view")
	public String viewOrder(Model model) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		Order order;
		try {
			order = objectMapper.readValue(new File("target/order.json"), Order.class);
			model.addAttribute("order", order);

		    if (order.getFeeId() != null) {
		        Fees fee = orderService.getFees();
		        model.addAttribute("fee", fee);
		    }
		    return "order";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}