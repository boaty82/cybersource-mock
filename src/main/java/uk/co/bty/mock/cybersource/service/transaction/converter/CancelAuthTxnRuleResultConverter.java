package uk.co.bty.mock.cybersource.service.transaction.converter;

import org.joda.time.DateTime;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.txn.CancelAuthTxn;
import uk.co.bty.mock.cybersource.data.CancelAuthTxnResponseData;
import uk.co.bty.mock.cybersource.rules.data.CancelAuthTxnRuleData;

public class CancelAuthTxnRuleResultConverter implements Converter<CancelAuthTxnRuleData, CancelAuthTxn>
{
	@Override
	public CancelAuthTxn convert(final CancelAuthTxnRuleData source)
	{
		final CancelAuthTxnResponseData response = source.getResponse();

		return CancelAuthTxn.builder()
				.id(source.getResponseId())
				.amount(source.getRequest().getRequest().getPurchaseTotals().getGrandTotalAmount())
				.decision(response.getDecision())
				.reasonCode(response.getReasonCode())
				.originalAuthId(source.getRequest().getRequest().getCcAuthReversalService().getAuthRequestID())
				.txnTime(DateTime.now().toString())
				.build();
	}
}
