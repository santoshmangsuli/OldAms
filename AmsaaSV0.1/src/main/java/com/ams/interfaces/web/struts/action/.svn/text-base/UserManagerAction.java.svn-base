package com.ams.interfaces.web.struts.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.ams.application.common.ServiceException;
import com.ams.application.service.usermanagerservice.ManageUser;
import com.ams.domain.model.person.Person;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("json-default")
@Namespace("/")
public class UserManagerAction extends ActionSupport
{

	private static final long	serialVersionUID	= 9086072862754088699L;

	@Autowired
	private ManageUser			manageUserService;
	private Person				user				= new Person();
	private ArrayList<Person>	users;

	@JSON(serialize = true)
	public Person getUser()
	{
		return this.user;
	}

	@Action(value = "/getUserDetailAction",results = { @Result(name = "success",type = "json",
													params = { "includeProperties",
															"user" }) })
	public String getUserDetail()
	{
		long id = user.getPersnId();
		Person persn = new Person();
		try
		{
			persn = (manageUserService.getUserDetails(id));
			System.out.println("From Service Person Name is:"
					+ persn.getPersnFirstName());
		} catch (Exception e)
		{
			System.out.println("Exception thrown & personId is:"
					+ user.getPersnId());
			user.setPersnId(id);
			e.printStackTrace();
			return "error";
		}

		finally
		{
			user = persn;

		}
		return "success";
	}

	@JSON(serialize = true)
	public List<Person> getUsers()
	{
		return users;
	}

	@Action(
			value = "/getUsersListAction",
			results = { @Result(
							name = "success",
							type = "json",
							params = {
									"includeProperties",
									"users\\[\\d+\\]\\.persnId,users\\[\\d+\\]\\.persnFirstName,users\\[\\d+\\]\\.persnLastName,users\\[\\d+\\]\\.persnDetail.*,users\\[\\d+\\]\\.persnAddress.*" }) })
	public String getUsersList()
	{
		try
		{
			users = (ArrayList<Person>) manageUserService.getAllUsers();
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@Action(value = "/registerUserAction",results = { @Result(name = "success",type = "json") })
	public String registerUser()
	{
		try
		{
			manageUserService.registerUser(user);
		} catch (ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

	@Action(value = "/removeUserAction",results = { @Result(name = "success",type = "json",params = {
			"includeProperties",
			"users\\[\\d+\\]\\.persnId,users\\[\\d+\\]\\.persnFirstName,users\\[\\d+\\]\\.persnLastName,users\\[\\d+\\]\\.persnDetail.*,users\\[\\d+\\]\\.persnAddress.*" }) })
	public String removeUser()
	{
		try
		{
			manageUserService.deleteUser(user.getPersnId());

		} catch (ServiceException e)
		{
			e.printStackTrace();
		}
		return "success";
	}

	public void setUser(Person arg)
	{
		this.user = arg;
	}

	public void setUsers(ArrayList<Person> users)
	{
		this.users = users;
	}

	@Action(value = "/updateUserAction",results = { @Result(name = "success",type = "json") })
	public String updateUserDetails()
	{
		try
		{
			manageUserService.updateUserDetails(user);
		} catch (ServiceException e)
		{
			e.printStackTrace();
		}
		return "success";
	}

}
