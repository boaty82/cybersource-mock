package uk.co.bty.mock.cybersource.data;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;
import uk.co.bty.mock.cybersource.constants.Eci;
import uk.co.bty.mock.cybersource.constants.ParesStatus;
import uk.co.bty.mock.cybersource.constants.PayerAuthValidateCommerceIndicator;
import uk.co.bty.mock.cybersource.constants.ReasonCode;

@ToString
@Value
@Builder
public class PayerAuthValidationResponseData
{
	private ReasonCode reasonCode;
	private String message;
	private ParesStatus paresStatus;
	private PayerAuthValidateCommerceIndicator commerceIndicator;
	private Eci eci;
}
