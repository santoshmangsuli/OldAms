package com.ams.application.service.moneymanagerservice.assembler;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;

import com.ams.application.service.moneymanagerservice.servicedata.AccountServiceData;
import com.ams.domain.model.account.Account;
import com.ams.domain.model.account.AccountDetail;
import com.ams.domain.model.person.Person;

@Component("AccountServiceDataAssembler")
public class AccountServiceDataAssembler
{

	private Account			account;
	private AccountServiceData	accountServiceData;

	public Account getAccount(AccountServiceData acntServiceData, Person person)
	{
		this.account = new Account();
		this.account.setAcntNumber(acntServiceData.getNumber());
		this.account.setAcntName(acntServiceData.getName());
		this.account.setAcntBalance(acntServiceData.getBalance());
		this.account.setAcntCreationDate(acntServiceData.getCreationDate());
		this.account.setAcntCurrency(acntServiceData.getCurrency());
		this.account.setAcntType(acntServiceData.getType());
		this.account.setAcntHolder(person);

		AccountDetail ad = new AccountDetail();
		ad.setAcntDetNotes(acntServiceData.getDetail());
		this.account.setAcntDetail(ad);

		return (this.account);

	}

	public AccountServiceData getAccountServiceData(Account acnt)
	{
		this.accountServiceData = new AccountServiceData();
		this.accountServiceData.setNumber(acnt.getAcntNumber());
		this.accountServiceData.setName(acnt.getAcntName());
		this.accountServiceData.setBalance(acnt.getAcntBalance());
		this.accountServiceData.setCreationDate(acnt.getAcntCreationDate());
		this.accountServiceData.setCurrency(acnt.getAcntCurrency());
		this.accountServiceData.setType(acnt.getAcntType());
		this.accountServiceData.setPersonId(acnt.getAcntHolder().getPersnId());
		this.accountServiceData.setDetail(acnt.getAcntDetail()
										.getAcntDetNotes());

		return (this.accountServiceData);

	}

	public Set<AccountServiceData> getAccountServiceDataSet(Set<Account> acnts)
	{

		Set<AccountServiceData> asds = new TreeSet<AccountServiceData>();
		AccountServiceDataAssembler asdA = new AccountServiceDataAssembler();
		Iterator<Account> i = acnts.iterator();

		while (i.hasNext())
		{
			asds.add(asdA.getAccountServiceData(i.next()));
		}

		return asds;
	}

}
