package uk.co.bty.mock.cybersource.stepdefs.auth;

import cucumber.api.Transpose;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.schema.transaction.BillTo;
import uk.co.bty.mock.cybersource.schema.transaction.CCAuthService;
import uk.co.bty.mock.cybersource.schema.transaction.Card;
import uk.co.bty.mock.cybersource.schema.transaction.Item;
import uk.co.bty.mock.cybersource.schema.transaction.MerchantDefinedData;
import uk.co.bty.mock.cybersource.schema.transaction.PurchaseTotals;
import uk.co.bty.mock.cybersource.schema.transaction.RecurringSubscriptionInfo;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.schema.transaction.ShipTo;
import uk.co.bty.mock.cybersource.stepdefs.state.CardState;
import uk.co.bty.mock.cybersource.stepdefs.token.SopRequestForm;
import uk.co.bty.mock.cybersource.stepdefs.util.CardUtil;
import uk.co.bty.mock.cybersource.test.Gateway;

import static org.assertj.core.api.Assertions.assertThat;

public class TransactionAuthStepDefs {

    @Resource
    private Gateway gateway;

    @Autowired
    private CardState cardState;

    @Value("${cucumber.merchant.id")
    private String merchantId;

    @When("^I try to authorise payment$")
    public void iTryToAuthorisePayment(@Transpose final List<AuthRequestDto> requests) {
        final SopRequestForm sopRequestForm = cardState.getSopRequestForm();

        final AuthRequestDto requestDto = requests.get(0);

        final BillTo billTo = BillTo.builder()
                .title(requestDto.getTitle())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .street1(requestDto.getLine1())
                .street2(requestDto.getLine2())
                .city(requestDto.getCity())
                .postalCode(requestDto.getPostalCode())
                .country(requestDto.getCountry())
                .email(requestDto.getEmail())
                .ipAddress(requestDto.getIpAddress())
                .customerID(requestDto.getCustomerId())
                .build();

        final ShipTo shipTo = ShipTo.builder()
                .title(requestDto.getTitle())
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .street1(requestDto.getLine1())
                .street2(requestDto.getLine2())
                .city(requestDto.getCity())
                .postalCode(requestDto.getPostalCode())
                .country(requestDto.getCountry())
                .email(requestDto.getEmail())
                .shippingMethod("deliver")
                .build();

        final List<Item> items = Arrays.asList(
                Item.builder()
                        .unitPrice(requestDto.getAmount())
                        .quantity("1")
                        .productCode("Code")
                        .productName("Product Name")
                        .productSKU("SKU")
                        .taxAmount("0.00")
                        .totalAmount(requestDto.getAmount())
                        .build()
        );

        final RecurringSubscriptionInfo subscriptionInfo = RecurringSubscriptionInfo.builder()
                .subscriptionID(cardState.getPostResult().getPayment_token())
                .build();

        final PurchaseTotals purchaseTotals = PurchaseTotals.builder()
                .currency(requestDto.getCurrency())
                .grandTotalAmount(requestDto.getAmount())
                .build();

        final Card card = Card.builder()
                .expirationMonth(CardUtil.stringToNumber(sopRequestForm.getCard_expiry_month()))
                .expirationYear(CardUtil.stringToNumber(sopRequestForm.getCard_expiry_year()))
                .cardType(sopRequestForm.getCard_type())
                .build();

        final MerchantDefinedData merchantDefinedData = MerchantDefinedData.builder().build();

        final RequestMessage requestMessage = RequestMessage.builder()
                .merchantID(merchantId)
                .merchantReferenceCode(UUID.randomUUID().toString())
                .billTo(billTo)
                .shipTo(shipTo)
                .item(items)
                .purchaseTotals(purchaseTotals)
                .card(card)
                .recurringSubscriptionInfo(subscriptionInfo)
                .merchantDefinedData(merchantDefinedData)
                .ccAuthService(CCAuthService.builder().run("true").build())
                .build();

        cardState.setAuthorisationReplyMessage(gateway.transactionAuthorisation(requestMessage));
    }

    @Then("^the payment authorisation decision was \"([^\"]*)\"$")
    public void thePaymentAuthorisationDecisionWas(final String decision) {
        assertThat(cardState.getAuthorisationReplyMessage().getDecision())
                .as("Auth Result was not as expected")
                .isEqualTo(decision);
    }

    @And("^the payment authorisation reason was \"([^\"]*)\"$")
    public void thePaymentAuthorisationReasonWas(final String reason)  {
        assertThat(cardState.getAuthorisationReplyMessage().getCcAuthReply().getReasonCode())
                .as("Auth Result was not as expected")
                .isEqualTo(ReasonCode.valueOf(reason).getBigInt());
    }
}
