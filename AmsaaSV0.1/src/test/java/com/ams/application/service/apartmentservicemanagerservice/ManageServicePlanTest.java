package com.ams.application.service.apartmentservicemanagerservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.service.apartmentservicemanagerservice.servicedata.ServicePlanDTO;
import com.ams.domain.model.service.Service;
import com.ams.domain.model.service.ServicePlan;
import com.ams.domain.model.service.ServiceRate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/ams/infrastructure/configuration/ApplicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager_Jpa",defaultRollback = false)
public class ManageServicePlanTest
{
	@Autowired
	private ManageServicePlan	manageSrvcPlan;
	@Autowired
	private ManageService		manageSrvc;
	private ServicePlan			srvcPlan;
	private Service			srvc;
	private ServiceRate	srvcRateCompo;
	String					id;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{}

	@Before
	public void setUp() throws Exception
	{
		id = manageSrvcPlan.registerServicePlan("ECONOMY-PLAN");

		manageSrvc.registerService(new Service("SRVC-01", "MAINT-SRVC",
										"Maintainance Service"));

	}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public final void testRegisterServicePlan()
	{
		assertEquals("", 1, manageSrvcPlan.getAllServicePlans().size());
		manageSrvcPlan.registerServicePlan("LOCAL-PLAN");
		assertEquals("# of service plans in repo:" + manageSrvcPlan.getAllServicePlans().size(), 2, manageSrvcPlan.getAllServicePlans().size());
		assertEquals(new Date().getDay(), manageSrvcPlan.getServicePlanDetails("ECONOMY-PLAN").getSrvcPlanCreatDate().getDay());
	}

	@Test
	public final void testUpdateServicePlan()
	{
		assertEquals("", 1, manageSrvcPlan.getAllServicePlans().size());
		assertEquals(new Date().getHours(), manageSrvcPlan.getServicePlanDetails("ECONOMY-PLAN").getSrvcPlanCreatDate().getHours());
		manageSrvcPlan.updateServicePlanDetails("ECONOMY-PLAN", new Date());
		assertEquals(new Date().getSeconds(), manageSrvcPlan.getServicePlanDetails("ECONOMY-PLAN").getSrvcPlanCreatDate().getSeconds());

	}

	@Test(expected = java.lang.NullPointerException.class)
	public final void testRemoveServicePlan()
	{
		assertEquals("# of service plans in repo:" + manageSrvcPlan.getAllServicePlans().size(), 1, manageSrvcPlan.getAllServicePlans().size());
		manageSrvcPlan.removeServicePlan("ECONOMY-PLAN");
		assertEquals("# of service plans in repo:" + manageSrvcPlan.getAllServicePlans().size(), 0, manageSrvcPlan.getAllServicePlans().size());
		assertEquals("ECONOMY-PLAN", manageSrvcPlan.getServicePlanDetails("ECONOMY-PLAN").getSrvcPlanName());
	}

	@Test
	public final void testGetAllServicePlans()
	{
		assertEquals("# of service plans in repo:" + manageSrvcPlan.getAllServicePlans().size(), 1, manageSrvcPlan.getAllServicePlans().size());
		id = manageSrvcPlan.registerServicePlan("ECO-PLAN");
		String id1 = manageSrvcPlan.registerServicePlan("REGULAR-PLAN");
		assertEquals("# of service plans in repo:" + manageSrvcPlan.getAllServicePlans().size(), 3, manageSrvcPlan.getAllServicePlans().size());

	}

	@Test
	public final void testGetServicePlanDetails()
	{
		// assertEquals(0,
		// manageSrvcPlan.getServicePlanDetails("ECONOMY-PLAN").getServiceRatePlanSet().size());
		ServicePlanDTO spDto = new ServicePlanDTO();
		spDto.setSrvcPlanName("ECONOMY-PLAN");
		spDto.setSrvcCode("SRVC-01");
		spDto.setChargeName("Maintainance Charge");
		spDto.setChargeType("RATE");
		spDto.setRateAmount(1000.0);
		spDto.setRateCurrency("INR");
		spDto.setRatePerUnit("Months");
		spDto.setChargeFreq("MONTHLY");
		manageSrvcPlan.addServiceRateComponent(spDto);
		// assertEquals(1,
		// manageSrvcPlan.getServicePlanDetails("ECONOMY-PLAN").getServiceRatePlanSet().size());

	}

	@Test
	public final void testAddServiceRateComponent()
	{
		fail("Not yet implemented");
	}

	@Test
	public final void testRemoveServiceRateComponent()
	{
		fail("Not yet implemented");
	}

	@Test
	public final void testUpdateServiceRateComponentDetails()
	{
		fail("Not yet implemented");
	}

}
