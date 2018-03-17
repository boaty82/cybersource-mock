package uk.co.bty.mock.cybersource.rules.drools;

import java.util.Optional;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.drools.core.io.impl.ClassPathResource;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;

import uk.co.bty.mock.cybersource.rules.RuleType;

import static org.slf4j.LoggerFactory.getLogger;

public class KieBaseBuilder
{
	private static final Logger LOG = getLogger(KieBaseBuilder.class);

	private static final String PROFILE_FILE_PATH = "drools/%s/%s.drl";
	private static final String DEFAULT_FILE_PATH = "drools/%s.drl";

	private KieBaseBuilder()
	{
	}

	public static KieBase createService(final String profile, final KieServices kieService)
	{
		final KieFileSystem kfs = kieService.newKieFileSystem();
		Stream.of(RuleType.values())
				.map(type -> getPath(profile, type))
				.peek(typePath -> LOG.debug("profile [{}], type [{}], will use rules located in [{}]", profile, typePath.getLeft(), typePath.getRight()))
				.map(ImmutablePair::getRight)
				.map(ClassPathResource::new)
				.forEach(kfs::write);

		final KieBuilder builder = kieService.newKieBuilder(kfs).buildAll();
		final ReleaseId relId = builder.getKieModule().getReleaseId();
		final KieContainer container = kieService.newKieContainer(relId);
		return container.getKieBase();
	}

	private static ImmutablePair<RuleType, String> getPath(final String profile, final RuleType ruleType)
	{
		final String type = ruleType.name().toLowerCase();
		final String profilePath = String.format(PROFILE_FILE_PATH, type, profile);
		return Optional.ofNullable(KieBaseBuilder.class.getResource(profilePath))
				.map(r -> ImmutablePair.of(ruleType, profilePath))
				.orElse(ImmutablePair.of(ruleType, String.format(DEFAULT_FILE_PATH, type)));
	}
}
