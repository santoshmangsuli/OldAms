package com.ams.domain.repository;

import java.util.List;

import com.ams.domain.model.service.ServicePlan;
import com.ams.domain.model.service.ServiceRate;
import com.ams.domain.model.service.ServiceRateId;
import com.ams.domain.repository.common.Repository;

public interface ServicePlanRepository extends Repository<ServicePlan, String>
{

	void saveOrUpdateServiceRate(ServiceRate srvcRate);

	List<ServiceRate> findAllServiceRateByPlanName(String srvcPlanName);

	List<ServiceRate> findAllServiceRateByCriteria(String srvcPlanName, String srvcCode);

	void deleteServiceRate(ServiceRateId srvcRateId);

	void deleteServiceRateByCriteria(String srvcPlanName, String srvcCode);

}
