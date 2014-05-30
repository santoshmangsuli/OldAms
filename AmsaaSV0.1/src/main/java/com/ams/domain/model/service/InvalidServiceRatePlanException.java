package com.ams.domain.model.service;

import com.ams.domain.model.shared.DomainException;

public class InvalidServiceRatePlanException extends DomainException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public InvalidServiceRatePlanException()
	{}

	public InvalidServiceRatePlanException(String msg)
	{
		super(msg);
	}

}
