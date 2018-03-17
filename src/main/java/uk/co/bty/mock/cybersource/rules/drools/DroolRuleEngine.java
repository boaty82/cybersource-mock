package uk.co.bty.mock.cybersource.rules.drools;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import uk.co.bty.mock.cybersource.rules.RuleEngine;
import uk.co.bty.mock.cybersource.rules.data.RuleData;

import static org.slf4j.LoggerFactory.getLogger;

public class DroolRuleEngine implements RuleEngine
{
	private static final Logger LOG = getLogger(DroolRuleEngine.class);

	private KieSessionFactory kieSessionFactory;

	@Override
	public void apply(final RuleData data)
	{
		final KieSession session = getKieSessionFactory().getSession(data.getProfileId());
		session.insert(data);
		session.fireAllRules();
		session.dispose();
		LOG.debug("got session [{}] for profile [{}]. resulted in [{}]", session, data.getProfileId(), data);
	}

	protected KieSessionFactory getKieSessionFactory()
	{
		return kieSessionFactory;
	}

	@Required
	public void setKieSessionFactory(final KieSessionFactory kieSessionFactory)
	{
		this.kieSessionFactory = kieSessionFactory;
	}
}
