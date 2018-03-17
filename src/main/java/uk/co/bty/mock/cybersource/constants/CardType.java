package uk.co.bty.mock.cybersource.constants;

import java.util.HashMap;
import java.util.Map;

public enum CardType
{
	VISA("001"),
	MASTERCARD("002"),
	AMEX("003"),
	DISCOVER("004"),
	DINERS("005"),
	CARTE_BLANCHE("006"),
	JCB("007"),
	ENROUTE("014"),
	JAL("021"),
	MAESTRO_UK("024"),
	NICOS("027"),
	DELTA("031"),
	VISA_ELECTRON("033"),
	DANKORT("034"),
	CARTE_BLEUE("036"),
	CARTA_SI("037"),
	MAESTRO_INTERNATIONAL("042"),
	GE_MONEY("043"),
	HIPERCARD("050"),
	ORICO("053"),
	ELO("054"),
	PRIVATE_LABEL("055");

	private static Map<String, CardType> map = new HashMap<>();

	static {
		for (CardType type : CardType.values()) {
			map.put(type.getCode(), type);
		}
	}

	public static CardType valueOfCode(String code) {
		return map.get(code);
	}

	private final String code;

	CardType(final String code)
	{
		this.code = code;
	}

	public String getCode()
	{
		return code;
	}
}
