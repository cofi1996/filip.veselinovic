package automobil;

public enum VrstaAutomobila {
	PUTNICKO,
	KOMBI;
	
	public static VrstaAutomobila getVrstaAutomobila(int a) {
		switch(a) {
		case 1: 
			return PUTNICKO;
		default:
			return KOMBI;
		}
	}
	
	public static int toInt(VrstaAutomobila vrstaAutomobila) {
		switch (vrstaAutomobila) {
		case PUTNICKO:
			return 1;
		default:
			return 0;
		}
	}
}
