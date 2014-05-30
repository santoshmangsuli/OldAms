package com.ams.application.service.billingandpaymentservice;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BillBatchServiceImplTest
{
	static ApplicationContext		ctx;
	@Autowired
	public static BillBatchService	bbs;

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
		bbs = (BillBatchService) ctx.getBean("BillBatchService");
		System.out.println("BillBatchService Context Loaded!!");
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
		ApplicationContext ctx =
				new ClassPathXmlApplicationContext(
											"com/ams/infrastructure/configuration/ApplicationContext.xml");
		bbs = (BillBatchService) ctx.getBean("BillBatchService");
		bbs.fetchSubs();
	}
}
