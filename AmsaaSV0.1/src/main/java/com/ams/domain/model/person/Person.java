package com.ams.domain.model.person;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.domain.model.account.Account;
import com.ams.domain.model.account.Transaction;
import com.ams.domain.model.bill.Bill;
import com.ams.domain.model.bill.Payment;
import com.ams.domain.model.service.ServiceSubscription;

@Entity
@Access(PROPERTY)
@Table(name = "T_PERSON")
public class Person implements Serializable
{
	private static final long		serialVersionUID	= 1L;
	private Long					persnId;
	private String					persnFirstName;
	private String					persnLastName;
	private PersonDetail			persnDetail;
	private Address				persnAddress;
	private ServiceSubscription		persnServiceProfile;
	private Collection<Account>		persnAccounts;
	private Collection<Bill>			persnBill;
	private Collection<Transaction>	persnTransactions;
	private Collection<Payment>		persnPayments;
	private Collection<String>		persnRoles;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPersnId()
	{
		return persnId;
	}

	public void setPersnId(Long id)
	{
		this.persnId = id;
	}

	public String getPersnFirstName()
	{
		return persnFirstName;
	}

	public void setPersnFirstName(String firstName)
	{
		this.persnFirstName = firstName;
	}

	public String getPersnLastName()
	{
		return persnLastName;
	}

	public void setPersnLastName(String lastName)
	{
		this.persnLastName = lastName;
	}

	@Embedded
	public PersonDetail getPersnDetail()
	{
		return persnDetail;
	}

	public void setPersnDetail(PersonDetail detail)
	{
		this.persnDetail = detail;
	}

	@Embedded
	public Address getPersnAddress()
	{
		return persnAddress;
	}

	public void setPersnAddress(Address address)
	{
		this.persnAddress = address;
	}

	@OneToOne(mappedBy = "srvcSubcrptnOfPerson",fetch = FetchType.LAZY)
	@JsonIgnore
	public ServiceSubscription getPersnServiceProfile()
	{
		return persnServiceProfile;
	}

	public void setPersnServiceProfile(ServiceSubscription persnServiceProfile)
	{
		this.persnServiceProfile = persnServiceProfile;
	}

	/**
	 * @param accounts
	 *             the accounts to set
	 */
	public void setPersnAccounts(Collection<Account> accounts)
	{

		this.persnAccounts = accounts;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "acntHolder",targetEntity = Account.class,fetch = FetchType.LAZY)
	public Collection<Account> getPersnAccounts()
	{
		return persnAccounts;
	}

	@OneToMany(mappedBy = "billedPerson",fetch = FetchType.LAZY)
	@JsonIgnore
	public Collection<Bill> getPersnBill()
	{
		return persnBill;
	}

	public void setPersnBill(Collection<Bill> param)
	{
		this.persnBill = param;
	}

	@OneToMany(mappedBy = "transPerson",cascade = CascadeType.ALL,orphanRemoval = false,
				targetEntity = Transaction.class)
	public Collection<Transaction> getPersnTransactions()
	{
		return persnTransactions;
	}

	public void setPersnTransactions(Collection<Transaction> param)
	{
		this.persnTransactions = param;
	}

	@OneToMany(mappedBy = "paymntPerson",targetEntity = Payment.class,cascade = CascadeType.ALL,
				orphanRemoval = false,fetch = FetchType.LAZY)
	public Collection<Payment> getPersnPayments()
	{
		return persnPayments;
	}

	public void setPersnPayments(Collection<Payment> param)
	{
		this.persnPayments = param;
	}

	@ElementCollection
	public Collection<String> getPersnRoles()
	{
		return persnRoles;
	}

	public void setPersnRoles(Collection<String> persnRoles)
	{
		this.persnRoles = persnRoles;
	}

}
