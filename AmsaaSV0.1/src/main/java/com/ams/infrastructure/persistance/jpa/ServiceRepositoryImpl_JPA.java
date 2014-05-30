package com.ams.infrastructure.persistance.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.domain.model.service.Service;
import com.ams.domain.model.service.ServiceCharge;
import com.ams.domain.model.service.ServiceSubscription;
import com.ams.domain.repository.ServiceRepository;

@Repository("ServiceRepository")
public class ServiceRepositoryImpl_JPA implements ServiceRepository
{

	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public String createOrUpdate(Service srvc)
	{
		if (srvc.getSrvcCode() == null)
		{
			entityManager.persist(srvc);
		}
		else
		{
			entityManager.merge(srvc);
		}

		System.out.println(srvc.toString());
		return srvc.getSrvcCode();

	}

	public void delete(String svcCode)
	{
		Service svc = entityManager.find(Service.class, svcCode);
		entityManager.remove(svc);

	}

	public Service findById(String svcCode)
	{
		return entityManager.find(Service.class, svcCode);
	}

	public List<Service> findAll()
	{
		TypedQuery<Service> query = entityManager.createQuery("select S from Service S", Service.class);
		return query.getResultList();
	}

	public List<Service> findBySrvcPlanName()
	{
		TypedQuery<Service> query = entityManager.createQuery("select S from Service S, ServiceRate R " +
				" where S.srvcCode = R.service.srvcCode and R.srvcPlan.srvcPlanName='ADHOC-BILL'",
													Service.class);
		return query.getResultList();
	}

	public String createServiceCharge(ServiceCharge srvch)
	{
		if (srvch.getSrvcChargeId() != 0)
		{
			entityManager.persist(srvch);
		}
		else
		{
			entityManager.persist(srvch);
		}
		return null;

	}

	public Long createOrUpdateServiceSubscription(ServiceSubscription srvcsub)
	{
		if (srvcsub.getSrvcSubcrptnId() != null)
		{
			entityManager.persist(srvcsub);
		}
		else
		{
			entityManager.merge(srvcsub);
		}

		System.out.println(srvcsub.toString());
		return srvcsub.getSrvcSubcrptnId();

	}

	public List<ServiceSubscription> findServiceSubscription(final Date billGenerationDate)
	{
		TypedQuery<ServiceSubscription> query =
				entityManager.
							createQuery("select S from ServiceSubscription S where S.srvcSubcrptnStatus=:status " +
									" and S.srvcSubcrptnStartDate<:billGenerationDate",
										ServiceSubscription.class);
		query.setParameter("status", ServiceSubscription.Status.ACTIVE);
		query.setParameter("billGenerationDate", billGenerationDate);

		return query.getResultList();
	}
}
