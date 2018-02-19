package uk.co.bty.mock.cybersource;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
	public static void main(final String[] args)
	{
		new SpringApplicationBuilder()
				.sources(Application.class)
				.web(true)
				.registerShutdownHook(true)
				.run(args);
	}

}
