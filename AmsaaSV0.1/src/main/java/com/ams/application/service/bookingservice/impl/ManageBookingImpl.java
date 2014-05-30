package com.ams.application.service.bookingservice.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.common.ServiceException;
import com.ams.application.service.bookingservice.ManageBooking;
import com.ams.domain.model.booking.Booking;
import com.ams.domain.repository.BookingRepository;

@Service
@Transactional
public class ManageBookingImpl implements ManageBooking
{
	@Autowired
	private BookingRepository	bookingRepository;

	public Long newBooking(Booking booking) throws ServiceException
	{

		System.out.println(" booking.getAllDay()" + booking.getAllDay());
		Date startDateTime = booking.getStartDateTime();
		Date endDateTime = booking.getEndDateTime();
		if (booking.getAllDay().equals("true"))
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDateTime);
			cal.set(Calendar.HOUR_OF_DAY, 8);
			cal.set(Calendar.MINUTE, 0);
			startDateTime = cal.getTime();
			booking.setStartDateTime(startDateTime);
			cal.setTime(endDateTime);
			cal.set(Calendar.HOUR_OF_DAY, 20);
			cal.set(Calendar.MINUTE, 0);
			endDateTime = cal.getTime();
			booking.setEndDateTime(endDateTime);
		}

		List<Booking> bookings = bookingRepository.findAllOverlapBookingsOfResource(booking.getBookedResource().getResourceId(),
																		startDateTime, endDateTime);
		System.out.println(" bookings " + bookings.size());
		if (bookings.size() > 0)
		{
			throw new ServiceException("bookings exist");
		}
		else
		{
			return bookingRepository.createBooking(booking);
		}
	}

	public List<Booking> findAllBookingsOfResource(String resourceName, Date startDateTime, Date endDateTime)
	{
		// TODO Auto-generated method stub
		return bookingRepository.findAllBookingsOfResource(resourceName, startDateTime, endDateTime);
	}

	public Long updateBooking(Long bookingId, Date startDateTime, Date endDateTime)
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Long cancelBooking(Long bookingId)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
