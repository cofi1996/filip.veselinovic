package osoba;

public abstract class Osoba {
	
	protected TipOsobe tip;
	protected String ime;
	protected String prezime;
	protected String JMBG;
	protected String adresa;
	protected PolOsobe pol;
	protected String brojTelefona;
	protected String korisnickoIme;
	protected String lozinka;

	public Osoba() {
		this.ime = "";
		this.prezime = "";
		this.JMBG = "";
		this.adresa = "";
		this.brojTelefona = "";
		this.korisnickoIme = "";
		this.lozinka = "";
	}


	public Osoba(TipOsobe tip,String ime, String prezime, String JMBG, String adresa,
		PolOsobe pol, String brojTelefona, String korisnickoIme, String lozinka	) {
		super();
		this.tip = tip;
		this.ime = ime;
		this.prezime = prezime;
		this.JMBG = JMBG;
		this.adresa = adresa;
		this.pol = pol;
		this.brojTelefona = brojTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
	}
	
	public Osoba(Osoba original) {
		this.tip = original.tip;
		this.ime = original.ime;
		this.prezime = original.prezime;
		this.JMBG = original.JMBG;
		this.adresa = original.adresa;
		this.pol = original.pol;
		this.brojTelefona = original.brojTelefona;
		this.korisnickoIme = original.korisnickoIme;
		this.lozinka = original.lozinka;
	}




	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getJMBG() {
		return JMBG;
	}


	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}


	public String getAdresa() {
		return adresa;
	}


	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}


	public TipOsobe getTip() {
		return tip;
	}


	public void setTip(TipOsobe tip) {
		this.tip = tip;
	}


	public PolOsobe getPol() {
		return pol;
	}


	public void setPol(PolOsobe pol) {
		this.pol = pol;
	}


	public String getBrojTelefona() {
		return brojTelefona;
	}


	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}


	public String getKorisnickoIme() {
		return korisnickoIme;
	}


	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}


	public String getLozinka() {
		return lozinka;
	}


	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}


	@Override
	public String toString() {
		return "Osoba [tip=" + tip + ", ime=" + ime + ", prezime=" + prezime + ", JMBG=" + JMBG + ", adresa=" + adresa
				+ ", pol=" + pol + ", brojTelefona=" + brojTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka="
				+ lozinka + "]";
	}


	
}
