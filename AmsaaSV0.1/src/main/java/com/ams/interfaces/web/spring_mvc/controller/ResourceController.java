package com.ams.interfaces.web.spring_mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.application.common.ServiceException;
import com.ams.application.service.bookingservice.ManageResource;
import com.ams.domain.model.booking.Resource;
import com.ams.domain.model.person.Person;

@Controller
public class ResourceController
{
	@Autowired
	private ManageResource	manageResource;

	@RequestMapping("resources")
	@ResponseBody
	public List<Resource> getResourcesList() throws ServiceException
	{
		return manageResource.findAllResources();
	}

	@RequestMapping(value = "resource/{resourceId}",method = RequestMethod.POST)
	@ResponseBody
	public Person getUserDetail(@PathVariable Long resourceId) throws
													ServiceException
	{

		// return manageResource.getUserDetails(resourceId);
		return null;

	}

	@RequestMapping(value = "resource",method = RequestMethod.POST)
	@ResponseBody
	public long saveResource(@RequestBody final Resource resource) throws ServiceException
	{
		try
		{
			return manageResource.newResource(resource.getResourceId(), resource.getResourceName());

		} catch (Exception e)
		{
			throw new ServiceException(e.toString());
		}

	}

	@RequestMapping(value = "resource",method = RequestMethod.PUT)
	@ResponseBody
	public String updateUseretail(@RequestBody final Resource resource) throws ServiceException
	{

		// manageUser.updateUserDetails(user);
		return "SUCCESS";

	}

	@RequestMapping(value = "resource/{resourceId}",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable final Long resourceId) throws ServiceException
	{
		// System.out.println(userId);
		// manageResource.deleteUser(userId);
		return "SUCCESS";

	}

}
