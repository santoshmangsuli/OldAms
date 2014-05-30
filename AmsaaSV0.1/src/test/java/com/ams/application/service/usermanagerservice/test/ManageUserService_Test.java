package com.ams.application.service.usermanagerservice.test;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ams.application.service.usermanagerservice.ManageUser;
import com.ams.domain.model.person.Address;
import com.ams.domain.model.person.Person;
import com.ams.domain.model.person.PersonDetail;

public class ManageUserService_Test
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
														"com/ams/infrastructure/configuration/ApplicationContext.xml");

		ManageUser mus = (ManageUser) ctx
									.getBean("ManageUserService");

		Person p = getPerson();
		try
		{
			mus.registerUser(p);
		} catch (com.ams.application.common.ServiceException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// mus.deleteUser(7);
		// Assert.assertEquals(null, mus.getUserDetails(4));

		/*
		 * Person person=new Person(); person=mus.getUserDetails(3L);
		 * System.out.println("First Name:"+person.getPersnFirstName());
		 * System.out.println("Last Name:"+person.getPersnLastName());
		 * 
		 * person.setPersnLastName("B"); mus.updateUserDetails(person);
		 * System.out
		 * .println("Last Name:"+mus.getUserDetails(1).getPersnLastName());
		 * 
		 * 
		 * 
		 * ArrayList<Person> ps; ps = (ArrayList<Person>) mus.getAllUsers();
		 * Iterator<Person> i = ps.iterator();
		 * 
		 * while (i.hasNext()) { Person per = i.next();
		 * System.out.println(per.getPersnId()); }
		 */

	}

	public static Person getPerson()
	{
		Person p = new Person();
		p.setPersnFirstName("Chin");
		p.setPersnLastName("Panchal");

		PersonDetail pd = new PersonDetail();
		pd.setEmailId("Chin-P@gmail.com");
		pd.setLandLineNumber("080-23226255");
		pd.setMobileNumber("9886435173");
		p.setPersnDetail(pd);

		Address address = new Address();
		address.setApartmentName("Lake View Apartments");
		address.setFlatNumber(1);
		address.setStreet("Scenic Street");
		address.setLocality("Lake View Nagar");
		address.setLandMark("Behind XYZ School");
		address.setCity("Bangalore");
		address.setDistrict("Bangalore");
		address.setState("Karnataka");
		address.setPin(560079);
		p.setPersnAddress(address);
		Collection<String> roles = new ArrayList<String>();

		roles.add("User");
		roles.add("Flat_Resident");

		p.setPersnRoles(roles);
		return p;
	}

}
