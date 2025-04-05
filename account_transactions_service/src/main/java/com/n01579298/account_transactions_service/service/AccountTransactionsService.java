package com.n01579298.account_transactions_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n01579298.account_transactions_service.model.AccountTransactions;
import com.n01579298.account_transactions_service.model.Order;
import com.n01579298.account_transactions_service.repository.AccountTransactionsRepository;

@Service
public class AccountTransactionsService {
	
	@Autowired
	private AccountTransactionsRepository accountTransactionsRepository;
	
	public AccountTransactions confirmTransaction(Order order) {
		AccountTransactions accountTransactions = new AccountTransactions();
		accountTransactions.setOrderId(order.getOrderId());
		accountTransactions.setTransactionType(order.getOrderType());
		accountTransactions.setTicketSymbol(order.getTickerSymbol());
		accountTransactions.setTransactionPrice(order.getTotalAmount());
		accountTransactions.setOrderDateTime(order.getOrderDate());
		accountTransactions.setOrderAmount(order.getQuantity());
		return accountTransactionsRepository.save(accountTransactions);
	}
}