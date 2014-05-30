package com.ams.domain.model.bill;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ams.domain.model.bill.BillSpecification.status;

@Embeddable
public class BillPaymentRegister implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private status				billPaymentStatus;
	private BigDecimal			billAmountPaid;
	private BigDecimal			billRemainingAmount;
	private boolean			isDuePenaltyApplied;
	private BigDecimal			billPenaltyAmount;

	public BillPaymentRegister()
	{
		this.billPaymentStatus = status.UNPAID;
		this.billAmountPaid = new BigDecimal(0);
		this.billRemainingAmount = new BigDecimal(0);
		this.isDuePenaltyApplied = false;
		this.billPenaltyAmount = new BigDecimal(0);
	}

	/*
	 * BILL DOMAIN FUNCTIONS
	 */

	protected void updateBillPayment(Bill bill, Payment paymnt)
	{

		if (!BillSpecification.isPaymentWithinDueDate(bill, paymnt)
				&& !isDuePenaltyApplied)
		{
			isDuePenaltyApplied = true;
			billPenaltyAmount = BillSpecification.billDuePenaltyAmount;
			bill.setBillNote("Late payment penalty amount "
					+ BillSpecification.billDuePenaltyAmount + " applied");
		}

		this.billAmountPaid = this.billAmountPaid.add(paymnt.getPaymntAmount());
		this.billRemainingAmount = bill.getBillTotalAmount()
									.subtract(this.billAmountPaid).add(billPenaltyAmount);

		paymnt.setPaymntBalance(this.billRemainingAmount);
		updateBillPaymentStatus(bill);
		bill.getBillPayments().add(paymnt);
	}

	protected void updateBillPaymentStatus(Bill bill)
	{
		if (this.billRemainingAmount.compareTo(BigDecimal.ZERO) <= 0)
		{
			billPaymentStatus = status.PAID;
		}

		else if ((this.billRemainingAmount.compareTo(BigDecimal.ZERO) > 0)
				&& (this.billRemainingAmount
										.compareTo(bill.getBillTotalAmount()) < 0))
		{
			billPaymentStatus = status.PARTIALLY_PAID;
		}

		else if (this.billRemainingAmount.compareTo(bill.getBillTotalAmount()) <= 0)
		{
			billPaymentStatus = status.UNPAID;
		}
	}

	/*
	 * ACCESSORS AND MUTATORS
	 */

	@Enumerated(EnumType.STRING)
	public status getBillPaymentStatus()
	{
		return this.billPaymentStatus;
	}

	public void setBillPaymentStatus(status billPaymentStatus)
	{
		this.billPaymentStatus = billPaymentStatus;
	}

	public BigDecimal getBillAmountPaid()
	{
		return this.billAmountPaid;
	}

	public void setBillAmountPaid(BigDecimal billAmountPaid)
	{
		this.billAmountPaid = billAmountPaid;
	}

	public BigDecimal getBillRemainingAmount()
	{
		return this.billRemainingAmount;
	}

	public void setBillRemainingAmount(BigDecimal billRemainingAmount)
	{
		this.billRemainingAmount = billRemainingAmount;
	}

	public boolean isDuePenaltyApplied()
	{
		return this.isDuePenaltyApplied;
	}

	public void setDuePenaltyApplied(boolean isDuePenaltyApplied)
	{
		this.isDuePenaltyApplied = isDuePenaltyApplied;
	}

	public BigDecimal getBillPenaltyAmount()
	{
		return this.billPenaltyAmount;
	}

	public void setBillPenaltyAmount(BigDecimal penaltyAmount)
	{
		this.billPenaltyAmount = penaltyAmount;
	}

}
