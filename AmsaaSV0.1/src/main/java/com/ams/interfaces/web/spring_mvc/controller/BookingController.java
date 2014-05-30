package com.ams.interfaces.web.spring_mvc.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ams.application.common.ServiceException;
import com.ams.application.service.bookingservice.ManageBooking;
import com.ams.domain.model.booking.Booking;

@Controller
public class BookingController
{
	@Autowired
	private ManageBooking	manageBooking;

	@RequestMapping("bookings/{resourceName}")
	@ResponseBody
	public List<Booking> getBookingListForResource(@PathVariable String resourceName) throws ServiceException
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		Date today = cal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH));
		Date tomm = cal.getTime();
		System.out.println("stadt date;" + today);
		return manageBooking.findAllBookingsOfResource(resourceName, today, tomm);
	}

	@RequestMapping(value = "bookings",method = RequestMethod.POST)
	@ResponseBody
	public long saveBooking(@RequestBody final Booking booking) throws ServiceException
	{

		return manageBooking.newBooking(booking);

	}
}
