package com.ams.domain.model.person;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private int				flatNumber;
	private String				apartmentName;
	private String				street;
	private String				locality;
	private String				landMark;
	private String				city;
	private String				district;
	private String				state;
	private int				pin;

	public int getFlatNumber()
	{
		return this.flatNumber;
	}

	public void setFlatNumber(int flatNumber)
	{
		this.flatNumber = flatNumber;
	}

	public String getApartmentName()
	{
		return this.apartmentName;
	}

	public void setApartmentName(String apartmentName)
	{
		this.apartmentName = apartmentName;
	}

	public String getStreet()
	{
		return this.street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getLocality()
	{
		return this.locality;
	}

	public void setLocality(String locality)
	{
		this.locality = locality;
	}

	public String getLandMark()
	{
		return this.landMark;
	}

	public void setLandMark(String landMark)
	{
		this.landMark = landMark;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getCity()
	{
		return city;
	}

	public String getDistrict()
	{
		return this.district;
	}

	public void setDistrict(String district)
	{
		this.district = district;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public int getPin()
	{
		return this.pin;
	}

	public void setPin(int pin)
	{
		this.pin = pin;
	}

}
