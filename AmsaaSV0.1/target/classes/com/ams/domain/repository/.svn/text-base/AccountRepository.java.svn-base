package com.ams.domain.repository;

import java.util.List;

import com.ams.domain.model.account.Account;
import com.ams.domain.model.account.Transaction;
import com.ams.domain.repository.common.Repository;

public interface AccountRepository extends Repository<Account, Long>
{

	public List<Account> findAccountsByCustomerId(Long custId);

	public void createTransaction(Transaction transaction);

	public void updateTransaction(Transaction transaction);

	public void deleteTransaction(Long transNumber);

	public Transaction findTransactionById(long transNumber);

	public List<Transaction> findTransactionsByAcntNumber(long acntNumber);

}
