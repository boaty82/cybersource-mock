package uk.co.bty.mock.cybersource.stepdefs.token;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.NotImplementedException;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uk.co.bty.mock.cybersource.stepdefs.state.CardState;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateTokenStepDefs
{
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Value("${cucumber.sop.post.url}")
	private String sopPostUrl;

	@Autowired
	private CardState cardState;

	@Autowired
	private ObjectMapper objectMapper;

	@Given("^I enter the following card data$")
	public void i_enter_the_following_card_data(final List<CardData> cards)
	{
		final CardData card = cards.get(0);
		final SopRequestForm sopRequestForm = new SopRequestForm();
		sopRequestForm.setCard_type(CardType.valueOf(card.getCardType()).getCode());
		sopRequestForm.setCard_number(card.getCardNumber());
		sopRequestForm.setCard_expiry_month("string:" + card.getExpMonth());
		sopRequestForm.setCard_expiry_year("number:" + card.getExpYear());
		sopRequestForm.setCard_expiry_date(card.getExpMonth() + "-" + card.getExpYear());
		Optional.ofNullable(card.getForename()).ifPresent(sopRequestForm::setBill_to_forename);
		Optional.ofNullable(card.getSurname()).ifPresent(sopRequestForm::setBill_to_surname);
		cardState.setSopRequestForm(sopRequestForm);
	}

	@And("^billing info is empty$")
	public void billingInfoIsEmpty()
	{
		final SopRequestForm sopRequestForm = cardState.getSopRequestForm();
		sopRequestForm.setBill_to_email("");
		sopRequestForm.setBill_to_forename("");
		sopRequestForm.setBill_to_surname("");
		sopRequestForm.setBill_to_address_line1("");
		sopRequestForm.setBill_to_address_line2("");
		sopRequestForm.setBill_to_address_city("");
		sopRequestForm.setBill_to_address_country("");
		sopRequestForm.setBill_to_address_postal_code("");
	}

	@When("^I request a token$")
	public void i_request_a_token()
	{
		throw new NotImplementedException("TODO, just want to try and run tests in travis for now");
	}

	@Then("^I receive back the given data$")
	public void i_receive_back_the_given_data()
	{
		final SopResponseForm postResult = cardState.getPostResult();

		final SoftAssertions softly = new SoftAssertions();

		softly.assertThat(postResult.getDecision())
				.as("Decision is not as expected")
				.isEqualTo("ACCEPT");
		softly.assertThat(postResult.getMessage())
				.as("Message is not as expected")
				.isEqualTo("Request was processed successfully.");
		softly.assertThat(postResult.getReason_code())
				.as("Reason Code is not as expected")
				.isEqualTo("100");

		softly.assertThat(postResult.getRequest_token())
				.as("Request Token should not be empty")
				.isNotEmpty();
		softly.assertThat(postResult.getTransaction_id())
				.as("Transaction Id should not be empty")
				.isNotEmpty();
		softly.assertThat(postResult.getSignature())
				.as("Req Signature should not be empty")
				.isNotEmpty();
		softly.assertThat(postResult.getSigned_date_time())
				.as("Req Signed Date Time should not be empty")
				.isNotEmpty();
		softly.assertThat(postResult.getSigned_field_names())
				.as("Req Signed Field Names should not be empty")
				.isEqualTo("transaction_id,decision,req_access_key,req_profile_id,req_transaction_uuid,req_transaction_type,req_reference_number," +
						"req_currency,req_locale,req_payment_method,req_override_custom_receipt_page,req_override_custom_cancel_page,req_skip_decision_manager," +
						"req_bill_to_forename,req_bill_to_surname,req_bill_to_email,req_bill_to_address_line1,req_bill_to_address_line2,req_bill_to_address_city," +
						"req_bill_to_address_country,req_bill_to_address_postal_code,req_card_number,req_card_type,req_card_expiry_date,req_customer_ip_address," +
						"req_merchant_defined_data90,message,reason_code,request_token,payment_token,signed_field_names,signed_date_time");

		final SopRequestForm sopRequestForm = cardState.getSopRequestForm();
		softly.assertThat(postResult.getReq_access_key())
				.as("Req Access Key is not as expected")
				.isEqualTo(sopRequestForm.getAccess_key());
		softly.assertThat(postResult.getReq_bill_to_address_city())
				.as("Req Bill City Key is not as expected")
				.isEqualTo(sopRequestForm.getBill_to_address_city());
		softly.assertThat(postResult.getReq_bill_to_address_country())
				.as("Req Bill Country is not as expected")
				.isEqualTo(sopRequestForm.getBill_to_address_country());
		softly.assertThat(postResult.getReq_bill_to_address_line1())
				.as("Req Bill Line 1 is not as expected")
				.isEqualTo(sopRequestForm.getBill_to_address_line1());
		softly.assertThat(postResult.getReq_bill_to_address_line2())
				.as("Req Bill Line 2 is not as expected")
				.isEqualTo(sopRequestForm.getBill_to_address_line2());
		softly.assertThat(postResult.getReq_bill_to_address_postal_code())
				.as("Req Bill Postal Code is not as expected")
				.isEqualTo(sopRequestForm.getBill_to_address_postal_code());
		softly.assertThat(postResult.getReq_bill_to_email())
				.as("Req Bill Email is not as expected")
				.isEqualTo(sopRequestForm.getBill_to_email());
		softly.assertThat(postResult.getReq_bill_to_forename())
				.as("Req Bill Key Forename not be empty")
				.isEqualTo(sopRequestForm.getBill_to_forename());
		softly.assertThat(postResult.getReq_bill_to_surname())
				.as("Req Bill Surname is not as expected")
				.isEqualTo(sopRequestForm.getBill_to_surname());
		softly.assertThat(postResult.getReq_card_expiry_date())
				.as("Req Card Expiry Date is not as expected")
				.isEqualTo(sopRequestForm.getCard_expiry_date());
		softly.assertThat(postResult.getReq_card_type())
				.as("Req Card Type is not as expected")
				.isEqualTo(sopRequestForm.getCard_type());
		softly.assertThat(postResult.getReq_currency())
				.as("Req Currency is not as expected")
				.isEqualTo(sopRequestForm.getCurrency());
		softly.assertThat(postResult.getReq_customer_ip_address())
				.as("Req Customer IP Address is not as expected")
				.isEqualTo(sopRequestForm.getCustomer_ip_address());
		softly.assertThat(postResult.getReq_locale())
				.as("Req Locale is not as expected")
				.isEqualTo(sopRequestForm.getLocale());
		softly.assertThat(postResult.getReq_merchant_defined_data90())
				.as("Req Merchant Defined Data 90 is not as expected")
				.isEqualTo(sopRequestForm.getMerchant_defined_data90());
		softly.assertThat(postResult.getReq_override_custom_cancel_page())
				.as("Req Cancel Page is not as expected")
				.isEqualTo(sopRequestForm.getOverride_custom_cancel_page());
		softly.assertThat(postResult.getReq_override_custom_receipt_page())
				.as("Req Receipt Page is not as expected")
				.isEqualTo(sopRequestForm.getOverride_custom_receipt_page());
		softly.assertThat(postResult.getReq_payment_method())
				.as("Req Payment Method is not as expected")
				.isEqualTo(sopRequestForm.getPayment_method());
		softly.assertThat(postResult.getReq_profile_id())
				.as("Req Profile Id is not as expected")
				.isEqualTo(sopRequestForm.getProfile_id());
		softly.assertThat(postResult.getReq_reference_number())
				.as("Req Reference Number is not as expected")
				.isEqualTo(sopRequestForm.getReference_number());
		softly.assertThat(postResult.getReq_skip_decision_manager())
				.as("Req Skip Decision Manager is not as expected")
				.isEqualTo(sopRequestForm.getSkip_decision_manager());
		softly.assertThat(postResult.getReq_transaction_type())
				.as("Req Transaction Type is not as expected")
				.isEqualTo(sopRequestForm.getTransaction_type());

		softly.assertAll();
	}

	@Then("^the card number is masked as \"([^\"]*)\"$")
	public void the_card_number_is_masked_as(String maskedNumber)
	{
		assertThat(cardState.getPostResult().getReq_card_number()).isEqualTo(maskedNumber);
	}

	@Then("^I receive a token id$")
	public void i_receive_a_token_id()
	{
		assertThat(cardState.getPostResult().getPayment_token()).isNotEmpty();
	}

	@Then("^the token request decision is \"([^\"]*)\" and reason is \"([^\"]*)\" and message is \"([^\"]*)\" and lists the following as invalid fields$")
	public void theTokenRequestDecisionIsAndReasonIsAndListsTheFollowingAsInvalidFields(final String decision, final String reason, final String message, final DataTable table)
	{
		final List<String> expectedInvalidFields = table.asList(String.class);

		final SoftAssertions softly = checkTokenRequestDecisionAndReasonIs(decision, reason, message);

		softly.assertThat(Arrays.asList(cardState.getPostResult().getInvalid_fields().split(",")))
				.as("Invalid Fields not as expected")
				.containsOnly(expectedInvalidFields.toArray(new String[]{}));

		softly.assertAll();
	}

	@Then("^the token request decision is \"([^\"]*)\" and reason is \"([^\"]*)\" and message is \"([^\"]*)\"$")
	public void theTokenRequestDecisionIsAndReasonIsAndMessageIs(final String decision, final String reason, final String message)
	{
		final SoftAssertions softly = checkTokenRequestDecisionAndReasonIs(decision, reason, message);
		softly.assertAll();
	}

	private SoftAssertions checkTokenRequestDecisionAndReasonIs(final String decision, final String reason, final String message)
	{
		final SopResponseForm postResult = cardState.getPostResult();

		final SoftAssertions softly = new SoftAssertions();

		softly.assertThat(postResult.getDecision())
				.as("Decision is not as expected")
				.isEqualTo(decision);
		softly.assertThat(postResult.getMessage())
				.as("Message is not as expected")
				.isEqualTo(message);
		softly.assertThat(postResult.getReason_code())
				.as("Reason Code is not as expected")
				.isEqualTo(reason);

		return softly;
	}
}
