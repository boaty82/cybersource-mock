package uk.co.bty.mock.cybersource.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.bty.mock.cybersource.constants.PayerAuthValidateResult;
import uk.co.bty.mock.cybersource.controller.form.ThreeDeeRenderForm;
import uk.co.bty.mock.cybersource.controller.form.ThreeDeeResponseForm;
import uk.co.bty.mock.cybersource.controller.form.ThreeDeeSubmitForm;
import uk.co.bty.mock.cybersource.service.payerauth.PayerAuthEnrolCheckService;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/silent/enroll")
public class ThreeDeeSecureController
{
	private static final Logger LOG = getLogger(ThreeDeeSecureController.class);

	@Value("${3dController.return.raw.form}")
	private String returnRawForm;

	@Resource
	private PayerAuthEnrolCheckService payerAuthEnrolCheckService;

	@RequestMapping(value = "/render", method = RequestMethod.POST)
	public String render(final ThreeDeeRenderForm form, final Model model)
	{
		LOG.debug("Received {}", form);

		if (Boolean.valueOf(returnRawForm))
		{
			model.addAttribute("rawForm", ReflectionToStringBuilder.toString(form, ToStringStyle.JSON_STYLE));
		}
		else
		{
			model.addAttribute("choices", PayerAuthValidateResult.values());
			model.addAttribute("requestForm", ThreeDeeSubmitForm.builder()
					.PaReq(form.getPaReq())
					.MD(form.getMD())
					.TermUrl(form.getTermUrl())
					.choice(PayerAuthValidateResult.SUCCESS)
					.build());
		}

		return "three-dee-render";
	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String result(final ThreeDeeSubmitForm form, final Model model)
	{
		LOG.debug("Received {}", form);

		payerAuthEnrolCheckService.updateWith(form.getMD(), form.getChoice());

		final ThreeDeeResponseForm responseForm = ThreeDeeResponseForm.builder()
				.PaRes(form.getPaReq())
				.MD(form.getMD())
				.build();
		model.addAttribute("redirectTo", form.getTermUrl());
		model.addAttribute("responseForm", responseForm);

		return "three-dee-response";
	}
}
