package com.n01579298.account_transactions_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.n01579298.account_transactions_service.model.AccountTransactions;

@Repository
public interface AccountTransactionsRepository extends MongoRepository<AccountTransactions, String> {

}
