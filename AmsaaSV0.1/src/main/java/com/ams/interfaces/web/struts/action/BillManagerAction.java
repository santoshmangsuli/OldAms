package com.ams.interfaces.web.struts.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ams.application.service.billingandpaymentservice.ManageBill;
import com.ams.application.service.billingandpaymentservice.servicedata.BillDTO;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
@InterceptorRef(value = "json")
public class BillManagerAction extends ActionSupport implements SessionAware
{

	private static final long	serialVersionUID	= 1L;
	@Autowired
	private ManageBill			billingService;
	private Map<String, Object>	session;
	private BillDTO			billSrvcData		= new BillDTO();

	public BillDTO getBillSrvcData()
	{
		return billSrvcData;
	}

	public void setBillSrvcData(BillDTO billSrvcData)
	{
		this.billSrvcData = billSrvcData;
	}

	public void setSession(Map<String, Object> arg0)
	{
		this.session = arg0;

	}

	@Action(value = "/addBillItemsAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },
			results = { @Result(name = "success",type = "json",params = { "root", "billSrvcData"
			}), @Result(name = "input",type = "json",params = { "root", "billSrvcData" }) })
	public String addBillItems()
	{
		try
		{
			System.out.println(billSrvcData.getCustomerId());
			System.out.println(billSrvcData.getBillDate());
			billSrvcData = billingService.addBillItems(billSrvcData);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return "success";
	}

	@Action(value = "/submitBillAction",interceptorRefs = { @InterceptorRef(value = "jsonValidationWorkflowStack") },
			results = { @Result(name = "success",type = "json",params = { "root", "billSrvcData"
			}), @Result(name = "input",type = "json",params = { "root", "billSrvcData" }) })
	public String submitBill()
	{
		try
		{
			billSrvcData = billingService.createNewBill(billSrvcData);

		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return "success";
	}

}
