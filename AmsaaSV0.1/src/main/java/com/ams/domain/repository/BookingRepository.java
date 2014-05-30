package com.ams.domain.repository;

import java.util.Date;
import java.util.List;

import com.ams.domain.model.booking.Booking;

public interface BookingRepository
{
	Long createBooking(Booking booking);

	List<Booking> findAllBookingsOfResource(String resourceName, Date startDateTime, Date endDateTime);

	List<Booking> findAllOverlapBookingsOfResource(Long resourceId, Date startDateTime, Date endDateTime);

	Long updateBooking(Long bookingId, Date startDateTime, Date endDateTime);

	Long cancelBooking(Long bookingId);

}
