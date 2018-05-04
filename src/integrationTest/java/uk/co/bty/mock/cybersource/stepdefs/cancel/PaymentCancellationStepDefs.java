package uk.co.bty.mock.cybersource.stepdefs.cancel;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.schema.transaction.CCAuthReversalService;
import uk.co.bty.mock.cybersource.schema.transaction.Item;
import uk.co.bty.mock.cybersource.schema.transaction.PurchaseTotals;
import uk.co.bty.mock.cybersource.schema.transaction.RecurringSubscriptionInfo;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.stepdefs.state.ResponseState;
import uk.co.bty.mock.cybersource.test.Gateway;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentCancellationStepDefs {

    @Resource
    private Gateway gateway;

    @Autowired
    private ResponseState responseState;

    @Value("${cucumber.merchant.id")
    private String merchantId;
    
    private String authId;
    
    @Given("^I have a authorised a transaction and the ID was \"([^\"]*)\"$")
    public void iHaveAAuthorisedATransactionAndTheIDWas(final String id) {
        this.authId = id;
    }

    @When("^I try to cancel the authorisation$")
    public void iTryToCancelTheAuthorisation()  {

        final List<Item> items = Arrays.asList(
                Item.builder()
                        .unitPrice("2.00")
                        .quantity("1")
                        .productCode("Code")
                        .productName("Product Name")
                        .productSKU("SKU")
                        .taxAmount("0.00")
                        .totalAmount("2.00")
                        .build()
        );

        final PurchaseTotals purchaseTotals = PurchaseTotals.builder()
                .currency("GBP")
                .grandTotalAmount("2.00")
                .build();
        
        final RequestMessage cancellationResponse = RequestMessage.builder()
                .merchantID(merchantId)
                .merchantReferenceCode(UUID.randomUUID().toString())
                .item(items)
                .purchaseTotals(purchaseTotals)
                .ccAuthReversalService(CCAuthReversalService.builder().authRequestID(authId).run("true").build())
                .build();
        
        responseState.setCancellationReplyMessage(gateway.getResponse(cancellationResponse));
    }

    @Then("^the payment cancellation decision was \"([^\"]*)\"$")
    public void thePaymentCancellationDecisionWas(String decision)  {
        assertThat(responseState.getCancellationReplyMessage().getDecision())
                .as("Auth Result was not as expected")
                .isEqualTo(decision);
    }

    @And("^the payment cancellation reason was \"([^\"]*)\"$")
    public void thePaymentCancellationReasonWas(String reason)  {
        assertThat(responseState.getCancellationReplyMessage().getCcAuthReversalReply().getReasonCode())
                .as("Auth Result was not as expected")
                .isEqualTo(ReasonCode.valueOf(reason).getBigInt());
    }
}
