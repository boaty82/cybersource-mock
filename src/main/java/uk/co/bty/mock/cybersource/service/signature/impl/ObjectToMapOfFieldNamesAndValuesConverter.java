package uk.co.bty.mock.cybersource.service.signature.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;

public class ObjectToMapOfFieldNamesAndValuesConverter implements Converter<Object, Map<String, String>>
{
	@Override
	public Map<String, String> convert(final Object source)
	{
		final Map<String, String> map = new HashMap<>();
		for (final Field f : source.getClass().getDeclaredFields())
		{
			boolean wasAccessible = true;
			try
			{
				wasAccessible = f.isAccessible();
				if (!wasAccessible)
				{
					f.setAccessible(true);
				}
				map.put(f.getName(), (String) f.get(source));
			}
			catch (final IllegalAccessException e)
			{
				throw new ConversionFailedException(new TypeDescriptor(f), TypeDescriptor.forObject(map), null, e);
			}
			finally
			{
				if (!wasAccessible)
				{
					f.setAccessible(true);
				}
			}
		}
		return map;
	}
}
