package gui;

import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import osoba.Dispecer;
import osoba.Musterija;
import osoba.Osoba;
import osoba.PolOsobe;
import osoba.TipOsobe;
import taksiSluzba.TaksiSluzba;

public class MusterijeIzmenaProzor extends JFrame {
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblJMBG;
	private JLabel lblAdresa;
	private JLabel lblPol;
	private JLabel lblBrojTelefona;
	private JLabel lblKorisnickoIme;
	private JLabel lblLozinka;
	private JLabel lblKoristiApp;
	private JComboBox<String> cbKoristiMobApp;
	private JComboBox< PolOsobe> cbPol;
	private JButton btnOk,btnCancel;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtJMBG;
	private JTextField txtAdresa;
	private JTextField txtBrojTelefona;
	private JTextField txtKorisnickoIme;
	private JTextField txtLozinka;
	private boolean zauzeto;
	
	private TaksiSluzba taksiSluzba;
	private Musterija musterijaZaIzmenu;
	
	public MusterijeIzmenaProzor(TaksiSluzba taksiSluzba, Musterija musterijaZaIzmenu) {
		this.taksiSluzba = taksiSluzba;
		this.musterijaZaIzmenu = musterijaZaIzmenu;
		if(this.musterijaZaIzmenu == null) {
			setTitle("Musterije - Dodavanje");
		}else {
			setTitle("Musterije - Izmena");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
		setResizable(false);
		}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]","[][]20[]");
		setLayout(mig);
		lblIme = new JLabel("Ime");
		lblPrezime = new JLabel("Prezime");
		lblJMBG = new JLabel("JMBG");
		lblAdresa = new JLabel("Adresa");
		lblPol = new JLabel("Pol");
		lblBrojTelefona = new JLabel("Broj telefona");
		lblKorisnickoIme = new JLabel("Korisnicko ime");
		lblLozinka = new JLabel("Lozinka");
		lblKoristiApp = new JLabel("Koristi aplikaciju:");
		
		txtIme = new JTextField(20);
		txtPrezime = new JTextField(20);
		txtJMBG = new JTextField(20);
		txtAdresa = new JTextField(20);
		txtBrojTelefona = new JTextField(20);
		txtKorisnickoIme = new JTextField(20);
		txtLozinka = new JTextField(20);
		cbPol = new JComboBox<PolOsobe>();
		cbPol.addItem(PolOsobe.MUSKO);
		cbPol.addItem(PolOsobe.ZENSKO);
		cbKoristiMobApp = new JComboBox<String>();
		cbKoristiMobApp.addItem("Koristi");
		cbKoristiMobApp.addItem("Ne koristi");
		//for(Musterija musterija : taksiSluzba.getMusterije()) {
			
			//cbKoristiMobApp.addItem(musterija.isKoristiAplikaciju());
		//}
		
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		
		if(musterijaZaIzmenu != null) {
			txtIme.setText(musterijaZaIzmenu.getIme());
			txtPrezime.setText(musterijaZaIzmenu.getPrezime());
			txtJMBG.setText(musterijaZaIzmenu.getJMBG());
			txtAdresa.setText(musterijaZaIzmenu.getAdresa());
			cbPol.setSelectedItem(musterijaZaIzmenu.getPol());
			txtBrojTelefona.setText(musterijaZaIzmenu.getBrojTelefona());
			txtKorisnickoIme.setText(musterijaZaIzmenu.getKorisnickoIme());
			txtLozinka.setText(musterijaZaIzmenu.getLozinka());
			//TODO za selected(iz AutomobiliIzmenaProzor)
		}
		
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJMBG);
		add(txtJMBG);
		add(lblAdresa);
		add(txtAdresa);
		add(lblPol);
		add(cbPol);
		add(lblBrojTelefona);
		add(txtBrojTelefona);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(txtLozinka);
		add(cbKoristiMobApp);
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijeIzmenaProzor.this.dispose();
				MusterijeIzmenaProzor.this.setVisible(false);
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacijaPodataka(txtIme.getText().trim(), txtPrezime.getText().trim(),txtJMBG.getText().trim(), txtAdresa.getText().trim(),txtBrojTelefona.getText().trim(), txtKorisnickoIme.getText().trim(), txtLozinka.getText().trim())) {
				try {
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String JMBG = txtJMBG.getText().trim();
					String adresa = txtAdresa.getText().trim();
					PolOsobe pol = PolOsobe.valueOf(cbPol.getSelectedItem().toString());
					String brojTelefona = txtBrojTelefona.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = txtLozinka.getText().trim();
					TipOsobe tip = TipOsobe.MUSTERIJA;
					String koristiMobAppString = cbKoristiMobApp.getSelectedItem().toString();
				
					
					boolean koristiMobApp = false;
					if(koristiMobAppString.equals("Koristi")) {
						koristiMobApp = true;
					}
					zauzeto = false;
					if (musterijaZaIzmenu == null) {
						if(taksiSluzba.isZauzetoKorisnickoIme(korisnickoIme)) {
							zauzeto = true;
						}else {
							Musterija musterija = new Musterija(tip, ime, prezime, JMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka, koristiMobApp);
							taksiSluzba.getMusterije().add(musterija);
							taksiSluzba.getOsobe().add(musterija); 
							
						}
					}else{
						if(taksiSluzba.isZauzetoKorisnickoIme(korisnickoIme) && !musterijaZaIzmenu.getKorisnickoIme().equals(korisnickoIme)) {
							zauzeto = true;
						}else {
						musterijaZaIzmenu.setIme(ime);
						musterijaZaIzmenu.setPrezime(prezime);
						musterijaZaIzmenu.setAdresa(adresa);
						musterijaZaIzmenu.setJMBG(JMBG);
						musterijaZaIzmenu.setPol(pol);
						musterijaZaIzmenu.setBrojTelefona(brojTelefona);
						musterijaZaIzmenu.setKorisnickoIme(korisnickoIme);
						musterijaZaIzmenu.setLozinka(lozinka);
						musterijaZaIzmenu.setKoristiAplikaciju(koristiMobApp);
						}
						}
					
					
					
					
					
					if(zauzeto == true) {
						MusterijeIzmenaProzor.this.setVisible(true);
						JOptionPane.showMessageDialog(null, "Korisnicko ime zauzeto", "Greska" , JOptionPane.ERROR_MESSAGE);
					}else {
						taksiSluzba.snimiOsobe();
						MusterijeIzmenaProzor.this.dispose();
						MusterijeIzmenaProzor.this.setVisible(false);
					}
					
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Pogresno uneti podaci", "", JOptionPane.WARNING_MESSAGE);
				}
				} else {
					JOptionPane.showMessageDialog(null, "Pogresno uneti podaci", "", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
	}
	public boolean validacijaPodataka(String ime,String prezime,String JMBG,String adresa,String brojTelefona,String korisnickoIme,String lozinka) {
		if (ime.isEmpty()  == true || prezime.isEmpty() == true || JMBG.isEmpty() == true || adresa.isEmpty() == true || brojTelefona.isEmpty() == true || korisnickoIme.isEmpty() == true || lozinka.isEmpty() == true) {
			return false;
		} else {
			try {
				Long.parseLong(JMBG);
				Long.parseLong(brojTelefona);
				return true;
			} catch (Exception NumberFormatException) {
				System.out.println("greska");
				return false;
			}
		}
		}
	}
