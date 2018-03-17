package uk.co.bty.mock.cybersource.rules;

import uk.co.bty.mock.cybersource.rules.data.RuleData;

public interface RuleEngine
{
	void apply(final RuleData data);
}
