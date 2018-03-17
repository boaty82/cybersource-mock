package uk.co.bty.mock.cybersource.service;

import java.io.Serializable;

public interface ResultSavingService<REQUEST, RESPONSE, PK extends Serializable>
{
	RESPONSE save(REQUEST request);
}
