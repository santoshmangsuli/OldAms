package com.ams.domain.repository.common;

import java.io.Serializable;
import java.util.List;

public interface Repository<T, ID extends Serializable>
{
	public ID createOrUpdate(T entity);

	public void delete(ID entityId);

	public T findById(ID entityId);

	public List<T> findAll();
}
