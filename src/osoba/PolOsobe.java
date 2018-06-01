package osoba;

public enum PolOsobe {
	MUSKO,
	ZENSKO;
	
	public static PolOsobe getPolOsobe(int a) {
		switch (a) {
		case 1:
			return MUSKO;
		default:
			return ZENSKO;
		}
	}
	
	public static int toInt(PolOsobe polOsobe) {
		switch (polOsobe) {
		case MUSKO:
			return 1;
		default:
			return 0;
		}
	}
}
