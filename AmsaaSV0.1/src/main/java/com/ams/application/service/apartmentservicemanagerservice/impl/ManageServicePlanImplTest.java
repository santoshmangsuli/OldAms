package com.ams.application.service.apartmentservicemanagerservice.impl;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ams.application.service.apartmentservicemanagerservice.ManageServicePlan;
import com.ams.application.service.apartmentservicemanagerservice.servicedata.ServicePlanDTO;
import com.ams.application.service.billingandpaymentservice.servicedata.BillDTO;
import com.ams.domain.model.bill.BillItem;
import com.ams.domain.model.measureandunits.Currency;
import com.ams.domain.model.measureandunits.Frequency;
import com.ams.domain.model.measureandunits.Money;
import com.ams.domain.model.measureandunits.Rate;
import com.ams.domain.model.measureandunits.Unit;
import com.ams.domain.model.service.ChargeComponent.ServiceChargeType;
import com.ams.domain.model.service.Service;
import com.ams.domain.model.service.ServicePlan;
import com.ams.domain.model.service.ServiceRate;

public class ManageServicePlanImplTest
{
	static ApplicationContext		ctx;
	@Autowired
	public static ManageServicePlan	msp;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		ctx = new ClassPathXmlApplicationContext(
											"com/ams/infrastructure/configuration/ApplicationContext.xml");

		System.out.println("Application Context Loaded!!");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{

	}

	@Before
	public void setUp() throws Exception
	{
		// msp = (ManageServicePlan) ctx.getBean("ManageServicePlan");
		System.out.println("ManageServicePlan Context Loaded!!");
	}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public void testInitCustomerBill()
	{

		fail("Not yet implemented");
	}

	@Test
	public void testAddBillItem()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSetBillCustomer()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSaveOrUpdateBillBill()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteBill()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testPayBill()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveBillItem()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testAddBillItems()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testSaveOrUpdateBillBillServiceData()
	{
		fail("Not yet implemented");
	}

	// temp main method implementation for test data
	public static void main(String args[])
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
														"com/ams/infrastructure/configuration/ApplicationContext.xml");
		// ManageBill mbs = (ManageBill) ctx.getBean("");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = new Date();
		BillDTO billDTO = new BillDTO();
		billDTO.setBillDate(formatter.format(date));
		billDTO.setBillDueDate(formatter.format(date));
		List<BillItem> billLineItems = new ArrayList<BillItem>();
		BillItem bi = new BillItem();

		// billLineItems.add(billLineItems);
		billDTO.setBillLineItems(billLineItems);
		billDTO.setBillNumber(6555565);
		billDTO.getBillPeriodFromDate();
		billDTO.setBillPeriodToDate(formatter.format(date));
		billDTO.setBillTotalAmount(new Double(56456.66));
		billDTO.setBillTotalTax(new Double(56456.66));
		billDTO.setCustomerId(657675);
		msp = (ManageServicePlan) ctx.getBean("ManageServicePlan");
		Service srvc = new Service("SRVC-03", "Plumber_Service", "Plumber service");
		ServicePlan sp = new ServicePlan("BasicSrvc", new Date());
		ServiceRate srvcRate = new ServiceRate(sp, srvc, "Maintainance Service Charge", ServiceChargeType.valueOf("RATE"), new Rate(new Money(new BigDecimal(1000), Currency.INR), Unit.Months), Frequency.valueOf("MONTHLY"));
		// svc.setSrvcCode(srvcCode)
		List<ServiceRate> serviceRateLst = new ArrayList<ServiceRate>();
		serviceRateLst.add(srvcRate);
		srvc.setSrvcRateList(serviceRateLst);
		// msp.registerService(srvc);
		System.out.println("Done");

		ServicePlanDTO spDto = new ServicePlanDTO();
		spDto.setSrvcPlanName("BasicSrvc");
		spDto.setSrvcCode("SRVC-01");
		spDto.setChargeName("Maintainance Charge");
		spDto.setChargeType("RATE");
		spDto.setRateAmount(1000.0);
		spDto.setRateCurrency("INR");
		spDto.setRatePerUnit("Months");
		spDto.setChargeFreq("MONTHLY");
		msp.addServiceRateComponent(spDto);

	}
}
