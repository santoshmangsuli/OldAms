package com.ams.domain.model.service;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ams.domain.model.measureandunits.Frequency;
import com.ams.domain.model.measureandunits.Rate;

/*
 * @author Raghavendra Badiger
 * 
 */

@Embeddable
public class ChargeComponent implements Serializable
{
	/*
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public enum ServiceChargeType
	{
		RATE, DISCOUNT, TAX
	};

	private ServiceChargeType	chargeType;
	private String				chargeName;
	private Rate				chargeRate;
	private int				chargePercentile;
	private Frequency			chargeFrequency;
	private boolean			percentileBased	= false;

	public ChargeComponent()
	{}

	public ChargeComponent(String chargeName, ServiceChargeType chargeType, Rate chargeRate,
						Frequency chargeFreq)
	{
		this.chargeType = chargeType;
		this.chargeName = chargeName;
		this.chargeRate = chargeRate;
		this.chargeFrequency = chargeFreq;
		this.percentileBased = false;
	}

	public ChargeComponent(String chargeName, ServiceChargeType chargeType, int percentDiscnt,
						Frequency chargeFreq)
	{
		this.chargeType = chargeType;
		this.chargeName = chargeName;
		this.chargeFrequency = chargeFreq;
		this.chargePercentile = percentDiscnt;
		this.percentileBased = true;

	}

	@Enumerated(EnumType.STRING)
	public ServiceChargeType getChargeType()
	{
		return chargeType;
	}

	@SuppressWarnings("unused")
	private void setChargeType(ServiceChargeType chargeType)
	{
		this.chargeType = chargeType;
	}

	public String getChargeName()
	{
		return chargeName;
	}

	@SuppressWarnings("unused")
	private void setChargeName(String chargeName)
	{
		this.chargeName = chargeName;
	}

	public Rate getChargeRate()
	{
		return chargeRate;
	}

	@SuppressWarnings("unused")
	private void setChargeRate(Rate chargeRate)
	{
		this.chargeRate = chargeRate;
	}

	public int getChargePercentile()
	{
		return chargePercentile;
	}

	@SuppressWarnings("unused")
	private void setChargePercentile(int chargePercentile)
	{
		this.chargePercentile = chargePercentile;
	}

	@Enumerated(EnumType.STRING)
	public Frequency getChargeFrequency()
	{
		return chargeFrequency;
	}

	@SuppressWarnings("unused")
	private void setChargeFrequency(Frequency chargeFrequency)
	{
		this.chargeFrequency = chargeFrequency;
	}

	public boolean getPercentileBased()
	{
		return percentileBased;
	}

	@SuppressWarnings("unused")
	private void setPercentileBased(boolean percentileBased)
	{
		this.percentileBased = percentileBased;
	}

	@Override
	public String toString()
	{
		return "ChargeComponent [chargeType=" + chargeType + ", chargeName=" + chargeName + ", chargeRate=" + chargeRate + ", chargePercentile=" + chargePercentile + ", chargeFrequency=" + chargeFrequency + ", percentileBased=" + percentileBased + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((chargeName == null) ? 0 : chargeName.hashCode());
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
		if (!(obj instanceof ChargeComponent))
		{
			return false;
		}
		ChargeComponent other = (ChargeComponent) obj;
		if (chargeName == null)
		{
			if (other.chargeName != null)
			{
				return false;
			}
		}
		else if (!chargeName.equals(other.chargeName))
		{
			return false;
		}
		return true;
	}

}
