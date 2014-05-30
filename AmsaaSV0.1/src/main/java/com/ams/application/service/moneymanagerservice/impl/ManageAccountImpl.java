package com.ams.application.service.moneymanagerservice.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.common.ServiceException;
import com.ams.application.service.moneymanagerservice.ManageAccount;
import com.ams.application.service.moneymanagerservice.assembler.AccountServiceDataAssembler;
import com.ams.application.service.moneymanagerservice.servicedata.AccountServiceData;
import com.ams.domain.model.account.Account;
import com.ams.domain.model.account.Transaction;
import com.ams.domain.model.person.Person;
import com.ams.domain.repository.AccountRepository;
import com.ams.domain.repository.PersonRepository;

@Transactional
@Service("ManageAccountService")
public class ManageAccountImpl implements ManageAccount
{

	@Autowired
	private AccountRepository			accountRepository;
	@Autowired
	private PersonRepository				personRepository;
	@Autowired
	private AccountServiceDataAssembler	accountServiceDataAssembler;

	/****************** ACCOUNT RELATED BUSINESS FUNCTIONS ************************/

	public void createAccount(AccountServiceData acntServiceData)
														throws ServiceException
	{

		Person p = this.personRepository
									.findById(acntServiceData.getPersonId());
		if (p.equals(null))
		{
			throw new ServiceException("The User with id :"
					+ acntServiceData.getPersonId()
					+ ", to whom account is to be set up, doesn't exist");
		}

		Account acnt = this.accountServiceDataAssembler.getAccount(
														acntServiceData, p);
		this.accountRepository.createOrUpdate(acnt);
	}

	public void updateAccountDetails(AccountServiceData acntServiceData)
															throws ServiceException
	{
		/****** CAN THIS BE COMBINED WITH CREATE OPERATION?? ***********/

		Person p = this.personRepository
									.findById(acntServiceData.getPersonId());
		if (p.equals(null))
		{
			throw new ServiceException(
									"The User with id :"
											+ acntServiceData.getPersonId()
											+ ",whose account details are to be modified, doesn't exist");
		}

		Account acnt = this.accountServiceDataAssembler.getAccount(
														acntServiceData, p);
		this.accountRepository.createOrUpdate(acnt);

	}

	public void deleteAccount(long acntNumber) throws ServiceException
	{

		this.accountRepository.delete(acntNumber);

	}

	public AccountServiceData getAccountDetail(long acntNumber)
													throws ServiceException
	{

		Account acnt = this.accountRepository.findById(acntNumber);
		if (acnt.equals(null))
		{
			throw new ServiceException("The requested Account: " + acntNumber
					+ " doesn't exist");
		}

		AccountServiceData asd = this.accountServiceDataAssembler
														.getAccountServiceData(acnt);

		return asd;
	}

	public Set<AccountServiceData> getAccounts(long persnId)
													throws ServiceException
	{

		@SuppressWarnings("unchecked")
		Set<Account> acnts = (Set<Account>) this.accountRepository
														.findAccountsByCustomerId(persnId);

		if (acnts.equals(null))
		{
			throw new ServiceException(
									"There are no accounts set up for the User with id: "
											+ persnId);
		}

		Set<AccountServiceData> asds = this.accountServiceDataAssembler
															.getAccountServiceDataSet(acnts);
		return asds;
	}

	/****************** ACCOUNT TRANSACTION RELATED BUSINESS FUNCTIONS ************************/

	public void createTransaction(Transaction transctn)
	{

		accountRepository.createTransaction(transctn);
	}

	public void updateTransactionDetails(Transaction transctn)
	{
		accountRepository.updateTransaction(transctn);
	}

	public void deleteTransaction(long transNumber)
	{
		accountRepository.deleteTransaction(transNumber);
	}

	public Transaction getTransactionDetails(long transNumber)
	{

		return accountRepository.findTransactionById(transNumber);
	}

	@SuppressWarnings("unchecked")
	public Set<Transaction> getTransactions(int acntNumber)
	{
		return (Set<Transaction>) accountRepository
											.findTransactionsByAcntNumber(acntNumber);
	}

}
