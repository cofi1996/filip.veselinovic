package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.omg.IOP.TAG_JAVA_CODEBASE;

import net.miginfocom.swing.MigLayout;
import osoba.Dispecer;
import osoba.Musterija;
import osoba.Vozac;
import taksiSluzba.TaksiSluzba;
import taksiSluzba.Voznja;

public class VoznjeIzmenaProzor extends JFrame {
	private JLabel lblTip;
	private JLabel lblVreme;
	private JLabel lblAdresaPolaska;
	private JLabel lblMusterija;
	private JLabel lblVozac;
	private JLabel lblDispecer;
	private JLabel lblNadmorskaDuzina;
	private JLabel lblNadmorskaSirina;
	private JLabel lblNapomena;
	private JButton btnOk,btnCancel;
	private JTextField txtVreme;
	private JTextField txtAdresaPolaska;
	private JComboBox<String> cbMusterija;
	private JComboBox<String> cbVozac;
	private JComboBox<String> cbDispecer;
	private JTextField txtNadmorskaDuzina;
	private JTextField txtNadmorskaSirina;
	private JTextField txtNapomena;
	
	private TaksiSluzba taksiSluzba;
	private Voznja voznjaZaIzmenu;
	private boolean postojeci;
	private boolean validanDatum;
	
	public VoznjeIzmenaProzor(TaksiSluzba taksiSluzba, Voznja voznjaZaIzmenu, int rezim) {
		this.taksiSluzba = taksiSluzba;
		this.voznjaZaIzmenu = voznjaZaIzmenu;
		if(this.voznjaZaIzmenu == null) {
			setTitle("Voznje - Dodavanje");
		}else {
			setTitle("Voznje - Izmena");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(rezim);
		initActions(rezim);
		pack();
		setResizable(false);
		}
	
	public void initGUI(int rezim) {
		if(rezim == 1){
			MigLayout mig = new MigLayout("wrap 2", "[][]","[][][]");
			setLayout(mig);
		
			lblVreme = new JLabel("Vreme");
			lblAdresaPolaska = new JLabel("Adresa polaska");
			lblMusterija = new JLabel("Musterija");
			lblVozac = new JLabel("Vozac");
			lblDispecer = new JLabel("Dispcer");
		
			txtVreme = new JTextField(15);
			txtAdresaPolaska = new JTextField(15);
			cbDispecer = new JComboBox<String>();
			cbVozac = new JComboBox<String>();
			cbMusterija = new JComboBox<String>();
		//cbKoristiMobApp.addItem("Koristi");
		//cbKoristiMobApp.addItem("Ne koristi");
		//for(Musterija musterija : taksiSluzba.getMusterije()) {
			
			//cbKoristiMobApp.addItem(musterija.isKoristiAplikaciju());
		//}
		
			btnOk = new JButton("Ok");
			btnCancel = new JButton("Cancel");
		
			if(voznjaZaIzmenu != null) {
				
				txtVreme.setText(voznjaZaIzmenu.getVreme());
				txtAdresaPolaska.setText(voznjaZaIzmenu.getAdresaPolaska());
				for(Musterija musterija : taksiSluzba.getMusterije()) {
					if(musterija.isKoristiAplikaciju() == false) {
						cbMusterija.addItem(musterija.getKorisnickoIme());
					}
				}
				for(Vozac vozac : taksiSluzba.getVozaci()) {
					cbVozac.addItem(vozac.getKorisnickoIme());
				}
				for(Dispecer dispecer: taksiSluzba.getDispeceri()) {
					cbDispecer.addItem(dispecer.getKorisnickoIme());
				}
			
			}else {
				GregorianCalendar vreme = new GregorianCalendar();
				SimpleDateFormat sablon = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
				txtVreme.setText(sablon.format(vreme.getTime()));
				for(Musterija musterija : taksiSluzba.getMusterije()) {
					if(musterija.isKoristiAplikaciju() == false) {
						cbMusterija.addItem(musterija.getKorisnickoIme());
					}
				}
				for(Vozac vozac : taksiSluzba.getVozaci()) {
					cbVozac.addItem(vozac.getKorisnickoIme());
				}
				for(Dispecer dispecer : taksiSluzba.getDispeceri()) {
					cbDispecer.addItem(dispecer.getKorisnickoIme());
				}
				
			}
			add(lblVreme);
			add(txtVreme);
			add(lblAdresaPolaska);
			add(txtAdresaPolaska);
			add(lblMusterija);
			add(cbMusterija);
			add(lblVozac);
			add(cbVozac);
			add(lblDispecer);
			add(cbDispecer);
			add(btnOk, "split 2");
			add(btnCancel);
		
			}else if(rezim == 3) {
				if(ulogovanaMusterijaKoristiApp()) {
							MigLayout mig = new MigLayout("wrap 2", "[][]","[][][]");
							setLayout(mig);
							
							
						
							lblVreme = new JLabel("Vreme");
							lblAdresaPolaska = new JLabel("Adresa polaska");
							//lblNadmorskaDuzina = new JLabel("Nadmorska duzina");
							//lblNadmorskaSirina = new JLabel("Nadmorska sirina");
							lblNapomena = new JLabel("Napomena");
						
							txtVreme = new JTextField(15);
							txtAdresaPolaska = new JTextField(15);
							//txtNadmorskaDuzina= new JTextField(15);
							//txtNadmorskaSirina = new JTextField(15);		
							txtNapomena = new JTextField(15);
					
						
							btnOk = new JButton("Ok");
							btnCancel = new JButton("Cancel");
						
							if(voznjaZaIzmenu != null) {
								txtVreme.setText(voznjaZaIzmenu.getVreme());
								txtAdresaPolaska.setText(voznjaZaIzmenu.getAdresaPolaska());
								//txtNadmorskaDuzina.setText(String.valueOf(voznjaZaIzmenu.getNadmorskaDuzina()));
								//txtNadmorskaSirina.setText(String.valueOf(voznjaZaIzmenu.getNadmorskaSirina()));
								txtNapomena.setText(voznjaZaIzmenu.getNapomena());
							}
							else {
								GregorianCalendar vreme = new GregorianCalendar();
								SimpleDateFormat sablon = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
								txtVreme.setText(sablon.format(vreme.getTime()));
							}
						
							add(lblVreme);
							add(txtVreme);
							add(lblAdresaPolaska);
							add(txtAdresaPolaska);
							//add(lblNadmorskaDuzina);
							//add(txtNadmorskaDuzina);
							//add(lblNadmorskaSirina);
							//add(txtNadmorskaSirina);
							add(lblNapomena);
							add(txtNapomena);
							add(btnOk, "split 2");
							add(btnCancel);
						}
			}else {
				//System.out.println("aaaaaaa");
				MigLayout mig = new MigLayout("wrap 2", "[][]","[][][]");
				setLayout(mig);
				lblAdresaPolaska = new JLabel("Adresa polaska");
				txtAdresaPolaska = new JTextField(15);
				btnOk = new JButton("Ok");
				btnCancel = new JButton("Cancel");
				add(lblAdresaPolaska);
				add(txtAdresaPolaska);
				add(btnOk, "split 2");
				add(btnCancel);
			}

	}
	
	private boolean proveriValidnostUnetihPodataka(int rezim) {
		if(rezim == 1) {
		if(txtVreme.getText().equals("") || txtAdresaPolaska.getText().equals("") || cbMusterija.getSelectedItem().equals("")) {
			return false;
		}
		else {
			if(taksiSluzba.proveriValidnostUnetogDatuma(txtVreme.getText().trim())) {
				return true;
			}else {
				return false;
			}
		}
		
		}else if(rezim == 3) {
			//if(txtVreme.getText().equals("") || txtAdresaPolaska.getText().equals("") || txtNadmorskaDuzina.getText().equals("") || txtNadmorskaSirina.getText().equals("") || txtNapomena.getText().equals("")){
			if(txtVreme.getText().equals("") || txtAdresaPolaska.getText().equals("")  || txtNapomena.getText().equals("")){
				return false;
		}else {
			try{
				//Double.parseDouble(txtNadmorskaDuzina.getText());
				//Double.parseDouble(txtNadmorskaSirina.getText());
				taksiSluzba.proveriValidnostUnetogDatuma(txtVreme.getText().trim());
			}catch (Exception e) {
				return false;
			}
		}
			return true;
	}
		return true;
}
	
	public void initActions(int rezim) {
		if(rezim == 1) {
			btnCancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VoznjeIzmenaProzor.this.dispose();
					VoznjeIzmenaProzor.this.setVisible(false);
					
				}
			});
			
			btnOk.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(proveriValidnostUnetihPodataka(1)) {
					try {
						String vreme = txtVreme.getText().trim();
						String adresaPolaska = txtAdresaPolaska.getText().trim();
						String musterija = cbMusterija.getSelectedItem().toString().trim();
						String vozac = cbVozac.getSelectedItem().toString().trim();
						String	dispecer = cbDispecer.getSelectedItem().toString().trim();
						validanDatum = false;
						if(taksiSluzba.proveriValidnostUnetogDatuma(vreme)) {
							validanDatum = true;
						}else {
							validanDatum = false;
						}
					
						if (voznjaZaIzmenu == null) {
									if(taksiSluzba.isPostojeDispecerVozacMusterija(dispecer, vozac, musterija)) {
										postojeci = true;
										Voznja voznja = new Voznja(vreme, adresaPolaska, musterija, vozac, dispecer);
										taksiSluzba.getVoznje().add(voznja);
									}
							
						}else{
							if(taksiSluzba.isPostojeDispecerVozacMusterija(dispecer, vozac, musterija)) {
								postojeci = true;
								voznjaZaIzmenu.setVreme(vreme);
								voznjaZaIzmenu.setAdresaPolaska(adresaPolaska);
								voznjaZaIzmenu.setMusterija(musterija);
								voznjaZaIzmenu.setVozac(vozac);
								voznjaZaIzmenu.setDispecer(dispecer);
							}
							else {
								postojeci = false;
							}
						//	voznjaZaIzmenu.setKoristiAplikaciju(koristiMobApp);
							}
						
						
						
						if(postojeci == false) {
							JOptionPane.showMessageDialog(null, "Ne postojeci entineti(Dispecer, vozac ili musterija)", "Greska", JOptionPane.ERROR_MESSAGE);
						}else if(validanDatum == false) {
							JOptionPane.showMessageDialog(null, "Nije dobro unet format datuma, pokusajte ponovo" , "Greska", JOptionPane.ERROR_MESSAGE);
						}else {
							taksiSluzba.snimiVoznje();
							VoznjeIzmenaProzor.this.dispose();
							VoznjeIzmenaProzor.this.setVisible(false);
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "aaaaa", "aaaaaA", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Greska tokom unosa podataka", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				
				}
			});
		}else if(rezim == 3) {
			if(ulogovanaMusterijaKoristiApp()) {
				btnCancel.addActionListener(new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						VoznjeIzmenaProzor.this.dispose();
						VoznjeIzmenaProzor.this.setVisible(false);
						
					}
				});
				
				btnOk.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(proveriValidnostUnetihPodataka(3)) {
						try {
							String musterija = taksiSluzba.getUlogovan().getKorisnickoIme();
							String vreme = txtVreme.getText().trim();
							String adresaPolaska = txtAdresaPolaska.getText().trim();
							//double nadmorskaDuzina = Double.parseDouble(txtNadmorskaDuzina.getText().trim()) ;
							double nadmorskaDuzina = taksiSluzba.generisiKoordinatnuVrednost();
							double nadmorskaSirina = taksiSluzba.generisiKoordinatnuVrednost();
							//double nadmorskaSirina = Double.parseDouble(txtNadmorskaSirina.getText().trim());
							String napomena = txtNapomena.getText().trim();
						
							if (voznjaZaIzmenu == null) {
								Voznja voznja = new Voznja(vreme, adresaPolaska, musterija, "", nadmorskaDuzina, nadmorskaSirina, napomena);
								taksiSluzba.getVoznje().add(voznja);
							}else{
								voznjaZaIzmenu.setVreme(vreme);
								voznjaZaIzmenu.setAdresaPolaska(adresaPolaska);
								voznjaZaIzmenu.setMusterija(musterija);
								voznjaZaIzmenu.setNadmorskaDuzina(nadmorskaDuzina);
								voznjaZaIzmenu.setNadmorskaSirina(nadmorskaSirina);
								voznjaZaIzmenu.setNapomena(napomena);	
								}
							
							
							
							
							taksiSluzba.snimiVoznje();
							VoznjeIzmenaProzor.this.dispose();
							VoznjeIzmenaProzor.this.setVisible(false);
						} catch (NumberFormatException e2) {
							JOptionPane.showMessageDialog(null, "aaaaa", "aaaaaA", JOptionPane.WARNING_MESSAGE);
						}
					}else{
						JOptionPane.showMessageDialog(null, "Pogresan unos podataka", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					}
				});
			}else {
				
			}
		}
	}

	
	public boolean ulogovanaMusterijaKoristiApp() {
		for(Musterija musterija : taksiSluzba.getMusterije()) {
			if(musterija.equals(taksiSluzba.getUlogovan())) {
				if(musterija.isKoristiAplikaciju() == true) {
					return true;
				}
			}
		}
		return false;
	}


}

