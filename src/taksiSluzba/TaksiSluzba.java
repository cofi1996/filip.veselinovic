package taksiSluzba;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.plaf.synth.SynthSpinnerUI;

import osoba.Dispecer;
import osoba.Musterija;
import osoba.OdeljenjeDispecera;
import osoba.Vozac;
import osoba.Osoba;
import osoba.PolOsobe;
import osoba.TipOsobe;
import automobil.Automobil;
import automobil.VrstaAutomobila;
import gui.LoginProzor;
import taksiSluzba.Voznja;



public class TaksiSluzba {
	
		public String naziv;
		public String PIB;
		public String adresa;

		private ArrayList<Dispecer> dispeceri;
		private ArrayList<Musterija> musterije;
		private ArrayList<Vozac> vozaci;
		private ArrayList<Osoba> osobe;
		private ArrayList<Automobil> automobili;
		private ArrayList<Voznja> voznje;
		private ArrayList<Voznja> nedodeljeneVoznje;
		private ArrayList<Voznja> voznjeVozaca;
		private ArrayList<Voznja> voznjeMusterije;
		private Osoba ulogovan;
		
		
		
		public TaksiSluzba(){
			this.naziv = naziv;
			this.adresa = adresa;
			this.PIB = PIB;
			this.dispeceri = new ArrayList<Dispecer>();
			this.musterije = new ArrayList<Musterija>();
			this.vozaci = new ArrayList<Vozac>();
			this.osobe = new ArrayList<Osoba>();
			this.automobili = new ArrayList<Automobil>();
			this.voznje = new ArrayList<Voznja>();
			this.nedodeljeneVoznje = new ArrayList<Voznja>();
			this.voznjeMusterije = new ArrayList<Voznja> ();
			this.voznjeVozaca = new ArrayList<Voznja>();
			this.ulogovan = ulogovan;
		}
		
		
		public void ucitajTSpodatke() {
			try {
				File podaciFile = new File("src/fajlovi/TSpodaci");
				BufferedReader br = new BufferedReader(new FileReader(podaciFile));
				String line = null;
				while((line = br.readLine()) != null) {
					String[] split = line.split("\\|");
					naziv = split[0];
					PIB = split[1];
					adresa = split[2];
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void snimiTSpodatke() {
			try{
			File podaciFile = new File("src/fajlovi/TSpodaci");
			String content  = getNaziv() + "|" + getPIB() + "|" + getAdresa();
			BufferedWriter writer = new BufferedWriter(new FileWriter(podaciFile));
			writer.write(content);
			writer.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		public String getNaziv() {
			return naziv;
		}


		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}


		public String getPIB() {
			return PIB;
		}


		public void setPIB(String pIB) {
			PIB = pIB;
		}


		public String getAdresa() {
			return adresa;
		}


		public void setAdresa(String adresa) {
			this.adresa = adresa;
		}


		public  void ucitajOsobe() {
			try {
			File osobeFile = new File("src/fajlovi/osobe");
			BufferedReader br = new BufferedReader(new FileReader(osobeFile));
			String line = null;
			while((line = br.readLine()) != null) {
				
				String[] split = line.split("\\|");
				System.out.println(split[0]);
				int tipInt = Integer.parseInt(split[0]);
				String ime = split[1];
				String prezime = split[2];
				String JMBG = split[3];
				String adresa = split[4];
				int polInt = Integer.parseInt(split[5]);
				PolOsobe pol = PolOsobe.getPolOsobe(polInt);
				String brojTelefona = split[6];
				String korisnickoIme = split[7];
				String lozinka = split[8];
				if(tipInt == 1) {
					TipOsobe tip = TipOsobe.getTipOsobe(1);
					int plata = Integer.parseInt(split[9]);
					String brojTelefonskeLinije = split[10];
					int odeljenjeInt= Integer.parseInt(split[11]);
					OdeljenjeDispecera odeljenje = OdeljenjeDispecera.getOdeljenjeDispecera(odeljenjeInt);
					Dispecer dispecer = new Dispecer(tip,ime,prezime,JMBG,adresa,pol,brojTelefona,korisnickoIme,lozinka,plata,brojTelefonskeLinije,odeljenje);
					dispeceri.add(dispecer);
					osobe.add(dispecer);
					//System.out.println(dispecer1);
					
				}else if(tipInt == 2) {
					TipOsobe tip = TipOsobe.getTipOsobe(2);
					int plata = Integer.parseInt(split[9]);
					String brojClanskeKarte = split[10];
					int voziloVozaca = Integer.parseInt(split[11]);
					for(Automobil automobil : automobili) {
						if(automobil.getBrojTaksiVozila() == voziloVozaca) {
							Vozac vozac = new Vozac(tip, ime, prezime, JMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka, plata, brojClanskeKarte, automobil);
							vozaci.add(vozac);
							osobe.add(vozac);
						}
					}
					//Vozac vozac = new Vozac(tip,ime,prezime,adresa,JMBG,pol,brojTelefona,korisnickoIme,lozinka, plata,brojClanskeKarte,automobil);
					//vozaci.add(vozac);
					//osobe.add(vozac);
					//System.out.println(vozaci);
				} else {
					TipOsobe tip = TipOsobe.getTipOsobe(3);
					int daLiKoristiMobAppInt = Integer.parseInt(split[9]);
					boolean daLiKoristiMobApp = false;
					if (daLiKoristiMobAppInt == 1) {
						daLiKoristiMobApp = true;
					} 
					Musterija musterija = new Musterija(tip,ime,prezime,JMBG,adresa,pol,brojTelefona,korisnickoIme,lozinka,daLiKoristiMobApp);
					//System.out.println(musterija1);
					musterije.add(musterija);
					osobe.add(musterija);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		public void snimiOsobe() {
			try {
				File osobeFile = new File("src/fajlovi/osobe");
				String content  = "";
				for(Dispecer dispecer : dispeceri) {
					int pol = PolOsobe.toInt(dispecer.getPol());
					int odeljenje = OdeljenjeDispecera.toInt(dispecer.getOdeljenje());
					//if(dispecer.getOdeljenje().equals(OdeljenjeDispecera.PRIJEM)) {
						//odeljenje = 1;
					content += "1|" + dispecer.getIme() + "|" +
													dispecer.getPrezime() + "|" + 
													dispecer.getJMBG() + "|" + 
													dispecer.getAdresa() + "|" + 
													pol + "|" + 
													dispecer.getBrojTelefona() + "|" + 
													dispecer.getKorisnickoIme() + "|" + 
													dispecer.getLozinka() + "|" + 
													dispecer.getPlata() + "|" +
													dispecer.getBrojTelLinije() + "|" +
													odeljenje + "\n";
				}
				for(Vozac vozac : vozaci) {
					int pol = PolOsobe.toInt(vozac.getPol());
					content  += "2|" +vozac.getIme() + "|" +
													vozac.getPrezime() + "|" +
													vozac.getJMBG() + "|" +
													vozac.getAdresa() + "|" +
													pol + "|" +
													vozac.getBrojTelefona() + "|" +
													vozac.getKorisnickoIme() + "|" +
													vozac.getLozinka() + "|" +
													vozac.getPlata() + "|" +
													vozac.getBrojClanskeKarte() + "|" +
													vozac.getAutomobil().getBrojTaksiVozila() + "\n";
													
				}
				for(Musterija musterija : musterije ) {
					int pol = PolOsobe.toInt(musterija.getPol());
					int koristiApp = 0;
					if(musterija.isKoristiAplikaciju() == true) {
						koristiApp = 1;
					}
					content += "3|" +musterija.getIme() + "|" +
													musterija.getPrezime() + "|" +
													musterija.getJMBG() + "|" +
													musterija.getAdresa() + "|" +
													pol + "|" +
													musterija.getBrojTelefona() + "|" +
													musterija.getKorisnickoIme() + "|" +
													musterija.getLozinka() + "|" +
													koristiApp + "\n";
				}
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(osobeFile));
				writer.write(content);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		public Dispecer pronadjiDispecera(String ime) {
			for(Dispecer dispecer : dispeceri) {
				if(dispecer.getIme().equals(ime)){
					return dispecer;
				}
			}
			return null;
		}
		
		public Vozac pronadjiVozaca(String ime) {
			for(Vozac vozac : vozaci) {
				if(vozac.getIme().equals(ime)) {
					return vozac;
				}
			}
			return null;
		}
		
		public Musterija pronadjiMusteriju(String ime) {
			for(Musterija musterija : musterije) {
				if(musterija.getIme().equals(ime)) {
					return musterija;
				}
			}
			return null;
		}
		
		public Voznja pronadjiVoznju(String datum) {
			for(Voznja voznja : voznje) {
				if(voznja.getVreme().equals(datum)) {
					return voznja;
				}
			}
			return null;
		}
		
		public Automobil pronadjiAutomobil(String brojRegOznake) {
			for(Automobil automobil : automobili) {
				if(automobil.getBrojRegistarskeOznake().equals(brojRegOznake)) {
					return automobil;
				}
			}
			return null;
		}
		
		public Automobil pronadjiAutomobilPoBroju(int brojTaksija) {
			for(Automobil automobil : automobili) {
				if(automobil.getBrojTaksiVozila() == brojTaksija) {
					return automobil;
				}
			}
			return null;
		}
		
		
		
		
		public int login(String korisnickoIme, String lozinka) {
			for(Osoba osoba : osobe) {
				if(osoba.getKorisnickoIme().equals(korisnickoIme) && osoba.getLozinka().equals(lozinka)) {
					//ulogovan = osoba;
					if(osoba.getTip().equals(TipOsobe.DISPECER)){
						return 1;
					} else if(osoba.getTip().equals(TipOsobe.VOZAC)) {
						return 2;
					} else{
						return 3;
					}
				}
			}
			return 0;
		}
		
		


		public void ucitajAutomobile() {
			try {
				File automobiliFile = new File("src/fajlovi/automobili");
				BufferedReader br = new BufferedReader(new FileReader(automobiliFile));
				String line = null;
				while((line = br.readLine()) != null) {
					String[] split = line.split("\\|");
					String model = split[0];
					String proizvodjac = split[1];
					int godinaProizvodnje = Integer.parseInt(split[2]);
					String brojRegistarskeOznake = split[3];
					int brojTaksiVozila = Integer.parseInt(split[4]);
					VrstaAutomobila vrstaAutomobila = VrstaAutomobila.getVrstaAutomobila(Integer.parseInt(split[5]));
					Automobil automobil = new Automobil(model,proizvodjac, godinaProizvodnje, brojRegistarskeOznake, brojTaksiVozila, vrstaAutomobila);
					automobili.add(automobil);	
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void snimiAutomobile() {
			try{
			File automobiliFile = new File("src/fajlovi/automobili");
			String content = "";
			for(Automobil automobil : automobili) {
			int vrstaAutomobila = VrstaAutomobila.toInt(automobil.getVrstaAutomobila());
				content += automobil.getModel() + "|" +
									automobil.getProizvodjac() + "|" +
									automobil.getGodinaProizvodnje() + "|" +
									automobil.getBrojRegistarskeOznake() + "|" +
									automobil.getBrojTaksiVozila() + "|" +
									vrstaAutomobila + "\n";
				
			}
			BufferedWriter writter = new BufferedWriter(new FileWriter(automobiliFile));
			writter.write(content);
			writter.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void ucitajVoznje() {
			try {
				File voznjeFile = new File("src/fajlovi/voznje");
				BufferedReader br = new BufferedReader(new FileReader(voznjeFile));
				String line = null;
				while((line = br.readLine()) != null) {
					String[] splitPoL = line.split("\\|");
					String[] splitPoC = line.split("\\-");
					
					String datum_vreme = splitPoL[0];
					//GregorianCalendar vreme = new GregorianCalendar();
					//SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
					//vreme.setTime(format.parse(datum_vreme));
					//format.format(vreme.getTime());
					

					String  mestoPolaska = splitPoL[1];
					String musterija = splitPoL[2];
					String vozac = splitPoL[3];
					if(splitPoL.length == 5) {
						String dispecer = splitPoL[4];
						Voznja voznja = new Voznja(datum_vreme, mestoPolaska, musterija, vozac, dispecer);
						voznje.add(voznja);
						if(voznja.getVozac().equals(ulogovan.getKorisnickoIme())) {
							voznjeVozaca.add(voznja);
						}
					} else {
						String koordinate_sirina_duzina = splitPoL[4];
						String[] koordinate = koordinate_sirina_duzina.split("-");
						double koordinate_sirina = Double.parseDouble(koordinate[0]);
						double koordinate_duzina = Double.parseDouble(koordinate[1]);
						String napomena = splitPoL[5];
						Voznja voznja = new Voznja(datum_vreme, mestoPolaska, musterija, vozac, koordinate_duzina, koordinate_sirina, napomena);
						voznje.add(voznja);
						if(voznja.getVozac().equals("")) {
							nedodeljeneVoznje.add(voznja);
							//System.out.println(ulogovan.getIme());
							if(voznja.getMusterija().equals(ulogovan.getKorisnickoIme())){
								voznjeMusterije.add(voznja);							
								}
						}else if(voznja.getVozac().equals(ulogovan.getKorisnickoIme())) {
								voznjeVozaca.add(voznja);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public ArrayList<Voznja> getVoznjeVozaca() {
			return voznjeVozaca;
		}

		public void setVoznjeVozaca(ArrayList<Voznja> voznjeVozaca) {
			this.voznjeVozaca = voznjeVozaca;
		}
		
		
		public void pronadjiSlobodnaVozila() {
			//TODO
		}

		public void snimiVoznje() {
			try{
			File voznjeFile = new File("src/fajlovi/voznje");
			String content  = "";
			
			for(Voznja voznja : voznje) {
				if(voznja.getDispecer() == null || voznja.getDispecer().toString().equals("")){
					
					content += voznja.getVreme()+ "|" +
										voznja.getAdresaPolaska() + "|" + 
										voznja.getMusterija() + "|" + 
										voznja.getVozac() + "|" + 
										voznja.getNadmorskaDuzina() + "-" +
										voznja.getNadmorskaSirina() + "|" +
										voznja.getNapomena() + "\n";
				}else{
					content += voznja.getVreme()+ "|" +
							voznja.getAdresaPolaska() + "|" + 
							voznja.getMusterija() + "|" + 
							voznja.getVozac() + "|" + 
							voznja.getDispecer() + "\n";
				}	
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(voznjeFile));
			writer.write(content);
			writer.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		public boolean isZauzetoKorisnickoIme(String korisnickoIme) {
			for(Osoba osoba : osobe) {
				if(osoba.getKorisnickoIme().equals(korisnickoIme)) {
					return true;
				}
			}
			return false;
		}
		
		public boolean isZauzetAutomobil(String brojRegistarskeOznake, int brojTaksiVozila) {
			for(Automobil automobil : automobili) {
				if(automobil.getBrojRegistarskeOznake().equals(brojRegistarskeOznake) &&  automobil.getBrojTaksiVozila() != brojTaksiVozila) {
					return true;
				}
			}
			return false;
		}
		
		public boolean isPostojeDispecerVozacMusterija(String dispecer, String vozac , String musterija) {
			for(Osoba osoba : osobe) {
				if((osoba.getKorisnickoIme().equals(dispecer) && osoba instanceof Dispecer) || dispecer == null || dispecer.equals("")){
					System.out.println(osoba.getKorisnickoIme());
					for(Osoba osoba2: osobe) {
						if((osoba2.getKorisnickoIme().equals(vozac) && osoba2 instanceof Vozac) || vozac.equals("")) {
							System.out.println(osoba2.getKorisnickoIme());
							for(Osoba osoba3: osobe) {
								if(osoba3.getKorisnickoIme().equals(musterija) && osoba3 instanceof Musterija){
									System.out.println(osoba3.getKorisnickoIme());
									return true;
								}
							}
						}
					}
				}
			}
			return false;
		}
		
		public double generisiKoordinatnuVrednost() {
			Random r = new Random();
			double low = 12.564861;
			double high = 45.156848;
			double result = r.nextDouble() * (high - low) + low;
			return result;
		}
		
		public boolean isZauzetAutomobil(int brojTaksiVozila) {
			for(Vozac vozac : vozaci) {
				if(vozac.getAutomobil().getBrojTaksiVozila() == brojTaksiVozila) {
					return true;
				}
			}
			return false;
		}
		
		public boolean proveriValidnostUnetogDatuma(String vreme) {
			GregorianCalendar datum = new GregorianCalendar();
			SimpleDateFormat sablon = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
			try{
				datum.setTime(sablon.parse(vreme));
				return true;
			}catch (Exception e) {
				return false;
			}
		}
		
		
		
		public ArrayList<Voznja> getVoznjeMusterije() {
			return voznjeMusterije;
		}

		public void setVoznjeMusterije(ArrayList<Voznja> voznjeMusterije) {
			this.voznjeMusterije = voznjeMusterije;
		}
		
		public Osoba getUlogovan() {
			return ulogovan;
		}

		public void setUlogovan(Osoba ulogovan) {
			this.ulogovan = ulogovan;
		}

		public ArrayList<Voznja> getNedodeljeneVoznje() {
			return nedodeljeneVoznje;
		}

		public void setNedodeljeneVoznje(ArrayList<Voznja> nedodeljeneVoznje) {
			this.nedodeljeneVoznje = nedodeljeneVoznje;
		}

		public ArrayList<Voznja> getVoznje() {
			return voznje;
		}

		public void setVoznje(ArrayList<Voznja> voznje) {
			this.voznje= voznje;
		}
		

		public ArrayList<Automobil> getAutomobili() {
			return automobili;
		}

		public void setAutomobili(ArrayList<Automobil> automobili) {
			this.automobili = automobili;
		}

		public ArrayList<Dispecer> getDispeceri() {
			return dispeceri;
		}

		public void setDispeceri(ArrayList<Dispecer> dispeceri) {
			this.dispeceri = dispeceri;
		}

		public ArrayList<Musterija> getMusterije() {
			return musterije;
		}

		public void setMusterije(ArrayList<Musterija> musterije) {
			this.musterije = musterije;
		}

		public ArrayList<Vozac> getVozaci() {
			return vozaci;
		}

		public void setVozaci(ArrayList<Vozac> vozaci) {
			this.vozaci = vozaci;
		}

		public ArrayList<Osoba> getOsobe() {
			return osobe;
		}

		public void setOsobe(ArrayList<Osoba> osobe) {
			this.osobe = osobe;
		}

		@Override
		public String toString() {
			return "TaksiSluzba [osobe=" + osobe + "]";
		}
	}
