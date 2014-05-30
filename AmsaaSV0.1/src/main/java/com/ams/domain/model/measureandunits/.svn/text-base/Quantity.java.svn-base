package com.ams.domain.model.measureandunits;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class Quantity implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private BigDecimal			value;
	private Unit				unit;

	public Quantity()
	{}

	public Quantity(BigDecimal qty, Unit qtUnit)
	{
		this.value = qty;
		this.unit = qtUnit;
	}

	public Quantity add(Quantity qty) throws InvalidUnitException
	{
		if (this.unit.equals(qty.getUnit()))
		{
			this.value = this.value.add(qty.getValue());
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be added is different!!");
		}

		return this;

	}

	public Quantity add(BigDecimal other)
	{
		this.value = this.value.add(other);
		return this;

	}

	public Quantity subtract(Quantity qty) throws InvalidUnitException
	{
		if (this.unit.equals(qty.getUnit()))
		{
			this.value = this.value.subtract(qty.getValue());
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be subtracted is different!!");
		}

		return this;

	}

	public Quantity subtract(BigDecimal other)
	{
		this.value = this.value.subtract(other);
		return this;

	}

	public Quantity multiplyBy(Quantity qty) throws InvalidUnitException
	{
		if (this.unit.equals(qty.getUnit()))
		{
			this.value = this.value.multiply(qty.getValue());
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be multiplied is different!!");
		}

		return this;

	}

	public Quantity multiplyBy(BigDecimal other)
	{
		this.value = this.value.multiply(other);
		return this;

	}

	public Quantity divideBy(Quantity qty) throws InvalidUnitException
	{
		if (this.unit.equals(qty.getUnit()))
		{
			this.value = this.value.divide(qty.getValue());
		}
		else
		{
			throw new InvalidUnitException("Unit of Quantity to be divided is different!!");
		}

		return this;

	}

	public Quantity divideBy(BigDecimal other)
	{
		this.value = this.value.divide(other);
		return this;

	}

	/*
	 * ACCESSOR & MUTATORS
	 */

	@SuppressWarnings("unused")
	private void setValue(BigDecimal value)
	{
		this.value = value;
	}

	public BigDecimal getValue()
	{
		return value;
	}

	public Unit getUnit()
	{
		return unit;
	}

	@SuppressWarnings("unused")
	private void setUnit(Unit unit)
	{
		this.unit = unit;
	}

	@Override
	public String toString()
	{
		return "Quantity [" + value + unit + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((unit == null) ? 0 : unit.hashCode());
		result = (prime * result) + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof Quantity))
		{
			return false;
		}
		Quantity other = (Quantity) obj;
		if (unit != other.unit)
		{
			return false;
		}
		if (value == null)
		{
			if (other.value != null)
			{
				return false;
			}
		}
		else if (!value.equals(other.value))
		{
			return false;
		}
		return true;
	}

}
