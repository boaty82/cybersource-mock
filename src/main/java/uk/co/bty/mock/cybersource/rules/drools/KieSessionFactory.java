package uk.co.bty.mock.cybersource.rules.drools;

import java.util.Map;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

import com.google.common.collect.Maps;

public class KieSessionFactory
{
	private final KieBase defaultProfile;
	private final Map<String, KieBase> profiles = Maps.newConcurrentMap();

	public KieSessionFactory(final KieBase defaultProfile, final Map<String, KieBase> profiles)
	{
		this.defaultProfile = defaultProfile;
		this.profiles.putAll(profiles);
	}

	public KieSession getSession(final String profile)
	{
		return profiles.getOrDefault(profile, defaultProfile).newKieSession();
	}
}
