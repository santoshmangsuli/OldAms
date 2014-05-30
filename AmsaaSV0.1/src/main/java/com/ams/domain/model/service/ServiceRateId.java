package com.ams.domain.model.service;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class ServiceRateId implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private long				id;
	private String				splName;
	private String				svcCode;

	public ServiceRateId()
	{}

	public ServiceRateId(String srvcPlanName, String srvcCode)
	{
		this.splName = srvcPlanName;
		this.svcCode = srvcCode;
	}

	@Column(name = "id")
	@GeneratedValue
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	@Column(name = "ServicePlan_Id")
	public String getSplName()
	{
		return splName;
	}

	public void setSplName(String splName)
	{
		this.splName = splName;
	}

	@Column(name = "Service_Code")
	public String getSvcCode()
	{
		return svcCode;
	}

	public void setSvcCode(String svcCode)
	{
		this.svcCode = svcCode;
	}

}
