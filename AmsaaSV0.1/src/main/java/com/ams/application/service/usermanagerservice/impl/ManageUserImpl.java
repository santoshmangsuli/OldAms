package com.ams.application.service.usermanagerservice.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.service.usermanagerservice.ManageUser;
import com.ams.domain.model.person.Person;
import com.ams.domain.repository.PersonRepository;

@Transactional
@Service("ManageUserService")
public class ManageUserImpl implements ManageUser
{

	@Autowired
	private PersonRepository	personRepository;

	@Transactional(propagation = Propagation.REQUIRED)
	public void registerUser(Person user)
	{
		personRepository.createOrUpdate(user);

	}

	// ,isolation = Isolation.REPEATABLE_READ
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUserDetails(Person user)
	{
		System.out.println("[updateUserDetails] PersnId :" + user.getPersnId());
		personRepository.createOrUpdate(user);

	}

	public void deleteUser(long userId)
	{
		personRepository.delete(userId);

	}

	public Person getUserDetails(long userId)
	{
		Person user = personRepository.findById(userId);
		return user;
	}

	public List<Person> getAllUsers()
	{
		List<Person> users = personRepository.findAll();
		return users;

	}

}
