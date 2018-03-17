package uk.co.bty.mock.cybersource.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.bty.mock.cybersource.controller.dto.SopResponseDto;
import uk.co.bty.mock.cybersource.controller.form.SopForm;
import uk.co.bty.mock.cybersource.controller.form.SopResponseForm;
import uk.co.bty.mock.cybersource.data.TokenRequestData;
import uk.co.bty.mock.cybersource.service.signature.SignatureGenerator;
import uk.co.bty.mock.cybersource.service.token.TokenisationService;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/silent/token")
public class SopController
{
	private static final Logger LOG = getLogger(SopController.class);

	@Value("${sopController.return.raw.form}")
	private String returnRawForm;

	@Resource
	private TokenisationService tokenisationService;

	@Resource
	private Converter<SopForm, TokenRequestData> sopFormRequestConverter;

	@Resource
	private Converter<SopResponseDto, SopResponseForm> tokenResultConverter;

	@Resource
	private SignatureGenerator sopResponseSignatureGenerator;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(final SopForm form, final Model model)
	{
		LOG.debug("Received {}", form);

		final TokenRequestData tokenRequest = sopFormRequestConverter.convert(form);

		final SopResponseDto dto = SopResponseDto.builder()
				.sourceForm(form)
				.tokenResponse(tokenisationService.tokenise(tokenRequest))
				.build();

		final SopResponseForm unsignedResponse = tokenResultConverter.convert(dto)
				.toBuilder()
				.signed_date_time(DateTime.now().toString("yyyy-MM-dd'T'HH:mm:ssZ"))
				.signed_field_names(sopResponseSignatureGenerator.getFieldNamesSigned())
				.build();

		final SignatureGenerator.SignedData signedData = sopResponseSignatureGenerator.generate(unsignedResponse);
		final SopResponseForm signedResponse = unsignedResponse.toBuilder()
				.signature(signedData.getSignature())
				.build();


		LOG.debug("Response {}", signedResponse);
		if (Boolean.valueOf(returnRawForm))
		{
			model.addAttribute("rawForm", ReflectionToStringBuilder.toString(signedResponse, ToStringStyle.JSON_STYLE));
		}
		else
		{
			model.addAttribute("redirectTo", signedResponse.getReq_override_custom_receipt_page());
			model.addAttribute("responseForm", signedResponse);
		}
		return "sop-received";
	}
}
