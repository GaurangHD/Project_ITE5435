package com.n01579298.account_transactions_service.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.n01579298.account_transactions_service.model.AccountTransactions;
import com.n01579298.account_transactions_service.model.Order;
import com.n01579298.account_transactions_service.service.AccountTransactionsService;

@RestController
@RequestMapping("/account")
public class AccountTransactionsController {

	@Autowired
	private AccountTransactionsService accountTransactionsService;

	private final ObjectMapper objectMapper;

	public AccountTransactionsController(AccountTransactionsService accountTransactionsService) {
		this.accountTransactionsService = accountTransactionsService;
		this.objectMapper = new ObjectMapper();
		this.objectMapper.registerModule(new JavaTimeModule());
	}

	@PostMapping("/confirmtransaction")
	public ResponseEntity<AccountTransactions> ConfirmTransaction(@RequestBody Order order) {
		AccountTransactions accountTransactions = accountTransactionsService.confirmTransaction(order);

		try {
			objectMapper.writeValue(new File("target/account-order.json"), accountTransactions);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ResponseEntity.ok(accountTransactions);
	}
}