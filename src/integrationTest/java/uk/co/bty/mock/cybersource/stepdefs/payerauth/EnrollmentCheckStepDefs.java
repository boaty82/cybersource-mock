package uk.co.bty.mock.cybersource.stepdefs.payerauth;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import uk.co.bty.mock.cybersource.constants.VeresEnrolled;
import uk.co.bty.mock.cybersource.controller.form.SopResponseForm;
import uk.co.bty.mock.cybersource.schema.transaction.Card;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthEnrollService;
import uk.co.bty.mock.cybersource.schema.transaction.PurchaseTotals;
import uk.co.bty.mock.cybersource.schema.transaction.RecurringSubscriptionInfo;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.stepdefs.state.CardState;
import uk.co.bty.mock.cybersource.stepdefs.token.SopRequestForm;
import uk.co.bty.mock.cybersource.test.Gateway;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static org.assertj.core.api.Assertions.assertThat;

public class EnrollmentCheckStepDefs
{
	@Resource
	private Gateway gateway;

	@Autowired
	private CardState cardState;

	@Value("${cucumber.merchant.id")
	private String merchantId;

	@And("^I check if the card is enrolled$")
	public void iCheckIfTheCardIsEnrolled()
	{
		final SopRequestForm sopRequestForm = cardState.getSopRequestForm();
		final SopResponseForm postResult = cardState.getPostResult();
		assertThat(postResult)
				.as("New Card should exist at this point")
				.isNotNull();

		final PurchaseTotals purchaseTotals = PurchaseTotals.builder().grandTotalAmount("15.00").build();

		final Card card = Card.builder()
				.expirationMonth(stringToNumber(sopRequestForm.getCard_expiry_month()))
				.expirationYear(stringToNumber(sopRequestForm.getCard_expiry_year()))
				.cardType(sopRequestForm.getCard_type())
				.build();

		final RecurringSubscriptionInfo subscriptionInfo = RecurringSubscriptionInfo.builder()
				.subscriptionID(postResult.getPayment_token())
				.build();

		final PayerAuthEnrollService enrollService = PayerAuthEnrollService.builder()
				.run("true")
				.build();

		final RequestMessage requestMessage = RequestMessage.builder()
				.merchantID(merchantId)
				.purchaseTotals(purchaseTotals)
				.card(card)
				.recurringSubscriptionInfo(subscriptionInfo)
				.payerAuthEnrollService(enrollService)
				.build();

		cardState.setEnrollmentReplyMessage(gateway.checkCardPayerAuthEnrollment(requestMessage));
	}

	private BigInteger stringToNumber(final String value)
	{
		return new BigInteger(value.substring(value.indexOf(":") + 1));
	}

	@Then("^the enrollment status is \"([^\"]*)\"$")
	public void theEnrollmentStatusIs(final String status)
	{
		assertThat(cardState.getEnrollmentReplyMessage().getPayerAuthEnrollReply().getVeresEnrolled())
				.as("VeresEnrolled was not as expected")
				.isEqualTo(VeresEnrolled.valueOf(status).getCode());
	}
}
