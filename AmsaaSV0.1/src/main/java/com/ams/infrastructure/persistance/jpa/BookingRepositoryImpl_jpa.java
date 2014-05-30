package com.ams.infrastructure.persistance.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ams.domain.model.booking.Booking;
import com.ams.domain.repository.BookingRepository;

@Repository("BookingRepository")
public class BookingRepositoryImpl_jpa implements BookingRepository
{
	@Autowired
	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager	entityManager;

	public Long createBooking(Booking booking)
	{
		System.out.println("StartDateTime " + booking.getStartDateTime() + " EndDateTime( " + booking.getEndDateTime());
		entityManager.persist(booking);
		return booking.getBookingId();
	}

	public List<Booking> findAllBookingsOfResource(String resourceName, Date startDateTime, Date endDateTime)
	{
		Query query = entityManager.createQuery("select b from Booking b where b.bookedResource.resourceName=:rname and b.startDateTime>=:sDate and b.endDateTime<=:eDate");
		query.setParameter("rname", resourceName);
		query.setParameter("sDate", startDateTime);
		query.setParameter("eDate", endDateTime);
		@SuppressWarnings("unchecked")
		List<Booking> bookings = query.getResultList();

		return bookings;
	}

	public List<Booking> findAllOverlapBookingsOfResource(Long resourceId, Date startDateTime, Date endDateTime)
	{
		/*
		 * CriteriaBuilder cb = entityManager.getCriteriaBuilder(); final
		 * CriteriaQuery criteriaQuery = cb.createQuery(Booking.class); final
		 * Root BookingRoot = criteriaQuery.from(Booking.class); final Root
		 * ResourceRoot = criteriaQuery.from(Resource.class); Expression<Date>
		 * nameParam1 = cb.parameter(Date.class); Expression<Date> nameParam2
		 * = cb.parameter(Date.class);
		 */
		System.out.println("startDateTime " + startDateTime + "  " +
						"endDateTime " + endDateTime
						);
		System.out.println("resourceId " + resourceId);
		// Query query =
		// entityManager.createQuery("select b from Booking b where b.bookedResource.resourceId=:rId and b.startDateTime>=:sDate and b.endDateTime<=:eDate");
		Query query = entityManager.createQuery("select b from Booking b where b.bookedResource.resourceId=:rId and (" +
				"(:sDate between b.startDateTime and b.endDateTime or :eDate between b.startDateTime and b.endDateTime ) or" +
				"(b.startDateTime between :sDate and :eDate  or b.endDateTime between :sDate and :eDate ))");
		query.setParameter("rId", resourceId);
		query.setParameter("sDate", startDateTime, TemporalType.TIMESTAMP);
		query.setParameter("eDate", endDateTime, TemporalType.TIMESTAMP);
		@SuppressWarnings("unchecked")
		List<Booking> bookings = query.getResultList();

		return bookings;
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
