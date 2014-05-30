package com.ams.application.service.billingandpaymentservice.servicedata;

public class BillLineItemDTO
{
	private String	srvcCode;
	private String	srvcName;
	private double	price;
	private int	quantity;
	private double	subTotal;

	public String getSrvcCode()
	{
		return srvcCode;
	}

	public void setSrvcCode(String srvcCode)
	{
		this.srvcCode = srvcCode;
	}

	public String getSrvcName()
	{
		return srvcName;
	}

	public void setSrvcName(String srvcName)
	{
		this.srvcName = srvcName;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public double getSubTotal()
	{
		return subTotal;
	}

	public void setSubTotal(double subTotal)
	{
		this.subTotal = subTotal;
	}

}
