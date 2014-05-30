package com.ams.application.service.usermanagerservice;

import java.util.List;

import com.ams.application.common.ServiceException;
import com.ams.domain.model.person.Person;

public interface ManageUser
{
	public void registerUser(Person user) throws ServiceException;

	public void updateUserDetails(Person user) throws ServiceException;

	public void deleteUser(long userId) throws ServiceException;

	public Person getUserDetails(long userId) throws ServiceException;

	public List<Person> getAllUsers() throws ServiceException;

}
