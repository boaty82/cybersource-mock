package uk.co.bty.mock.cybersource.service.token;

import java.util.Collections;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

public interface ValidationService<T>
{
	ResultData validate(T data);

	@Getter
	@Builder
	class ResultData
	{
		private boolean valid;

		@Builder.Default()
		private Map<String,String> errors = Collections.emptyMap();

		public static ResultData valid()
		{
			return ResultData.builder().valid(true).build();
		}

		public static ResultData invalid(final Map<String,String> errors)
		{
			return ResultData.builder().errors(errors).build();
		}
	}
}
