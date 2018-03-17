package uk.co.bty.mock.cybersource.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.repository.CrudRepository;

import uk.co.bty.mock.cybersource.service.ResultSavingService;

public class AbstractResultSavingService<REQUEST, RESPONSE, PK extends Serializable> implements ResultSavingService<REQUEST, RESPONSE, PK>
{
	private CrudRepository<RESPONSE, PK> repository;
	private Converter<REQUEST, RESPONSE> converter;

	@Override
	public RESPONSE save(REQUEST request)
	{
		return repository.save(converter.convert(request));
	}

	@Required
	public void setRepository(final CrudRepository<RESPONSE, PK> repository)
	{
		this.repository = repository;
	}

	@Required
	public void setConverter(final Converter<REQUEST, RESPONSE> converter)
	{
		this.converter = converter;
	}
}
