package com.ams.domain.model.booking;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.ams.domain.model.person.Person;

@Entity
public class Booking
{
	private long		bookingId;
	private String		bookingTitle;
	private Person		bookingPerson;
	private Resource	bookedResource;
	private Date		startDateTime;
	private Date		endDateTime;
	private String		allDay;

	private Booking()
	{}

	public Booking(Person person, String title, Resource resource, Date startDateTime2,
				Date endDateTime2)
	{
		this.bookingTitle = title;
		this.bookingPerson = person;
		this.bookedResource = resource;
		this.startDateTime = startDateTime2;
		this.endDateTime = endDateTime2;

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getBookingId()
	{
		return bookingId;
	}

	public void setBookingId(long bookingId)
	{
		this.bookingId = bookingId;
	}

	@ManyToOne
	public Person getBookingPerson()
	{
		return bookingPerson;
	}

	public void setBookingPerson(Person bookingPerson)
	{
		this.bookingPerson = bookingPerson;
	}

	@ManyToOne
	public Resource getBookedResource()
	{
		return bookedResource;
	}

	public void setBookedResource(Resource bookedResource)
	{
		this.bookedResource = bookedResource;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartDateTime()
	{
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getEndDateTime()
	{
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime)
	{
		this.endDateTime = endDateTime;
	}

	@Transient
	public String getAllDay()
	{
		return allDay;
	}

	public void setAllDay(String allDay)
	{
		this.allDay = allDay;
	}

	public void setBookingTitle(String bookingTitle)
	{
		this.bookingTitle = bookingTitle;
	}

	public String getBookingTitle()
	{
		return bookingTitle;
	}
}
