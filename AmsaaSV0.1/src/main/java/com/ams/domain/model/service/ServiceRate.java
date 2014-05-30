package com.ams.domain.model.service;

import static javax.persistence.AccessType.PROPERTY;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.ams.domain.model.measureandunits.Frequency;
import com.ams.domain.model.measureandunits.Rate;
import com.ams.domain.model.service.ChargeComponent.ServiceChargeType;

@Entity
@Access(PROPERTY)
@Table(name = "T_SERVICERATE")
public class ServiceRate implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private Long				srvcRateId;
	private ServicePlan			srvcPlan;
	private Service			service;
	private ChargeComponent		srvcChargeComponent;

	public ServiceRate()
	{

	}

	public ServiceRate(ServicePlan srvcPlan, Service srvc, String chargeName,
					ServiceChargeType chargeType,
					Rate chargeRate, Frequency chargeFreq)
	{
		this.srvcPlan = srvcPlan;
		this.service = srvc;
		this.srvcChargeComponent = new ChargeComponent(chargeName, chargeType, chargeRate, chargeFreq);

	}

	/*
	 * SERVICE RATE COMPONENT DOMAIN FUNCTIONS
	 */

	/*
	 * ACCESSOR & MUTATORS
	 */

	@Id
	@GeneratedValue
	public Long getSrvcRateId()
	{
		return srvcRateId;
	}

	public void setSrvcRateId(Long srvcRateId)
	{
		this.srvcRateId = srvcRateId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ServicePlan_Id",referencedColumnName = "srvcPlanName")
	@JsonIgnore
	public ServicePlan getSrvcPlan()
	{
		return srvcPlan;
	}

	@SuppressWarnings("unused")
	private void setSrvcPlan(ServicePlan srvcPlan)
	{
		this.srvcPlan = srvcPlan;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Service_Code",referencedColumnName = "srvcCode")
	public Service getService()
	{
		return service;
	}

	@SuppressWarnings("unused")
	private void setService(Service service)
	{
		this.service = service;
	}

	@Embedded
	public ChargeComponent getSrvcChargeComponent()
	{
		return srvcChargeComponent;
	}

	@SuppressWarnings("unused")
	private void setSrvcChargeComponent(ChargeComponent srvcChargeComponent)
	{
		this.srvcChargeComponent = srvcChargeComponent;
	}

	public String toString()
	{
		return "srvcRateId " + srvcRateId + " Charge rate" + srvcChargeComponent.getChargeRate().getPrice();
	}
}
