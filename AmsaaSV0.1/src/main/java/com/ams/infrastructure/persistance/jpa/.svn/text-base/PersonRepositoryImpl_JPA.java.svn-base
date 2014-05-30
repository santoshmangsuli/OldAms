package com.ams.infrastructure.persistance.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.domain.model.person.Person;
import com.ams.domain.repository.PersonRepository;

@Repository("PersonRepository")
public class PersonRepositoryImpl_JPA implements PersonRepository
{
	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public Long createOrUpdate(Person persn)
	{
		if (persn.getPersnId() == null)
		{
			entityManager.persist(persn);
		}
		else
		{
			entityManager.merge(persn);
		}

		return persn.getPersnId();
	}

	public Long create(Person person)
	{
		entityManager.persist(person);
		return person.getPersnId();
	}

	public Long update(Person person)
	{
		entityManager.merge(person);
		return person.getPersnId();
	}

	public void delete(Long personId)
	{
		Person p = entityManager.find(Person.class, personId);
		entityManager.remove(p);
	}

	public Person findById(Long personId)
	{
		return entityManager.find(Person.class, personId);
	}

	public List<Person> findAll()
	{
		TypedQuery<Person> query = entityManager.createQuery(
													"select P from Person P",
													Person.class);
		return query.getResultList();
	}

	public List<Person> findAllByIds(long[] userIds)
	{
		return null;
	}

}
