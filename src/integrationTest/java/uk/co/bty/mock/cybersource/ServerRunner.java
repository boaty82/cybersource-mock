package uk.co.bty.mock.cybersource;

import javax.annotation.PostConstruct;

public class ServerRunner
{
	@PostConstruct
	public void init()
	{
		Application.main(new String[]{ "--spring.boot.profiles.additional=cucumber" });
	}
}
