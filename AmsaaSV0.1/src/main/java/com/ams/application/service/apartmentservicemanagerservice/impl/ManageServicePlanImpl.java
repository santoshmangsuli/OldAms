package com.ams.application.service.apartmentservicemanagerservice.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.service.apartmentservicemanagerservice.ManageServicePlan;
import com.ams.application.service.apartmentservicemanagerservice.servicedata.ServicePlanDTO;
import com.ams.domain.model.service.InvalidServiceRatePlanException;
import com.ams.domain.model.service.Service;
import com.ams.domain.model.service.ServicePlan;
import com.ams.domain.model.service.ServiceRate;
import com.ams.domain.repository.ServicePlanRepository;
import com.ams.domain.repository.ServiceRepository;

@Transactional
@org.springframework.stereotype.Service("ManageServicePlan")
public class ManageServicePlanImpl implements ManageServicePlan
{

	@Autowired
	private ServiceRepository	serviceRepository;

	@Autowired
	private ServicePlanRepository	servicePlanRepository;

	public String registerServicePlan(String srvcPlanName)
	{

		return servicePlanRepository.createOrUpdate(new ServicePlan(srvcPlanName, new Date()));

	}

	public String updateServicePlanDetails(String srvcPlanName, Date srvcPlanCreationDate)
	{
		ServicePlan srvcPlan = servicePlanRepository.findById(srvcPlanName);
		srvcPlan.updateServicePlanDetails(srvcPlanName, srvcPlanCreationDate);
		return servicePlanRepository.createOrUpdate(srvcPlan);
	}

	public void removeServicePlan(String srvcPlanName)
	{
		servicePlanRepository.delete(srvcPlanName);
	}

	public List<ServicePlan> getAllServicePlans()
	{
		return servicePlanRepository.findAll();
	}

	public ServicePlan getServicePlanDetails(String srvcPlanName)
	{
		return servicePlanRepository.findById(srvcPlanName);
	}

	public void addServiceRateComponent(ServicePlanDTO srvcPlanDTO)
	{
		ServicePlan srvcPlan = servicePlanRepository.findById(srvcPlanDTO.getSrvcPlanName());
		Service srvc = serviceRepository.findById(srvcPlanDTO.getSrvcCode());

		ServiceRate srvcRateCompo = srvcPlan.addServiceRatePlan(
														srvc,
														srvcPlanDTO.getChargeName(),
														srvcPlanDTO.getChargeType(),
														srvcPlanDTO.getRateAmount(),
														srvcPlanDTO.getRateCurrency(),
														srvcPlanDTO.getRatePerUnit(),
														srvcPlanDTO.getChargeFreq());
		servicePlanRepository.saveOrUpdateServiceRate(srvcRateCompo);
	}

	public void removeServiceRateComponent(String srvcPlanName, String srvcCode) throws InvalidServiceRatePlanException
	{

		servicePlanRepository.deleteServiceRateByCriteria(srvcPlanName, srvcCode);
	}

	public void updateServiceRateComponentDetails(ServicePlanDTO spd)
	{

	}

}
