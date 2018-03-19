package uk.co.bty.mock.cybersource.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.bty.mock.cybersource.constants.PayerAuthValidateResult;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThreeDeeSubmitForm
{
	private String PaReq;
	private String TermUrl;
	private String MD;
	private PayerAuthValidateResult choice;
}
