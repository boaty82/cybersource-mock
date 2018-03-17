package uk.co.bty.mock.cybersource.service.signature.impl;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import uk.co.bty.mock.cybersource.service.signature.SignatureGenerator;

public class DefaultSignatureGenerator implements SignatureGenerator
{
	private static final String HMAC_SHA256 = "HmacSHA256";
	private static final Joiner FIELD_NAME_JOINER = Joiner.on(",").skipNulls();
	private static final MapJoiner MAP_JOINER = Joiner.on(",").withKeyValueSeparator("=").useForNull("");

	private String key;
	private List<String> fieldNamesToSign;
	private Converter<Object, Map<String, String>> objectMapConverter;

	@Override
	public String getFieldNamesSigned()
	{
		return FIELD_NAME_JOINER.join(getFieldNamesToSign());
	}

	@Override
	public SignedData generate(final Object source)
	{
		final Map<String, String> data = getObjectMapConverter().convert(source);

		final Map<String, String> dataToSign = new LinkedHashMap<>();
		getFieldNamesToSign().forEach(key -> dataToSign.put(key, getValue(key, data)));

		try
		{
			return SignedData.builder()
					.signature(createSignature(MAP_JOINER.join(dataToSign)))
					.build();
		}
		catch (GeneralSecurityException e)
		{
			throw new SignatureGenerationException(e);
		}
	}

	private String getValue(final String fieldName, final Map<String, String> data)
	{
		if (!data.containsKey(fieldName))
		{
			throw new MissingDataForSignatureGenerationException("Missing data for field: " + fieldName);
		}
		return data.get(fieldName);
	}

	private String createSignature(final String data) throws GeneralSecurityException
	{
		final SecretKeySpec secretKeySpec = new SecretKeySpec(getKey().getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
		final Mac mac = Mac.getInstance(HMAC_SHA256);
		mac.init(secretKeySpec);
		final byte[] rawHmac = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
		return DatatypeConverter.printBase64Binary(rawHmac).replace("\n", "");
	}

	protected String getKey()
	{
		return key;
	}

	@Required
	public void setKey(final String key)
	{
		this.key = key;
	}

	protected List<String> getFieldNamesToSign()
	{
		return fieldNamesToSign;
	}

	@Required
	public void setFieldNamesToSign(final List<String> fieldNamesToSign)
	{
		this.fieldNamesToSign = fieldNamesToSign;
	}

	protected Converter<Object, Map<String, String>> getObjectMapConverter()
	{
		return objectMapConverter;
	}

	@Required
	public void setObjectMapConverter(final Converter<Object, Map<String, String>> objectMapConverter)
	{
		this.objectMapConverter = objectMapConverter;
	}
}
