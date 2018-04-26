package uk.co.bty.mock.cybersource.stepdefs.util;

import java.math.BigInteger;

public class CardUtil {
    
    private CardUtil() {}

    public static BigInteger stringToNumber(final String value)
    {
        return new BigInteger(value.substring(value.indexOf(":") + 1));
    }
}
