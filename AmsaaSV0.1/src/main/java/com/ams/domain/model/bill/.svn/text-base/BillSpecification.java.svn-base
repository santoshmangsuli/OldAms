package com.ams.domain.model.bill;

import java.math.BigDecimal;

public class BillSpecification
{
	protected static final BigDecimal	billDuePenaltyAmount	= new BigDecimal(50);
	public static String			sourceEmailId			= "ams@xyz.com";

	public enum status
	{
		PAID, UNPAID, PARTIALLY_PAID
	}

	public static boolean isPaymentWithinDueDate(Bill bill, Payment payment)
	{
		return payment.getPaymntDate().before(bill.getBillDueDate());
	}

}
