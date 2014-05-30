package com.ams.domain.model.bill;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ams.domain.model.account.Transaction;
import com.ams.domain.model.person.Person;

@Entity
@Table(name = "T_PAYMENT")
@Access(PROPERTY)
public class Payment implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				paymntId;
	private BigDecimal			paymntAmount;
	private BigDecimal			paymntBalance;
	private String				paymntMethod;
	private Date				paymntDate;
	private Transaction			paymntTransaction;
	private Bill				paymntForBill;
	private Person				paymntPerson;

	@Id
	@GeneratedValue
	public Long getPaymntId()
	{
		return paymntId;
	}

	public void setPaymntId(Long paymntId)
	{
		this.paymntId = paymntId;
	}

	public BigDecimal getPaymntAmount()
	{
		return this.paymntAmount;
	}

	public void setPaymntAmount(BigDecimal paymntAmount)
	{
		this.paymntAmount = paymntAmount;
	}

	public String getPaymntMethod()
	{
		return this.paymntMethod;
	}

	public void setPaymntMethod(String paymntMethod)
	{
		this.paymntMethod = paymntMethod;
	}

	public Date getPaymntDate()
	{
		return this.paymntDate;
	}

	public void setPaymntDate(Date paymntDate)
	{
		this.paymntDate = paymntDate;
	}

	@OneToOne(cascade = CascadeType.PERSIST,optional = false)
	public Transaction getPaymntTransaction()
	{
		return this.paymntTransaction;
	}

	public void setPaymntTransaction(Transaction paymntTransaction)
	{
		this.paymntTransaction = paymntTransaction;
	}

	@ManyToOne(optional = false,cascade = CascadeType.ALL)
	@JoinColumn(name = "Bill_Number")
	public Bill getPaymntForBill()
	{
		return this.paymntForBill;
	}

	public void setPaymntForBill(Bill paymntForBill)
	{
		this.paymntForBill = paymntForBill;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Person_Id")
	public Person getPaymntPerson()
	{
		return this.paymntPerson;
	}

	public void setPaymntPerson(Person paymntPerson)
	{
		this.paymntPerson = paymntPerson;
	}

	public BigDecimal getPaymntBalance()
	{
		return paymntBalance;
	}

	public void setPaymntBalance(BigDecimal paymntBalance)
	{
		this.paymntBalance = paymntBalance;
	}

}
