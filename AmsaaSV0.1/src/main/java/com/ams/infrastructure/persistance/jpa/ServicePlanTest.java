package com.ams.infrastructure.persistance.jpa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ams.application.service.apartmentservicemanagerservice.ManageService;
import com.ams.application.service.apartmentservicemanagerservice.ManageServicePlan;
import com.ams.application.service.apartmentservicemanagerservice.servicedata.ServicePlanDTO;
import com.ams.domain.model.service.Service;

public class ServicePlanTest
{
	public static void main(String args[])
	{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com/ams/infrastructure/configuration/ApplicationContext.xml");
		ManageServicePlan manageServicePlan = (ManageServicePlan) context.getBean("ManageServicePlan");
		// manageServicePlan.registerServicePlan("Plumber");
		ManageService manageService = (ManageService) context.getBean("ManageService");
		Service svc = new Service();
		svc.setSrvcCode("SRVC-002");
		svc.setSrvcDescription("Monthly Security");
		svc.setSrvcName("Security");

		manageService.registerService(svc);
		ServicePlanDTO spdto = new ServicePlanDTO();
		spdto.setChargeFreq("MONTHLY");
		spdto.setChargeName("Monthy-Sec");
		spdto.setChargeType("RATE");
		spdto.setRateAmount(new Double("250"));
		spdto.setRateCurrency("INR");
		spdto.setRatePerUnit("Months");
		spdto.setSrvcCode("SRVC-002");
		spdto.setSrvcPlanId(2);
		spdto.setSrvcPlanName("ADHOC-BILL");

		// manageServicePlan.addServiceRateComponent(spdto);
	}

}
