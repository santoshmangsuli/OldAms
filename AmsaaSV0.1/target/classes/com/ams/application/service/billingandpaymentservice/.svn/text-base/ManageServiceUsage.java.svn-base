package com.ams.application.service.billingandpaymentservice;

import com.ams.application.common.ServiceException;
import com.ams.domain.model.measureandunits.Period;

public interface ManageServiceUsage
{

	void registerServiceUsage(long userId, String srvcCode, Period duration) throws ServiceException;

	void calculateRecurringServiceCharges(long userId, Period billPeriod) throws ServiceException;

}
