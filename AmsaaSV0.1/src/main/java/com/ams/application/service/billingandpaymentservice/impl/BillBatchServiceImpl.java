package com.ams.application.service.billingandpaymentservice.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Weeks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ams.application.service.billingandpaymentservice.BillBatchService;
import com.ams.application.service.billingandpaymentservice.ManageBill;
import com.ams.application.service.billingandpaymentservice.servicedata.BillDTO;
import com.ams.domain.model.bill.Bill;
import com.ams.domain.model.bill.BillItem;
import com.ams.domain.model.measureandunits.Frequency;
import com.ams.domain.model.measureandunits.Unit;
import com.ams.domain.model.service.ChargeComponent;
import com.ams.domain.model.service.ServicePlan;
import com.ams.domain.model.service.ServiceRate;
import com.ams.domain.model.service.ServiceSubscription;
import com.ams.domain.repository.BillRepository;
import com.ams.domain.repository.ServiceRepository;

@Transactional
@org.springframework.stereotype.Service("BillBatchService")
public class BillBatchServiceImpl implements BillBatchService
{

	private final int			noOfDaysBillAdv	= 10;
	@Autowired
	private BillRepository		billRepository;

	@Autowired
	private ManageBill			manageBill;

	@Autowired
	private ServiceRepository	serviceRepository;

	public Collection<Bill> fetchBills()
	{

		List<Bill> billLst = billRepository.findBillsByPaymentStatus();
		System.out.println(billLst.size());
		return null;
	}

	public void fetchSubs()
	{
		Date billDate = new Date();
		BillDTO billDTO = null;
		List<ServiceSubscription> serviceSubList =
				serviceRepository.findServiceSubscription(billDate);
		Iterator<ServiceSubscription> serviceSubscriptionItr =
				serviceSubList.iterator();

		List<BillItem> billItemLst = new ArrayList<BillItem>();
		while (serviceSubscriptionItr.hasNext())
		{
			ServiceSubscription serviceSubscription = serviceSubscriptionItr.next();
			billDTO = new BillDTO();

			generateBillItemForServicePlan(billItemLst, serviceSubscription);

			billDTO.setBillLineItems(billItemLst);
			billDTO.setCustomerId(serviceSubscription.getSrvcSubcrptnOfPerson().getPersnId());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(billDate);
			calendar.add(Calendar.DATE, noOfDaysBillAdv);
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			billDTO.setBillDueDate(formatter.format(calendar.getTime()));
			billDTO.setBillDate(formatter.format(billDate));
			manageBill.createNewBill(billDTO);
			System.out.println(serviceSubscription.
											getSrvcSubcrptnOfPerson().getPersnFirstName()
							+ " Has subscribed for " + serviceSubscription.getSrvcSubcrptnName()
							/*
							 * + " with plan " +
							 * serviceplan.getSrvcPlanName()
							 */);
		}

	}

	private void generateBillItemForServicePlan(List<BillItem> billItemLst,
											ServiceSubscription serviceSubscription)
	{
		ServicePlan serviceplan = serviceSubscription.getSubscribedSrvcsPlan();
		Set<ServiceRate> serviceRateSet = serviceplan.getServiceRateSet();
		Iterator<ServiceRate> serviceRateItr = serviceRateSet.iterator();

		BillItem billItem = null;
		while (serviceRateItr.hasNext())
		{
			ServiceRate serviceRate = serviceRateItr.next();
			ChargeComponent chargeComponent = serviceRate.getSrvcChargeComponent();
			if (chargeComponent.getChargeFrequency().equals(Frequency.MONTHLY))
			{
				billItem = new BillItem();
				billItem.setBillItemAmount(chargeComponent.getChargeRate().getPrice().getAmount());
				billItem.setBillItemQuantity(1);
				billItem.setBillItemService(serviceRate.getService());

			}
			else if (chargeComponent.getChargeFrequency().equals(Frequency.WEEKLY))
			{
				billItem = new BillItem();
				billItem.
						setBillItemAmount(
									chargeComponent.getChargeRate().
												getPrice().getAmount().
												multiply(
														calculateQuantity(
																		serviceSubscription.getSrvcSubcrptnStartDate(),
																		serviceSubscription.getSrvcSubcrptnendDate(),
																		chargeComponent.getChargeRate().getPerUnit())));
			}
			else if (chargeComponent.getChargeFrequency().equals(Frequency.DAILY))
			{
				billItem = new BillItem();
				billItem.
						setBillItemAmount(
						chargeComponent.getChargeRate().
									getPrice().getAmount().
										multiply(
												calculateQuantity(
																serviceSubscription.getSrvcSubcrptnStartDate(),
																serviceSubscription.getSrvcSubcrptnendDate(),
																chargeComponent.getChargeRate().getPerUnit()))
														);

			}

			if (billItem != null)
			{
				billItemLst.add(billItem);
			}
		}

	}

	private BigDecimal calculateQuantity(final Date startDate, final Date endDate, final Unit unit)
	{
		DateTime startDateJ = new DateTime(startDate);
		DateTime endDateJ = new DateTime(endDate);

		int quantity = 0;
		if (startDateJ.isBefore(endDateJ))
		{
			if (unit.equals(Unit.Weeks))
			{
				quantity = Weeks.weeksBetween(startDateJ, endDateJ).getWeeks();
			}
			else if (unit.equals(Unit.Days))
			{
				quantity = Days.daysBetween(startDateJ, endDateJ).getDays();
			}

		}

		return quantity == 0 ? BigDecimal.ZERO : (quantity > 0 ? new BigDecimal(quantity) : null);
	}

}
