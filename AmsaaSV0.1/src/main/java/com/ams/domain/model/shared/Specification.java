package com.ams.domain.model.shared;

public interface Specification<T>
{
	boolean isSatisfiedBy(T domainObject);

}
