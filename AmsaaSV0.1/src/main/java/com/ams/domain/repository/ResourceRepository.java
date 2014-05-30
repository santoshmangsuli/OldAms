package com.ams.domain.repository;

import java.util.List;

import com.ams.domain.model.booking.Resource;

public interface ResourceRepository
{
	Long createResource(Resource resource);

	List<Resource> findAllResources();

	Long updateResource(Long resourceId);

	Long cancelResource(Long resourceId);

}
