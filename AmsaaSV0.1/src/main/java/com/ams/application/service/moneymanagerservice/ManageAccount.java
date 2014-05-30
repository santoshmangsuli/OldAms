package com.ams.application.service.moneymanagerservice;

import java.util.Set;

import com.ams.application.common.ServiceException;
import com.ams.application.service.moneymanagerservice.servicedata.AccountServiceData;
import com.ams.domain.model.account.Transaction;

public interface ManageAccount
{
	/******************** ACCOUNT RELATED OPERATIONS *******************************/

	public void createAccount(AccountServiceData acntServiceData)
														throws ServiceException;

	public void updateAccountDetails(AccountServiceData acntServiceData)
															throws ServiceException;

	public void deleteAccount(long acntNumber) throws ServiceException;

	public AccountServiceData getAccountDetail(long acntNumber)
													throws ServiceException;

	public Set<AccountServiceData> getAccounts(long persnId)
													throws ServiceException;

	/******************** ACCOUNT TRANSACTION RELATED OPERATIONS *******************************/

	public void createTransaction(Transaction transctn);

	public void updateTransactionDetails(Transaction transctn);

	public void deleteTransaction(long transNumber);

	public Transaction getTransactionDetails(long transNumber);

	public Set<Transaction> getTransactions(int acntNumber);

}
