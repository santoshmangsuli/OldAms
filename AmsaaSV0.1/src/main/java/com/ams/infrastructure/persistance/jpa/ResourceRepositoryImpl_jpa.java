package com.ams.infrastructure.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.domain.model.booking.Resource;
import com.ams.domain.repository.ResourceRepository;

@Repository("ResourceRepository")
public class ResourceRepositoryImpl_jpa implements ResourceRepository
{
	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public Long createResource(Resource resource)
	{
		entityManager.persist(resource);
		return resource.getResourceId();
	}

	public List<Resource> findAllResources()
	{
		Query query = entityManager.createQuery("select r from Resource r");
		@SuppressWarnings("unchecked")
		List<Resource> resources = query.getResultList();

		return resources;
	}

	public Long updateResource(Long resourceId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Long cancelResource(Long resourceId)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
