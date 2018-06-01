package osoba;

public class Musterija extends Osoba {
	private boolean koristiAplikaciju;

	public Musterija(TipOsobe tip ,String ime, String prezime, String JMBG, 
			String adresa, PolOsobe pol, String brojTelefona, String korisnickoIme,
			String lozinka, boolean koristiAplikaciju) {
		super(tip,ime, prezime, JMBG, adresa, pol, brojTelefona,
				korisnickoIme, lozinka);
		this.koristiAplikaciju = koristiAplikaciju;
	}
	
	public Musterija(Musterija original) {
		super(original);
		this.koristiAplikaciju = original.koristiAplikaciju;
	}

	public boolean isKoristiAplikaciju() {
		return koristiAplikaciju;
	}

	public void setKoristiAplikaciju(boolean koristiAplikaciju) {
		this.koristiAplikaciju = koristiAplikaciju;
	}

	@Override
	public String toString() {
		return "Musterija [koristiAplikaciju=" + koristiAplikaciju + ", ime=" + ime + ", prezime=" + prezime + ", JMBG="
				+ JMBG + ", adresa=" + adresa + ", pol=" + pol + ", brojTelefona=" + brojTelefona + ", korisnickoIme="
				+ korisnickoIme + ", lozinka=" + lozinka + "]";
	}

	
	
}
