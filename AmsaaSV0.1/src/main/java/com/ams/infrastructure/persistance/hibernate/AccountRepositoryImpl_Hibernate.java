package com.ams.infrastructure.persistance.hibernate;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ams.domain.model.account.Account;
import com.ams.domain.model.account.Transaction;
import com.ams.domain.repository.AccountRepository;

@Transactional
@Repository
public class AccountRepositoryImpl_Hibernate implements AccountRepository
{
	@Autowired
	private SessionFactory	sessionFactory;

	public Long createOrUpdate(Account acnt)
	{
		this.sessionFactory.getCurrentSession().saveOrUpdate(acnt);
		return acnt.getAcntNumber();

	}

	public void deleteAccount(Account acnt)
	{

		this.sessionFactory.getCurrentSession().delete(acnt);

	}

	public Account findAccount(long acntNumber)
	{

		return (Account) this.sessionFactory.getCurrentSession().get(Account.class, acntNumber);

	}

	public List<Account> findAll()
	{
		return null;

	}

	public List<Account> findAccountsByCustomerId(Long custId)
	{
		return null;
	}

	public void createTransaction(Transaction trn)
	{

		this.sessionFactory.getCurrentSession().saveOrUpdate(trn);

	}

	public void updateTransaction(Transaction trn)
	{

		this.sessionFactory.getCurrentSession().saveOrUpdate(trn);

	}

	public void deleteTransaction(long trnNumber)
	{

		this.sessionFactory.getCurrentSession().delete(
												this.sessionFactory
																.getCurrentSession()
																.get(Transaction.class,
																	trnNumber));

	}

	public Transaction findAllTransactions(long transNumber)
	{
		return (Transaction) this.sessionFactory.getCurrentSession().get(
															Transaction.class,
															transNumber);

	}

	public Set<Transaction> findTransactions(long acntNumber)
	{
		return null;
	}

	public void deleteTransaction(Long transNumber)
	{

	}

	public Transaction findTransactionById(long transNumber)
	{
		return null;
	}

	public List<Transaction> findTransactionsByAcntNumber(long acntNumber)
	{
		return null;
	}

	public void delete(Long entityId)
	{

	}

	public Account findById(Long entityId)
	{
		return null;
	}

}
