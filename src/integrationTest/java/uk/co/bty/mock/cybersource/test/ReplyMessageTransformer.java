package uk.co.bty.mock.cybersource.test;

import javax.xml.bind.JAXBElement;

import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

public class ReplyMessageTransformer
{
	public ReplyMessage transform(final JAXBElement<ReplyMessage> element)
	{
		return element.getValue();
	}
}
