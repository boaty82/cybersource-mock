package uk.co.bty.mock.cybersource.controller;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import uk.co.bty.mock.cybersource.constants.ApplicationConstants;
import uk.co.bty.mock.cybersource.schema.transaction.ReplyMessage;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;
import uk.co.bty.mock.cybersource.service.transaction.TransactionService;

import static org.slf4j.LoggerFactory.getLogger;

@Endpoint
public class TransactionController
{
	private static final MultilineRecursiveToStringStyle STYLE = new MultilineRecursiveToStringStyle();
	private static final Logger LOG = getLogger(TransactionController.class);

	@Resource
	private TransactionService transactionService;

	@PayloadRoot(namespace = ApplicationConstants.NAMESPACE, localPart = "requestMessage")
	@ResponsePayload
	public ReplyMessage requestMessage(@RequestPayload final RequestMessage request)
	{
		final ReplyMessage response = ReplyMessage.builder()
				.purchaseTotals(request.getPurchaseTotals())
				.merchantReferenceCode(request.getMerchantReferenceCode())
				.requestID(UUID.randomUUID().toString())
				.requestToken(UUID.randomUUID().toString())
				.build();
		transactionService.apply(request, response);
		LOG.info("Responding with \r\n{}", ReflectionToStringBuilder.toString(response, STYLE));
		return response;
	}
}
