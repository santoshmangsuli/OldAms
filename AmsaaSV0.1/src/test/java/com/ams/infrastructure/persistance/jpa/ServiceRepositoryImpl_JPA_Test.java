package com.ams.infrastructure.persistance.jpa;

import static org.junit.Assert.assertEquals;

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

import com.ams.domain.model.service.Service;
import com.ams.domain.repository.ServiceRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/ams/infrastructure/configuration/ApplicationContext.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "txManager_Jpa",defaultRollback = true)
public class ServiceRepositoryImpl_JPA_Test
{
	private Service			srvc;

	@Autowired
	private ServiceRepository	srvcRepo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{

	}

	@Before
	public void setUp() throws Exception
	{
		srvc = new Service("SRVC-01", "Monthly-Maintainance", "Monthly maintainance service");

	}

	@After
	public void tearDown() throws Exception
	{}

	@Test
	public final void testCreate()
	{
		assertEquals(0, srvcRepo.findAll().size());
		srvcRepo.createOrUpdate(srvc);
		assertEquals(srvc.getSrvcDescription(), srvcRepo.findById("SRVC-01").getSrvcDescription());
		assertEquals(1, srvcRepo.findAll().size());

	}

	@Test
	public final void testUpdate()
	{
		srvc.modifyServiceDetails("MM", "Monthly maint srvc");
		srvcRepo.createOrUpdate(srvc);
		assertEquals("MM", srvcRepo.findById(srvc.getSrvcCode()).getSrvcName());
	}

	@Test
	public final void testDelete()
	{
		assertEquals(0, srvcRepo.findAll().size());
		srvcRepo.createOrUpdate(srvc);
		assertEquals(srvc.getSrvcDescription(), srvcRepo.findById("SRVC-01").getSrvcDescription());
		assertEquals(1, srvcRepo.findAll().size());
		srvcRepo.delete(srvc.getSrvcCode());
		assertEquals(0, srvcRepo.findAll().size());
	}

	@Test
	public final void testFindById()
	{
		srvcRepo.createOrUpdate(srvc);
		assertEquals(srvc.getSrvcDescription(), srvcRepo.findById("SRVC-01").getSrvcDescription());

	}

	@Test
	public final void testFindBySrvcPlanName()
	{
		srvcRepo.findBySrvcPlanName();
		assertEquals(srvc.getSrvcDescription(), srvcRepo.findById("SRVC-01").getSrvcDescription());

	}

	@Test
	public final void testFindAll()
	{
		assertEquals(0, srvcRepo.findAll().size());
		srvcRepo.createOrUpdate(srvc);
		assertEquals(1, srvcRepo.findAll().size());

	}

}
