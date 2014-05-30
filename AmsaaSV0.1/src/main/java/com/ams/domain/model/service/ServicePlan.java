package com.ams.domain.model.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.ams.domain.model.measureandunits.Currency;
import com.ams.domain.model.measureandunits.Frequency;
import com.ams.domain.model.measureandunits.Money;
import com.ams.domain.model.measureandunits.Rate;
import com.ams.domain.model.measureandunits.Unit;
import com.ams.domain.model.service.ChargeComponent.ServiceChargeType;

/**
 * @author Raghavendra Badiger
 * 
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICEPLAN")
public class ServicePlan implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String				srvcPlanName;
	private Date				srvcPlanCreatDate;
	private Set<ServiceRate>		serviceRateSet;

	public ServicePlan()
	{}

	public ServicePlan(String srvcName, Date srvcCreatDate)
	{
		this.srvcPlanName = srvcName;
		this.srvcPlanCreatDate = srvcCreatDate;
	}

	/*
	 * 
	 * SERVICE PLAN DOMAIN FUNCTIONS
	 */

	public void updateServicePlanDetails(String srvcPlanName, Date srvcPlanCreationDate)
	{
		if ((srvcPlanName != null) && (this.srvcPlanName != srvcPlanName))
		{
			this.srvcPlanName = srvcPlanName;
		}
		if ((srvcPlanCreationDate != null) && (this.srvcPlanCreatDate != srvcPlanCreationDate))
		{
			this.srvcPlanCreatDate = srvcPlanCreationDate;
		}
	}

	public ServiceRate addServiceRatePlan(Service srvc, String chargeName,
									String chargeType, Double rateAmount,
									String rateCurrency,
									String ratePerUnit, String chargeFreq)
	{
		Rate chargeRate = new Rate(new Money(new BigDecimal(rateAmount),
										Currency.valueOf(rateCurrency)), Unit.valueOf(ratePerUnit));
		ServiceRate srvcRate = new ServiceRate(this, srvc, chargeName,
										ServiceChargeType.valueOf(chargeType),
										chargeRate, Frequency.valueOf(chargeFreq));
		return srvcRate;
	}

	/*
	 * ACCESSOR & MUTATORS
	 */

	@Id
	public String getSrvcPlanName()
	{
		return srvcPlanName;
	}

	@SuppressWarnings("unused")
	private void setSrvcPlanName(String srvcPlanName)
	{
		this.srvcPlanName = srvcPlanName;
	}

	public Date getSrvcPlanCreatDate()
	{
		return srvcPlanCreatDate;
	}

	@SuppressWarnings("unused")
	private void setSrvcPlanCreatDate(Date srvcPlanCreatDate)
	{
		this.srvcPlanCreatDate = srvcPlanCreatDate;
	}

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "srvcPlan",targetEntity = ServiceRate.class,fetch = FetchType.LAZY)
	public Set<ServiceRate> getServiceRateSet()
	{
		return serviceRateSet;
	}

	@SuppressWarnings("unused")
	private void setServiceRateSet(Set<ServiceRate> serviceRateSet)
	{
		this.serviceRateSet = serviceRateSet;
	}

}
