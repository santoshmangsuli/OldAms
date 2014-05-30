package com.ams.application.service.billingandpaymentservice.assembler;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.ams.application.service.billingandpaymentservice.servicedata.BillDTO;
import com.ams.domain.model.bill.Bill;
import com.ams.domain.model.bill.BillItem;

public class BillServiceDataAssembler
{

	public BillDTO toBillDTO(Bill bill)
	{
		BillDTO bsd = new BillDTO();
		if (bill != null)
		{
			bsd.setBillNumber(bill.getBillNumber());
			if (bill.getBilledPerson() != null)
			{
				bsd.setCustomerId(bill.getBilledPerson().getPersnId());
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			bsd.setBillDate(formatter.format(bill.getBillDate()));
			bsd.setBillDueDate(formatter.format(bill.getBillDueDate()));
			bsd.setBillPeriodFromDate(formatter.format(bill.getBillPeriod().getFromDate()));
			bsd.setBillPeriodToDate(formatter.format(bill.getBillPeriod().getToDate()));
			bsd.setBillTotalTax(bill.getBillTotalTax().doubleValue());
			bsd.setBillTotalAmount(bill.getBillTotalAmount().doubleValue());
			Map<String, Long> bitem = new HashMap<String, Long>();
			for (BillItem bi : bill.getBillItems())
			{
				bitem.put(bi.getBillItemService().getSrvcCode(), bi.getBillItemQuantity());
				bsd.getBillLineItems().add(bi);
			}
		}

		return bsd;

	}

	public Bill toBill(BillDTO bsd)
	{
		return null;

	}

}
