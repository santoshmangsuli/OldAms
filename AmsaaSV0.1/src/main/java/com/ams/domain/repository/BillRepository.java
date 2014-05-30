package com.ams.domain.repository;

import java.util.List;
import java.util.Set;

import com.ams.domain.model.bill.Bill;
import com.ams.domain.model.bill.Payment;

public interface BillRepository
{

	public Bill createBill(Bill bill);

	public long updateBill(Bill bill);

	public long deleteBill(long billNumber);

	public void createBills(List<Bill> bills);

	public void updateBills(Set<Bill> bills);

	public void deleteBills(Set<Bill> bills);

	public Bill findBill(long billNumber);

	public Set<Bill> findBills(long userId);

	public Payment findPayment(long paymntNumber);

	public Set<Payment> findPayments(long billNumber);

	public List<Bill> findBillsByPaymentStatus();

}
