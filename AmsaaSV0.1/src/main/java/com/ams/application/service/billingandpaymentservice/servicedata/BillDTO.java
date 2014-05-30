package com.ams.application.service.billingandpaymentservice.servicedata;

import java.util.ArrayList;
import java.util.List;

import com.ams.domain.model.bill.BillItem;

public class BillDTO
{
	private String			billDate;
	private String			billDueDate;
	private String			billPeriodFromDate;
	private String			billPeriodToDate;
	private double			billTotalTax		= 0.0;
	private double			billTotalAmount	= 0.0;
	private List<BillItem>	billLineItems		= new ArrayList<BillItem>();
	private long			billNumber;
	private long			customerId;

	public long getBillNumber()
	{
		return billNumber;
	}

	public void setBillNumber(long billNumber)
	{
		this.billNumber = billNumber;
	}

	public long getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(long customerId)
	{
		this.customerId = customerId;
	}

	public String getBillDate()
	{
		return billDate;
	}

	public void setBillDate(String billDate)
	{
		this.billDate = billDate;
	}

	public String getBillDueDate()
	{
		return billDueDate;
	}

	public void setBillDueDate(String billDueDate)
	{
		this.billDueDate = billDueDate;
	}

	public String getBillPeriodFromDate()
	{
		return billPeriodFromDate;
	}

	public void setBillPeriodFromDate(String billPeriodFromDate)
	{
		this.billPeriodFromDate = billPeriodFromDate;
	}

	public String getBillPeriodToDate()
	{
		return billPeriodToDate;
	}

	public void setBillPeriodToDate(String billPeriodToDate)
	{
		this.billPeriodToDate = billPeriodToDate;
	}

	public Double getBillTotalTax()
	{
		return billTotalTax;
	}

	public void setBillTotalTax(Double billTotalTaxAmount)
	{
		this.billTotalTax = billTotalTaxAmount;
	}

	public Double getBillTotalAmount()
	{
		return billTotalAmount;
	}

	public void setBillTotalAmount(Double billTotalAmount)
	{
		this.billTotalAmount = billTotalAmount;
	}

	public List<BillItem> getBillLineItems()
	{
		return billLineItems;
	}

	public void setBillLineItems(List<BillItem> billLineItems)
	{
		this.billLineItems = billLineItems;
	}

}
