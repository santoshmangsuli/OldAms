package com.ams.domain.model.measureandunits;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Rate implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private Money				price;
	private Unit				perUnit;

	public Rate()
	{}

	public Rate(Money money, Unit perUnit)
	{
		this.price = money;
		this.perUnit = perUnit;
	}

	public Money getPrice()
	{
		return price;
	}

	@SuppressWarnings("unused")
	private void setPrice(Money price)
	{
		this.price = price;
	}

	@Enumerated(EnumType.STRING)
	public Unit getPerUnit()
	{
		return perUnit;
	}

	@SuppressWarnings("unused")
	private void setPerUnit(Unit perUnit)
	{
		this.perUnit = perUnit;
	}

	@Override
	public String toString()
	{
		return "Rate [" + price + "/" + perUnit + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((perUnit == null) ? 0 : perUnit.hashCode());
		result = (prime * result) + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Rate))
		{
			return false;
		}
		Rate other = (Rate) obj;
		if (perUnit != other.perUnit)
		{
			return false;
		}
		if (price == null)
		{
			if (other.price != null)
			{
				return false;
			}
		}
		else if (!price.equals(other.price))
		{
			return false;
		}
		return true;
	}

}
