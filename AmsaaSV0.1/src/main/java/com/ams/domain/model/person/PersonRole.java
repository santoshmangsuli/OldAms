package com.ams.domain.model.person;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "T_PERSONROLES")
public class PersonRole implements Serializable
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private String				roleName;
	private Authorisation		authrsn;

	public String getRoleName()
	{
		return roleName;
	}

	public void setRoleName(String roleName)
	{
		this.roleName = roleName;
	}

	public Authorisation getAuthrsn()
	{
		return authrsn;
	}

	public void setAuthrsn(Authorisation authrzn)
	{
		this.authrsn = authrzn;
	}

}
