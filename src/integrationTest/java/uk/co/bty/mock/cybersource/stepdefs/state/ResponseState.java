package uk.co.bty.mock.cybersource.stepdefs.state;

import org.springframework.stereotype.Component;

import lombok.Data;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

@Data
@Component
public class ResponseState {
    private ReplyMessage enrollmentReplyMessage;
    private ReplyMessage payerAuthValidationReplyMessage;
    private ReplyMessage authorisationReplyMessage;
    private ReplyMessage cancellationReplyMessage;
}
