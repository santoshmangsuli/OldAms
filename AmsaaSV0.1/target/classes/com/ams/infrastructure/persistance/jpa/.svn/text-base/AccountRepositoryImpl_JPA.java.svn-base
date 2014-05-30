package com.ams.infrastructure.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.domain.model.account.Account;
import com.ams.domain.model.account.Transaction;
import com.ams.domain.repository.AccountRepository;

@Repository("AccountRepository")
public class AccountRepositoryImpl_JPA implements AccountRepository
{
	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public Long createOrUpdate(Account account)
	{
		entityManager.persist(account);
		return account.getAcntNumber();
	}

	public Long update(Account account)
	{
		entityManager.persist(account);
		return account.getAcntNumber();

	}

	public void delete(Long accountNo)
	{
		Account acnt = entityManager.find(Account.class, accountNo);
		entityManager.remove(acnt);

	}

	public Account findById(Long accountNumber)
	{
		return entityManager.find(Account.class, accountNumber);
	}

	public List<Account> findAll()
	{
		TypedQuery<Account> query = entityManager.createQuery(
													"select A from Account A",
													Account.class);
		return query.getResultList();

	}

	public List<Account> findAccountsByCustomerId(Long personId)
	{
		return null;
	}

	public void createTransaction(Transaction transaction)
	{
		entityManager.persist(transaction);

	}

	public void updateTransaction(Transaction transaction)
	{
		entityManager.persist(transaction);

	}

	public void deleteTransaction(Long transNumber)
	{
		Transaction trn = entityManager.find(Transaction.class, transNumber);
		entityManager.remove(trn);

	}

	public Transaction findTransactionById(long transNumber)
	{
		return entityManager.find(Transaction.class, transNumber);

	}

	public List<Transaction> findTransactionsByAcntNumber(long acntNumber)
	{
		TypedQuery<Transaction> query = entityManager
											.createQuery(
														"select T from Transaction T where Account.acntNumber",
														Transaction.class);
		return query.getResultList();
	}

}
