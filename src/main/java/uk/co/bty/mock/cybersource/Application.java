package uk.co.bty.mock.cybersource;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	private static final String ADDITIONAL_PROFILES_PROPERTY = "spring.boot.profiles.additional";

	public static void main(final String[] args)
	{
		final List<String> profiles = getProfiles(args);
		LOG.debug("profiles {}", profiles);
		new SpringApplicationBuilder()
				.sources(Application.class)
				.web(true)
				.registerShutdownHook(true)
				.profiles(profiles.toArray(new String[profiles.size()]))
				.run(args);
	}

	private static List<String> getProfiles(final String[] args)
	{
		return Arrays.stream(args)
				.filter(s -> StringUtils.contains(s, ADDITIONAL_PROFILES_PROPERTY))
				.map(s -> {
					final String[] parts = s.split("=");
					return parts.length == 2 ? parts[1] : null;
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
}

