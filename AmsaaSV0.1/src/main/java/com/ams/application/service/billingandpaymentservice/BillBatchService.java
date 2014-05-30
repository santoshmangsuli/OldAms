package com.ams.application.service.billingandpaymentservice;

import java.util.Collection;

import com.ams.domain.model.bill.Bill;

public interface BillBatchService
{
	public Collection<Bill> fetchBills();

	public void fetchSubs();
}
