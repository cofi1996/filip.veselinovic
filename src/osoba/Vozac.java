package osoba;

import automobil.Automobil;

public class Vozac extends Osoba {
	
	private int plata;
	private String brojClanskeKarte;
	private Automobil automobil;
	
	
	public Vozac() {
		this.plata = 0;
		this.brojClanskeKarte = "";
	}
	
	public Vozac(TipOsobe tip,String ime, String prezime, String JMBG, String adresa,
			PolOsobe pol, String brojTelefona, String korisnickoIme,
			String lozinka, int plata, String brojClanskeKarte, Automobil automobil) {
		super(tip, ime,prezime,JMBG,adresa,pol,brojTelefona,korisnickoIme,lozinka);
		this.plata = plata;
		this.brojClanskeKarte = brojClanskeKarte;
		this.automobil = automobil;
	}
	
	public Vozac(Vozac original) {
		super(original);
		this.plata = original.plata;
		this.brojClanskeKarte = original.brojClanskeKarte;
		this.automobil = original.automobil;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}

	public String getBrojClanskeKarte() {
		return brojClanskeKarte;
	}

	public void setBrojClanskeKarte(String brojClanskeKarte) {
		this.brojClanskeKarte = brojClanskeKarte;
	}


	public Automobil getAutomobil() {
		return automobil;
	}

	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}

	@Override
	public String toString() {
		return "Vozac [plata=" + plata + ", brojClanskeKarte=" + brojClanskeKarte + ", automobil=" + automobil
				+ ", ime=" + ime + ", prezime=" + prezime + ", JMBG=" + JMBG + ", adresa=" + adresa + ", pol=" + pol
				+ ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka + "]";
	}


	
	
}
