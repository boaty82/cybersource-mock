package uk.co.bty.mock.cybersource.stepdefs.state;

import org.springframework.stereotype.Component;

import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;

import lombok.Data;
import uk.co.bty.mock.cybersource.stepdefs.token.SopRequestForm;
import uk.co.bty.mock.cybersource.stepdefs.token.SopResponseForm;

@Data
@Component
public class CardState
{
	private SopRequestForm sopRequestForm;
	private SopResponseForm postResult;
	private ReplyMessage enrollmentReplyMessage;
}