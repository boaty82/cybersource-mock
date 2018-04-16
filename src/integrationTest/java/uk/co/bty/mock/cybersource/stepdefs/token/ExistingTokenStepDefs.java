package uk.co.bty.mock.cybersource.stepdefs.token;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.java.en.Given;
import uk.co.bty.mock.cybersource.controller.form.SopResponseForm;
import uk.co.bty.mock.cybersource.stepdefs.state.CardState;

public class ExistingTokenStepDefs
{
	@Autowired
	private CardState cardState;

	@Given("^I have a token$")
	public void i_have_a_token(final List<CardData> cards)
	{
		final CardData cardData = cards.get(0);

		final SopRequestForm request = new SopRequestForm();
		request.setCard_type(cardData.getCardType());
		request.setCard_expiry_month(cardData.getExpMonth());
		request.setCard_expiry_year(cardData.getExpYear());

		cardState.setSopRequestForm(request);
		cardState.setPostResult(SopResponseForm.builder().payment_token(cardData.getToken()).build());
	}
}
