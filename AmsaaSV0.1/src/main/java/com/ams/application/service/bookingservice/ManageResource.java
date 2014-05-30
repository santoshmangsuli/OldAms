package com.ams.application.service.bookingservice;

import java.util.List;

import com.ams.application.common.ServiceException;
import com.ams.domain.model.booking.Resource;

public interface ManageResource
{

	Long newResource(Long resourceId, String resourceName) throws ServiceException;

	List<Resource> findAllResources();

	Long updateResource(Long resourceId);

	Long cancelResource(Long resourceId);

}
