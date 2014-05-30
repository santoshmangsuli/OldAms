package com.ams.application.service.bookingservice.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.common.ServiceException;
import com.ams.application.service.bookingservice.ManageResource;
import com.ams.domain.model.booking.Resource;
import com.ams.domain.repository.ResourceRepository;

@Transactional
@Service("ManageResourceService")
public class ManageResourceImpl implements ManageResource
{
	@Autowired
	private ResourceRepository	resourceRepository;

	public Long newResource(Long resourceId, String resourceName) throws ServiceException
	{

		List<Resource> resources = resourceRepository.
											findAllResources();
		if (resources.size() > 0)
		{
			throw new ServiceException("");
		}
		else
		{
			Resource resource = new Resource();
			resource.setResourceId(resourceId);
			resource.setResourceName(resourceName);

			return resourceRepository.createResource(resource);
		}
	}

	public List<Resource> findAllResources()
	{
		// TODO Auto-generated method stub
		return resourceRepository.findAllResources();
	}

	public Long updateResource(Long bookingId, Date startDateTime, Date endDateTime)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Long cancelResource(Long bookingId)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Long updateResource(Long resourceId)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
