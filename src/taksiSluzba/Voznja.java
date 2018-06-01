package taksiSluzba;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Voznja {
	
	//protected GregorianCalendar vreme;
	protected String vreme;
	protected String adresaPolaska;
	protected String musterija;
	protected String vozac;
	protected String dispecer;
	protected double nadmorskaDuzina;
	protected double nadmorskaSirina;
	protected String napomena;
	
	public Voznja(String vreme, String adresaPolaska, String musterija, String vozac, String dispecer) {
		super();
		this.vreme = vreme;
		this.adresaPolaska = adresaPolaska;
		this.musterija = musterija;
		this.vozac = vozac;
		this.dispecer = dispecer;
	}
	
	public Voznja(String vreme, String adresaPolaska, String musterija, String vozac, double nadmorskaDuzina
			,double nadmorskaSirina, String napomena){
		super();
		this.vreme = vreme;
		this.adresaPolaska = adresaPolaska;
		this.musterija = musterija;
		this.vozac = vozac;
		this.nadmorskaDuzina = nadmorskaDuzina;
		this.nadmorskaSirina = nadmorskaSirina;
		this.napomena = napomena;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public String getAdresaPolaska() {
		return adresaPolaska;
	}

	public void setAdresaPolaska(String adresaPolaska) {
		this.adresaPolaska = adresaPolaska;
	}

	public String getMusterija() {
		return musterija;
	}

	public void setMusterija(String musterija) {
		this.musterija = musterija;
	}

	public String getVozac() {
		return vozac;
	}

	public void setVozac(String vozac) {
		this.vozac = vozac;
	}

	public String getDispecer() {
		return dispecer;
	}

	public void setDispecer(String dispecer) {
		this.dispecer = dispecer;
	}

	public double getNadmorskaDuzina() {
		return nadmorskaDuzina;
	}

	public void setNadmorskaDuzina(double nadmorskaDuzina) {
		this.nadmorskaDuzina = nadmorskaDuzina;
	}

	public double getNadmorskaSirina() {
		return nadmorskaSirina;
	}

	public void setNadmorskaSirina(double nadmorskaSirina) {
		this.nadmorskaSirina = nadmorskaSirina;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	@Override
	public String toString() {
		return "Voznja [vreme=" + vreme + ", adresaPolaska=" + adresaPolaska + ", musterija=" + musterija + ", vozac="
				+ vozac + ", dispecer=" + dispecer + ", nadmorskaDuzina=" + nadmorskaDuzina + ", nadmorskaSirina="
				+ nadmorskaSirina + ", napomena=" + napomena + "]";
	}
	
	

	
} 
