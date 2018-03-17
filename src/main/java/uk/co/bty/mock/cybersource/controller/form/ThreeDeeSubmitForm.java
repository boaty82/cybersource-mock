package uk.co.bty.mock.cybersource.controller.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ThreeDeeSubmitForm
{
	private String PaReq;
	private String TermUrl;
	private String MD;
}
