package com.ams.application.service.bookingservice;

import java.util.Date;
import java.util.List;

import com.ams.application.common.ServiceException;
import com.ams.domain.model.booking.Booking;

public interface ManageBooking
{

	Long newBooking(Booking booking) throws ServiceException;

	List<Booking> findAllBookingsOfResource(String resourceName, Date startDateTime, Date endDateTime);

	Long updateBooking(Long bookingId, Date startDateTime, Date endDateTime);

	Long cancelBooking(Long bookingId);

}
