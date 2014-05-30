package com.ams.domain.repository;

import java.util.Date;
import java.util.List;

import com.ams.domain.model.service.Service;
import com.ams.domain.model.service.ServiceCharge;
import com.ams.domain.model.service.ServiceSubscription;
import com.ams.domain.repository.common.Repository;

public interface ServiceRepository extends Repository<Service, String>
{

	public String createServiceCharge(ServiceCharge srvch);

	public Long createOrUpdateServiceSubscription(ServiceSubscription srvcsub);

	public List<ServiceSubscription> findServiceSubscription(final Date billGenerationDate);

	public List<Service> findBySrvcPlanName();
}
