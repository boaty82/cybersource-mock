package uk.co.bty.mock.cybersource.stepdefs.state;

import org.springframework.stereotype.Component;

import lombok.Data;
import uk.co.bty.mock.cybersource.controller.form.SopResponseForm;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.stepdefs.token.SopRequestForm;

@Data
@Component
public class CardState
{
	private SopRequestForm sopRequestForm;
	private SopResponseForm postResult;
	private ReplyMessage enrollmentReplyMessage;
	private ReplyMessage payerAuthValidationReplyMessage;
}