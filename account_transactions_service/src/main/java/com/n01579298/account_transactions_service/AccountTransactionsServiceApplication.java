package com.n01579298.account_transactions_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AccountTransactionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountTransactionsServiceApplication.class, args);
	}

}
