package osoba;

public enum TipOsobe {
	DISPECER,
	VOZAC,
	MUSTERIJA;
	
	public static TipOsobe getTipOsobe(int a) {
		switch (a) {
		case 1:
			return DISPECER;
		case 2:
			return VOZAC;
		default:
			return MUSTERIJA;
		}
	}
	
	public static int toInt(TipOsobe tipOsobe) {
		switch (tipOsobe) {
		case DISPECER:
			return 1;
		case VOZAC:
			return 2;
		default:
			return 3;
		}
	}
}
