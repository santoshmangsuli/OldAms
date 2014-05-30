package com.ams.domain.model.booking;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Resource
{
	private long	resourceId;
	private String	resourceName;
	private String	description;

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@Id
	public long getResourceId()
	{
		return resourceId;
	}

	public void setResourceId(long resourceId)
	{
		this.resourceId = resourceId;
	}

	public String getResourceName()
	{
		return resourceName;
	}

	public void setResourceName(String resourceName)
	{
		this.resourceName = resourceName;
	}

}
