package com.ams.application.service.billingandpaymentservice.test;

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

import com.ams.application.service.billingandpaymentservice.ManageBill;
import com.ams.application.service.billingandpaymentservice.servicedata.BillDTO;
import com.ams.domain.model.bill.BillItem;
import com.ams.domain.model.service.Service;

public class ManageBillServiceImplTest
{
	static ApplicationContext	ctx;
	@Autowired
	public ManageBill			mbs;

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
		mbs = (ManageBill) ctx.getBean("ManageBillService");
		System.out.println("ManageBillService Context Loaded!!");
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
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		ManageBill mbs = (ManageBill) ctx.getBean("ManageBillService");
		Date date = new Date();
		BillDTO billDTO = new BillDTO();
		billDTO.setBillDate(formatter.format(date));
		billDTO.setBillDueDate(formatter.format(date));

		List<BillItem> billLineItems = new ArrayList<BillItem>();
		BillItem bi = new BillItem();
		bi.setBillItemAmount(new BigDecimal(45345.45));
		bi.setBillItemNumber((long) 2342344);
		bi.setBillItemQuantity(2334234);

		Service svc = new Service();
		svc.setSrvcCode("SVC03");
		svc.setSrvcName("PLUMB_SRVCS");

		bi.setBillItemService(svc);
		billLineItems.add(bi);
		billDTO.setBillLineItems(billLineItems);
		billDTO.setBillNumber(6555345);
		billDTO.getBillPeriodFromDate();
		billDTO.setBillPeriodToDate(formatter.format(date));
		billDTO.setBillTotalAmount(new Double(561.66));
		billDTO.setBillTotalTax(new Double(56.66));
		billDTO.setCustomerId(14);
		mbs.createNewBill(billDTO);

		System.out.println("Done ");

	}
}
