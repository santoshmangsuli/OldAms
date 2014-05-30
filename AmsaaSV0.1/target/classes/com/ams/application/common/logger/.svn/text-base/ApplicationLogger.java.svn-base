package com.ams.application.common.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationLogger
{

	public Object log(ProceedingJoinPoint joinpoint)
	{
		Object result = null;
		String layerName = "APPLICATION";
		String objectType = joinpoint.getSignature().getDeclaringTypeName();

		if (objectType.contains("service"))
		{
			layerName = "SERVICE";
		}

		else if (objectType.contains("interfaces"))
		{
			layerName = "APP-INTERFACES";
		}

		else if (objectType.contains("repository"))
		{
			layerName = "REPOSITORY";
		}

		Logger lgr = LoggerFactory.getLogger(objectType);

		lgr.info("{} LAYER METHOD CALL:{}", layerName, joinpoint.getSignature());

		try
		{
			result = joinpoint.proceed();

		} catch (Throwable e)
		{
			e.printStackTrace();
		}
		lgr.info("{} LAYER METHOD EXIT:{}", layerName, joinpoint.getSignature());
		return result;
	}

}
