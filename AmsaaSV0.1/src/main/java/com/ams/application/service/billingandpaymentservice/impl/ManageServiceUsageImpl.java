package com.ams.application.service.billingandpaymentservice.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.common.ServiceException;
import com.ams.application.service.billingandpaymentservice.ManageServiceUsage;
import com.ams.domain.model.measureandunits.Period;
import com.ams.domain.model.person.Person;
import com.ams.domain.model.service.Service;
import com.ams.domain.model.service.ServiceCharge;
import com.ams.domain.model.service.ServiceRate;
import com.ams.domain.model.service.ServiceSubscription.Status;
import com.ams.domain.model.service.ServiceUsage;
import com.ams.domain.repository.PersonRepository;
import com.ams.domain.repository.ServiceRepository;

@Transactional
@org.springframework.stereotype.Service("ManageServiceUsage")
public class ManageServiceUsageImpl implements ManageServiceUsage
{

	@Autowired
	private PersonRepository		personRepository;

	@Autowired
	private ServiceRepository	serviceRepository;

	@Transactional(propagation = Propagation.MANDATORY,isolation = Isolation.REPEATABLE_READ)
	public void registerServiceUsage(long persnId, String srvcCode, Period duration) throws ServiceException
	{

		Person persn = personRepository.findById(persnId);
		Service srvc = serviceRepository.findById(srvcCode);
		ServiceUsage svcUsage = new ServiceUsage(persn, srvc, duration);
		if (persn.getPersnServiceProfile().isSrvcSubcrptnStatus(Status.ACTIVE))
		{
			/*
			 * Its better to avoid train-wreck coding like below.
			 */
			// findServiceChargeRate(srvc)
			Set<ServiceRate> svcRateSet =
					persn.getPersnServiceProfile().getSubscribedSrvcsPlan().getServiceRateSet();
			Iterator<ServiceRate> serviceRateSet = svcRateSet.iterator();
			ServiceRate serviceRate = null;
			while (serviceRateSet.hasNext())
			{
				serviceRate = serviceRateSet.next();
				serviceRate.getService().getSrvcCode().equals(srvc);
				break;

			}
			// service
			if (serviceRate == null)
			{
				// Find the service rate from generic service plan
			}

			ServiceCharge svcCharge = new ServiceCharge();
			svcCharge.forUsage(svcUsage, serviceRate, new Date());
			serviceRepository.createServiceCharge(svcCharge);
		}
		else
		{
			throw new ServiceException("User Service Profile isn't ACTIVE!!");
		}

	}

	public void calculateRecurringServiceCharges(long userId, Period billPeriod) throws ServiceException
	{
		/*
		 * Person chargedPersn = personRepository.findById(userId);
		 * ServiceCharge serviceCharge; if
		 * (chargedPersn.getPersnServiceProfile
		 * ().isSrvcProfileStatus(Status.ACTIVE)) {
		 * 
		 * Its better to avoid train-wreck(Law of demeter) coding like below.
		 * 
		 * for (Map<Service, Set<ChargeComponent>> srvcChargeRate :
		 * chargedPersn.getPersnServiceProfile().getSubscribedSrvcsPlan().
		 * getSrvcChargeRateList()) { if
		 * (srvcChargeRate.getChargeType().equals
		 * (ServiceChargeType.RECURRING)) { serviceCharge = new
		 * ServiceCharge(); serviceCharge.forPeriod(chargedPersn, billPeriod,
		 * srvcChargeRate, new Date()); } } } else { throw new
		 * ServiceException("User Service Profile isn't ACTIVE!!"); }
		 */

	}

}
