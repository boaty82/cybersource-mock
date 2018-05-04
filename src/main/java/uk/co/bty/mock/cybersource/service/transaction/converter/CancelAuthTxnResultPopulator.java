package uk.co.bty.mock.cybersource.service.transaction.converter;

import uk.co.bty.mock.cybersource.Populator;
import uk.co.bty.mock.cybersource.dao.txn.CancelAuthTxn;
import uk.co.bty.mock.cybersource.schema.transaction.CCAuthReversalReply;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

public class CancelAuthTxnResultPopulator implements Populator<CancelAuthTxn, ReplyMessage> 
{
    @Override
    public void populate(final CancelAuthTxn cancelAuthTxn, final ReplyMessage replyMessage) 
    {
        replyMessage.setDecision(cancelAuthTxn.getDecision().name());
        replyMessage.setCcAuthReversalReply(
                CCAuthReversalReply.builder()
                        .reasonCode(cancelAuthTxn.getReasonCode().getBigInt())
                        .amount(cancelAuthTxn.getAmount())
                        .requestDateTime(cancelAuthTxn.getTxnTime())
                        .build()
        );
    }
}
