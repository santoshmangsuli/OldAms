package com.ams.application.service.billingandpaymentservice;

import java.util.List;

import com.ams.application.service.billingandpaymentservice.servicedata.BillDTO;
import com.ams.domain.model.bill.Bill;
import com.ams.domain.model.bill.Payment;

public interface ManageBill
{
	BillDTO addBillItems(BillDTO billSrvcData);

	BillDTO createNewBill(BillDTO billSrvcData);

	void deleteBill(long billNumber);

	void payBill(Payment paymnt);

	List<Bill> getUnpaidBills();

}
