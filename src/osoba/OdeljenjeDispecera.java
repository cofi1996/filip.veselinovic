package osoba;

public enum OdeljenjeDispecera {
	PRIJEM,
	REKLAMACIJE;
	
	
	public static OdeljenjeDispecera getOdeljenjeDispecera(int a) {
		switch (a) {
		case 1:
			return PRIJEM;
		default:	
			return REKLAMACIJE;
		}
	}
	
	public static int toInt(OdeljenjeDispecera odeljenjeDispecera) {
		switch (odeljenjeDispecera) {
		case PRIJEM:
			return 1;
		default:
			return 0;
		}
	}
}
