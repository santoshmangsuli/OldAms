package com.ams.infrastructure.persistance.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ams.domain.model.person.Person;
import com.ams.domain.repository.PersonRepository;

public class PersonRepositoryImpl_Hibernate implements PersonRepository
{

	private SessionFactory	sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	public SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public Long createOrUpdate(Person persn)
	{
		this.sessionFactory.getCurrentSession().saveOrUpdate(persn);
		return persn.getPersnId();
	}

	public void delete(Long persnId)
	{

		this.sessionFactory.getCurrentSession().delete(
												this.sessionFactory
																.getCurrentSession()
																.get(Person.class,
																	persnId));

	}

	public Person findById(Long persnId)
	{
		Person p = (Person) this.sessionFactory.getCurrentSession().get(
															Person.class, persnId);

		return p;
	}

	@SuppressWarnings("unchecked")
	public List<Person> findAll()
	{
		Query hibQuery = this.sessionFactory
										.getCurrentSession()
										.createQuery(
													"from Person as p ");
		return hibQuery.list();

	}

	public List<Person> findAllByIds(long[] userIds)
	{

		return null;
	}

}
