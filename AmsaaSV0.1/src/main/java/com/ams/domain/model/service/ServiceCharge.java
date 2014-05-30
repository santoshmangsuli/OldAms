package com.ams.domain.model.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;
import org.joda.time.Days;

import com.ams.domain.model.measureandunits.Currency;
import com.ams.domain.model.measureandunits.Money;
import com.ams.domain.model.measureandunits.Period;
import com.ams.domain.model.measureandunits.Quantity;
import com.ams.domain.model.measureandunits.Unit;
import com.ams.domain.model.person.Person;

/**
 * @author Raghavendra Badiger
 * 
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICECHARGE")
public class ServiceCharge implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private Long				srvcChargeId;
	private String				srvcChargeName;
	private Date				srvcChargeDate;
	private Quantity			chargeQuantity;

	private Money				chargedAmount;

	private Money				discountAmount;

	private Money				taxOnAmount;

	private Money				netAmount;
	private Service			chargeForService;
	private Person				chargedPerson;
	private ServiceUsage		srvcUsage;
	private boolean			billed;

	/*
	 * SERVICE CHARGE DOMAIN FUNCTIONS
	 */

	public Money forPeriod(Person chargedPersn, Period billPeriod,
						ServiceRate
						serviceRate, Date chargedDate)
	{

		this.chargedPerson = chargedPersn;
		this.srvcChargeDate = chargedDate;
		this.chargeForService = serviceRate.getService();

		Unit billFreqUnit = serviceRate.getSrvcChargeComponent().getChargeRate().getPerUnit();
		BigDecimal qty = new BigDecimal(0);
		Date srvcStartDate =
				chargedPersn.getPersnServiceProfile().getSrvcSubcrptnStartDate();
		switch (billFreqUnit)
		{
		case Months:
			if (srvcStartDate.after(billPeriod.getFromDate()))
			{
				double noOfDays =
						Days.daysBetween(new DateTime(srvcStartDate).toDateMidnight(), new
										DateTime(billPeriod.getToDate()).toDateMidnight()).getDays();
				double daysInMonth = Days.daysBetween(new
												DateTime(billPeriod.getFromDate()).toDateMidnight(), new
												DateTime(billPeriod.getToDate()).toDateMidnight()).getDays();
				System.out.println("No of days:" + noOfDays);
				System.out.println("days in Bill period:" + daysInMonth);
				qty = new
						BigDecimal(noOfDays / daysInMonth);

			}
			else
			{
				qty = new BigDecimal(1);
			}
			break;

		}

		setPriceAndQuantity(qty, Unit.Months,
						serviceRate.getSrvcChargeComponent().getChargeRate().getPrice().getAmount(),
						serviceRate.getSrvcChargeComponent().getChargeRate().getPrice().getCurrency());
		return this.chargedAmount;
	}

	public Money forUsage(ServiceUsage srvcUsage, ServiceRate
						serviceRate, Date chargedDate)
	{

		this.srvcUsage = srvcUsage;
		this.chargedPerson = srvcUsage.getPersn();
		this.chargeForService = srvcUsage.getSrvc();
		this.setSrvcChargeDate(chargedDate);
		Unit usageUnit = serviceRate.getSrvcChargeComponent().getChargeRate().getPerUnit();
		srvcUsage.calculateQuantity(usageUnit);

		return this.chargedAmount;
	}

	private void setPriceAndQuantity(BigDecimal qty, Unit unit, BigDecimal amount, Currency currency)
	{
		this.chargeQuantity = new Quantity(qty, unit);
		this.chargedAmount = new Money((qty.multiply(amount)).setScale(2, RoundingMode.FLOOR), currency);
	}

	/*
	 * SERVICE CHARGE ACCESSOR N MUTATOR FUNCTIONS
	 */
	@Id
	public Long getSrvcChargeId()
	{
		return srvcChargeId;
	}

	public void setSrvcChargeId(Long srvcChargeId)
	{
		this.srvcChargeId = srvcChargeId;
	}

	public String getSrvcChargeName()
	{
		return srvcChargeName;
	}

	public void setSrvcChargeName(String srvcChargeName)
	{
		this.srvcChargeName = srvcChargeName;
	}

	@Temporal(TemporalType.DATE)
	public Date getSrvcChargeDate()
	{
		return srvcChargeDate;
	}

	public void setSrvcChargeDate(Date srvcChargeDate)
	{
		this.srvcChargeDate = srvcChargeDate;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "chargedAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "chargedAmount_currency")) })
	public Money getChargedAmount()
	{
		return chargedAmount;
	}

	public void setChargedAmount(Money chargedAmount)
	{
		this.chargedAmount = chargedAmount;
	}

	@Embedded
	public Quantity getChargeQuantity()
	{
		return chargeQuantity;
	}

	public void setChargeQuantity(Quantity chargeQuantity)
	{
		this.chargeQuantity = chargeQuantity;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "discountAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "discountAmount_currency")) })
	public Money getDiscountAmount()
	{
		return discountAmount;
	}

	public void setDiscountAmount(Money discountAmount)
	{
		this.discountAmount = discountAmount;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "amount",column = @Column(name = "taxOnAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "taxOnAmount_currency")) })
	public Money getTaxOnAmount()
	{
		return taxOnAmount;
	}

	public void setTaxOnAmount(Money taxOnAmount)
	{
		this.taxOnAmount = taxOnAmount;
	}

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "amount",column = @Column(name = "netAmount")),
			@AttributeOverride(name = "currency",column = @Column(name = "netAmount_currency")) })
	public Money getNetAmount()
	{
		return netAmount;
	}

	public void setNetAmount(Money netAmount)
	{
		this.netAmount = netAmount;
	}

	public Service getChargeForService()
	{
		return chargeForService;
	}

	public void setChargeForService(Service chargeForService)
	{
		this.chargeForService = chargeForService;
	}

	public Person getChargedPerson()
	{
		return chargedPerson;
	}

	public void setChargedPerson(Person chargedPerson)
	{
		this.chargedPerson = chargedPerson;
	}

	public ServiceUsage getSrvcUsage()
	{
		return srvcUsage;
	}

	public void setSrvcUsage(ServiceUsage srvcUsage)
	{
		this.srvcUsage = srvcUsage;
	}

	@Enumerated(EnumType.STRING)
	public boolean isBilled()
	{
		return billed;
	}

	public void setBilled(boolean billed)
	{
		this.billed = billed;
	}

}
