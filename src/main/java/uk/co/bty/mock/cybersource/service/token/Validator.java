package uk.co.bty.mock.cybersource.service.token;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public interface Validator<T>
{
	ResultData validate(T data);

	@ToString
	@Getter
	@Builder
	class ResultData
	{
		private boolean valid;
		private String fieldName;
		private String errorMessage;

		public static ResultData valid()
		{
			return ResultData.builder().valid(true).build();
		}

		public static ResultData invalid(final String fieldName, final String errorMessage)
		{
			return ResultData.builder().fieldName(fieldName).errorMessage(errorMessage).build();
		}
	}
}
