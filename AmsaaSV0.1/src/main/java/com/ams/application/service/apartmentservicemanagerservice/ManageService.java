package com.ams.application.service.apartmentservicemanagerservice;

import java.util.List;

import com.ams.domain.model.service.Service;
import com.ams.domain.model.service.ServiceSubscription;

public interface ManageService
{
	String registerService(Service svc);

	String updateServiceDetails(Service svc);

	void deleteService(String svcCode);

	Service getService(String svcCode);

	List<Service> getAllServices();

	public void registerServiceSub(ServiceSubscription serviceSub);

}
