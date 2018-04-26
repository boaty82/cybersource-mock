package uk.co.bty.mock.cybersource.service.transaction.converter;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.dao.txn.AuthTxn;
import uk.co.bty.mock.cybersource.schema.transaction.CCAuthReply;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

public class AuthTxnResultPopulator implements Populator<AuthTxn, ReplyMessage>
{
	@Override
	public void populate(final AuthTxn authTxn, final ReplyMessage replyMessage)
	{
		replyMessage.setDecision(authTxn.getDecision().name());

		replyMessage.setCcAuthReply(CCAuthReply.builder()
				.reasonCode(authTxn.getReasonCode().getBigInt())
				.amount(authTxn.getAmount())
				.authorizationCode(authTxn.getAuthCode())
				.authorizedDateTime(authTxn.getTime())
				.avsCode(authTxn.getAvsCode().getCode())
				.avsCodeRaw(authTxn.getAvsCode().getCode())
				.cvCode(authTxn.getCvnCode().getCode())
				.cvCodeRaw(authTxn.getCvnCode().getCode())
				.ownerMerchantID(authTxn.getMerchantId())
				.build());
	}
}
