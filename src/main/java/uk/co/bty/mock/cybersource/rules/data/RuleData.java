package uk.co.bty.mock.cybersource.rules.data;

import uk.co.bty.mock.cybersource.rules.RuleType;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
public abstract class RuleData<REQUEST, RESPONSE>
{
	@Getter
	private final RuleType ruleType;
	private String profileId;
	private REQUEST request;
	private RESPONSE response;

	protected RuleData(final RuleType ruleType)
	{
		this.ruleType = ruleType;
	}

}
