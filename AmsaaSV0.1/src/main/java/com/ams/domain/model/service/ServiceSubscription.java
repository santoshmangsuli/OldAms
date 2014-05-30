package com.ams.domain.model.service;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.domain.model.person.Person;

/**
 * Entity implementation class for Entity: ServiceProfile
 * 
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "T_SERVICESUBSCRIPTION")
public class ServiceSubscription implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	public enum Status
	{
		ACTIVE, TERMINATED, APPROVAL_PENDING
	};

	private Long		srvcSubcrptnId;
	private String		srvcSubcrptnName;
	private Person		srvcSubcrptnOfPerson;
	private Date		srvcSubcrptnStartDate;
	private Date		srvcSubcrptnEndDate;
	private Status		srvcSubcrptnStatus;
	private ServicePlan	subscribedSrvcsPlan;

	public ServiceSubscription()
	{
		super();
	}

	/*
	 * PERSON SERVICE PROFILE DOMAIN LOGIC
	 */

	public boolean isSrvcSubcrptnStatus(Status status)
	{
		if (srvcSubcrptnStatus.equals(status))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/*
	 * ACCESSOR & MUTATORS
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SUBSCRPTN_ID")
	public Long getSrvcSubcrptnId()
	{
		return srvcSubcrptnId;
	}

	public void setSrvcSubcrptnId(Long srvcSubcrptnId)
	{
		this.srvcSubcrptnId = srvcSubcrptnId;
	}

	@Column(name = "SUBSCRPTN_NAME")
	public String getSrvcSubcrptnName()
	{
		return srvcSubcrptnName;
	}

	public void setSrvcSubcrptnName(String srvcSubcrptnName)
	{
		this.srvcSubcrptnName = srvcSubcrptnName;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	@JsonIgnore
	public Person getSrvcSubcrptnOfPerson()
	{
		return srvcSubcrptnOfPerson;
	}

	public void setSrvcSubcrptnOfPerson(Person srvcSubcrptnOfPerson)
	{
		this.srvcSubcrptnOfPerson = srvcSubcrptnOfPerson;
	}

	@Column(name = "SUBSCRPTN_STARTDATE")
	public Date getSrvcSubcrptnStartDate()
	{
		return srvcSubcrptnStartDate;
	}

	public void setSrvcSubcrptnStartDate(Date srvcSubcrptnStartDate)
	{
		this.srvcSubcrptnStartDate = srvcSubcrptnStartDate;
	}

	@Column(name = "SUBSCRPTN_ENDDATE")
	public Date getSrvcSubcrptnendDate()
	{
		return srvcSubcrptnEndDate;
	}

	public void setSrvcSubcrptnendDate(Date srvcSubcrptnendDate)
	{
		this.srvcSubcrptnEndDate = srvcSubcrptnendDate;
	}

	@Column(name = "SUBSCRPTN_STATUS")
	public Status getSrvcSubcrptnStatus()
	{
		return srvcSubcrptnStatus;
	}

	public void setSrvcSubcrptnStatus(Status srvcSubcrptnStatus)
	{
		this.srvcSubcrptnStatus = srvcSubcrptnStatus;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SERVICEPLAN_ID")
	public ServicePlan getSubscribedSrvcsPlan()
	{
		return subscribedSrvcsPlan;
	}

	public void setSubscribedSrvcsPlan(ServicePlan srvcPlan)
	{
		this.subscribedSrvcsPlan = srvcPlan;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
