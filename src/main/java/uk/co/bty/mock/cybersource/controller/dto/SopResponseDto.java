package uk.co.bty.mock.cybersource.controller.dto;

import uk.co.bty.mock.cybersource.controller.form.SopForm;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import uk.co.bty.mock.cybersource.data.TokenResponseData;

@Builder
@Value
@Getter
public class SopResponseDto
{
	private SopForm sourceForm;
	private TokenResponseData tokenResponse;
}
