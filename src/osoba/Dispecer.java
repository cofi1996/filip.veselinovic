package osoba;

public class Dispecer extends Osoba {
	private int plata;
	private String brojTelLinije;
	private OdeljenjeDispecera odeljenje;
	
	public Dispecer() {
		this.plata = 0;
		this.brojTelLinije = "";
	}
	
	public Dispecer(TipOsobe tip, String ime, String prezime, String JMBG,
			String adresa,PolOsobe pol, String brojTelefona,
			String korisnickoIme, String lozinka, int plata, String brojTelLinije, OdeljenjeDispecera odeljenje) {
		super(tip,ime,prezime,JMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka);
		this.plata = plata;
		this.brojTelLinije = brojTelLinije;
		this.odeljenje = odeljenje;
	}
	
	public Dispecer(Dispecer original) {
		super(original);
		this.plata = original.plata;
		this.brojTelLinije = original.brojTelLinije;
		this.odeljenje = original.odeljenje;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public String getBrojTelLinije() {
		return brojTelLinije;
	}

	public void setBrojTelLinije(String brojTelLinije) {
		this.brojTelLinije = brojTelLinije;
	}



	public OdeljenjeDispecera getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(OdeljenjeDispecera odeljenje) {
		this.odeljenje = odeljenje;
	}

	@Override
	public String toString() {
		return "Dispecer [plata=" + plata + ", brojTelLinije=" + brojTelLinije + ", odeljenje=" + odeljenje + ", ime="
				+ ime + ", prezime=" + prezime + ", JMBG=" + JMBG + ", adresa=" + adresa + ", pol=" + pol
				+ ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}


	
	
	
}
