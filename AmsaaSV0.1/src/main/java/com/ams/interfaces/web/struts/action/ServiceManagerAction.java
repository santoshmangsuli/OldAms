package com.ams.interfaces.web.struts.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.ams.application.service.apartmentservicemanagerservice.ManageService;
import com.ams.domain.model.service.Service;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
@InterceptorRef(value = "json")
public class ServiceManagerAction extends ActionSupport
{

	private static final long	serialVersionUID	= 1L;
	@Autowired
	private ManageService		manageServices;
	private Service			aptService		= new Service();
	private List<Service>		aptServicesList;

	public Service getAptService()
	{
		return aptService;
	}

	public List<Service> getAptServicesList()
	{
		return this.aptServicesList;
	}

	public void setAptService(Service arg)
	{
		this.aptService = arg;
	}

	public void setAptServicesList(List<Service> servicesList)
	{
		this.aptServicesList = servicesList;
	}

	@Action(value = "/registerNewServiceAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String registerNewService()
	{
		try
		{
			manageServices.registerService(aptService);
			manageServices.getAllServices();
			return "success";

		} catch (Exception excptn)
		{
			return "failure";
		}

	}

	@Action(value = "/updateServiceDetailAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String updateServiceDetails()
	{
		try
		{
			System.out.println("Service code:" + aptService.getSrvcCode());
			manageServices.updateServiceDetails(aptService);
			manageServices.getAllServices();
			return "success";

		} catch (Exception excptn)
		{
			excptn.printStackTrace();
		}

		return "failure";
	}

	@Action(value = "/deleteServiceAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String deleteService()
	{
		try
		{
			System.out.println("Service code:" + aptService.getSrvcCode());
			manageServices.deleteService(aptService.getSrvcCode());
			manageServices.getAllServices();
			return "success";

		} catch (Exception excptn)
		{
			excptn.printStackTrace();
		}

		return "failure";
	}

	@Action(value = "/getServiceDetailAction",results = { @Result(name = "success",type = "json",params = { "root", "aptService" }) })
	public String getServiceDetail()
	{
		this.aptService = manageServices.getService(aptService.getSrvcCode());
		return "success";
	}

	@Action(value = "/getServicesListAction",results = { @Result(name = "success",type = "json",params = { "root", "aptServicesList" }) })
	public String getServicesList()
	{
		aptServicesList = manageServices.getAllServices();
		return "success";
	}

}
