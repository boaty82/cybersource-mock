package uk.co.bty.mock.cybersource.stepdefs.payerauth;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.bty.mock.cybersource.constants.PayerAuthValidateCommerceIndicator;
import uk.co.bty.mock.cybersource.constants.PayerAuthValidateResult;
import uk.co.bty.mock.cybersource.controller.form.SopResponseForm;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthValidateReply;
import uk.co.bty.mock.cybersource.schema.transaction.PayerAuthValidateService;
import uk.co.bty.mock.cybersource.schema.transaction.RecurringSubscriptionInfo;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.stepdefs.state.CardState;
import uk.co.bty.mock.cybersource.test.Gateway;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidationStepDefs
{
	@Resource
	private Gateway gateway;

	@Autowired
	private CardState cardState;

	@Value("${cucumber.merchant.id")
	private String merchantId;

	@Given("^the card is enrolled$")
	public void the_card_is_enrolled()
	{
		//Nothing to do, just a descriptive step
	}

	@When("^I check the card payer authorisation$")
	public void iCheckTheCardPayerAuthorisation()
	{
		final SopResponseForm postResult = cardState.getPostResult();
		assertThat(postResult)
				.as("New Card should exist at this point")
				.isNotNull();

		final RecurringSubscriptionInfo subscriptionInfo = RecurringSubscriptionInfo.builder()
				.subscriptionID(postResult.getPayment_token())
				.build();

		final PayerAuthValidateService payerAuthValidateService = PayerAuthValidateService.builder()
				.signedPARes(postResult.getPayment_token() + "-pareq")
				.run("true")
				.build();

		final RequestMessage requestMessage = RequestMessage.builder()
				.merchantID(merchantId)
				.payerAuthValidateService(payerAuthValidateService)
				.recurringSubscriptionInfo(subscriptionInfo)
				.build();

		cardState.setPayerAuthValidationReplyMessage(gateway.checkCardPayerAuthValidation(requestMessage));
	}

	@Then("^the card payer authorisation is \"([^\"]*)\" and the indicator is \"([^\"]*)\"$")
	public void theCardPayerAuthorisationIsAndTheIndicatorIs(final String status, final String indicator)
	{
		final PayerAuthValidateReply reply = cardState.getPayerAuthValidationReplyMessage().getPayerAuthValidateReply();

		assertThat(reply.getAuthenticationResult())
				.as("VeresEnrolled was not as expected")
				.isEqualTo(PayerAuthValidateResult.valueOf(status).getCode());


		assertThat(reply.getCommerceIndicator())
				.as("Commerce Indicator was not as expected")
				.isEqualTo(PayerAuthValidateCommerceIndicator.valueOf(indicator).getCode());
	}
}
