package uk.co.bty.mock.cybersource;

public interface Populator<SOURCE, TARGET>
{
	void populate(SOURCE source, TARGET target);
}
