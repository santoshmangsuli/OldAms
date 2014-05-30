package com.ams.domain.model.service;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ams.domain.model.measureandunits.Period;
import com.ams.domain.model.measureandunits.Quantity;
import com.ams.domain.model.measureandunits.Unit;
import com.ams.domain.model.person.Person;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICEUSAGE")
public class ServiceUsage implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Service			srvc;
	private Person				persn;
	private Period				srvcUsagePeriod;
	private Quantity			srvcUsageQty;
	private long				serviceUsageId;

	public ServiceUsage()
	{}

	public ServiceUsage(Person persn, Service srvc, Period duration)
	{
		this.srvc = srvc;
		this.persn = persn;
		this.srvcUsagePeriod = duration;

	}

	/*
	 * SERVICE CHARGE DOMAIN FUNCTIONS
	 */

	public void calculateQuantity(Unit unit)
	{

	}

	/*
	 * SERVICE CHARGE ACCESSOR & MUTATOR FUNCTIONS
	 */

	public Service getSrvc()
	{
		return srvc;
	}

	void setSrvc(Service srvc)
	{
		this.srvc = srvc;
	}

	public Person getPersn()
	{
		return persn;
	}

	void setPersn(Person persn)
	{
		this.persn = persn;
	}

	public Period getSrvcUsagePeriod()
	{
		return srvcUsagePeriod;
	}

	void setSrvcUsagePeriod(Period srvcUsagePeriod)
	{
		this.srvcUsagePeriod = srvcUsagePeriod;
	}

	public Quantity getSrvcUsageQty()
	{
		return srvcUsageQty;
	}

	void setSrvcUsageQty(Quantity srvcUsageQty)
	{
		this.srvcUsageQty = srvcUsageQty;
	}

	@Id
	public long getServiceUsageId()
	{
		return serviceUsageId;
	}

	public void setServiceUsageId(long serviceUsageId)
	{
		this.serviceUsageId = serviceUsageId;
	}

}
