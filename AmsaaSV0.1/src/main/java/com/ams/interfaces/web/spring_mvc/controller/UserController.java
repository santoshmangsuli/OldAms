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
import com.ams.application.service.usermanagerservice.ManageUser;
import com.ams.domain.model.person.Person;

@Controller
public class UserController
{
	@Autowired
	private ManageUser	manageUser;

	@RequestMapping("users")
	@ResponseBody
	public List<Person> getUsersList() throws ServiceException
	{
		return manageUser.getAllUsers();
	}

	@RequestMapping(value = "user/{userId}",method = RequestMethod.POST)
	@ResponseBody
	public Person getUserDetail(@PathVariable Long userId) throws
												ServiceException
	{

		return manageUser.getUserDetails(userId);

	}

	@RequestMapping(value = "user",method = RequestMethod.POST)
	@ResponseBody
	public String saveUser(@RequestBody final Person user) throws ServiceException
	{
		try
		{
			System.out.println(user.getPersnFirstName());
			manageUser.registerUser(user);
			return "SUCCESS";
		} catch (Exception e)
		{
			throw new ServiceException(e.toString());
		}

	}

	@RequestMapping(value = "user",method = RequestMethod.PUT)
	@ResponseBody
	public String updateUseretail(@RequestBody final Person user) throws ServiceException
	{
		System.out.println(user.getPersnFirstName());
		manageUser.updateUserDetails(user);
		return "SUCCESS";

	}

	@RequestMapping(value = "user/{userId}",method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable final Long userId) throws ServiceException
	{
		System.out.println(userId);
		manageUser.deleteUser(userId);
		return "SUCCESS";

	}

}
