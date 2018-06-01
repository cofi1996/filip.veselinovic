package automobil;

public class Automobil {
	
	protected String model;
	protected String proizvodjac;
	protected int godinaProizvodnje;
	protected String brojRegistarskeOznake;
	protected int brojTaksiVozila;
	protected enum vrstaAutomobila {PUTNICKO, KOMBI};
	public VrstaAutomobila vrstaAutomobila;
	 
	public Automobil() {
		this.model = "";
		this.proizvodjac = "";
		this.godinaProizvodnje = 0;
		this.brojRegistarskeOznake = "";
		this.brojTaksiVozila = 0;
	}
	
	public Automobil(String model, String proizvodjac, int godinaProizvodnje,
			String brojRegistarskeOznake, int brojTaksiVozila, VrstaAutomobila vrstaAutomobila) {
		this.model = model;
		this.proizvodjac = proizvodjac;
		this.godinaProizvodnje = godinaProizvodnje;
		this.brojRegistarskeOznake = brojRegistarskeOznake;
		this.brojTaksiVozila = brojTaksiVozila;
		this.vrstaAutomobila = vrstaAutomobila;
	}
	
	public Automobil(Automobil original) {
		this.model = original.model;
		this.proizvodjac = original.proizvodjac;
		this.godinaProizvodnje = original.godinaProizvodnje;
		this.brojRegistarskeOznake = original.brojRegistarskeOznake;
		this.brojTaksiVozila = original.brojTaksiVozila;
		this.vrstaAutomobila = original.vrstaAutomobila;
	}


	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProizvodjac() {
		return proizvodjac;
	}

	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}

	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}

	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public String getBrojRegistarskeOznake() {
		return brojRegistarskeOznake;
	}

	public void setBrojRegistarskeOznake(String brojRegistarskeOznake) {
		this.brojRegistarskeOznake = brojRegistarskeOznake;
	}

	public int getBrojTaksiVozila() {
		return brojTaksiVozila;
	}

	public void setBrojTaksiVozila(int brojTaksiVozila) {
		this.brojTaksiVozila = brojTaksiVozila;
	}

	public VrstaAutomobila getVrstaAutomobila() {
		return vrstaAutomobila;
	}

	public void setVrstaAutomobila(VrstaAutomobila vrstaAutomobila) {
		this.vrstaAutomobila = vrstaAutomobila;
	}

	@Override
	public String toString() {
		return "Automobil [model=" + model + ", proizvodjac=" + proizvodjac + ", godinaProizvodnje=" + godinaProizvodnje
				+ ", brojRegistarskeOznake=" + brojRegistarskeOznake + ", brojTaksiVozila=" + brojTaksiVozila
				+ ", vrstaAutomobila=" + vrstaAutomobila + "]";
	}



	
	
	
}
