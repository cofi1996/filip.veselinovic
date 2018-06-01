package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.Refreshable;
import javax.sound.midi.Synthesizer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;
import osoba.Dispecer;
import osoba.OdeljenjeDispecera;
import osoba.Osoba;
import osoba.PolOsobe;
import osoba.TipOsobe;
import taksiSluzba.TaksiSluzba;

public class DispeceriIzmenaProzor extends JFrame {
	
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblJMBG;
	private JLabel lblAdresa;
	private JLabel lblPol;
	private JLabel lblBrojTelefona;
	private JLabel lblKorisnickoIme;
	private JLabel lblLozinka;
	private JLabel lblPlata;
	private JLabel lblBrojTelLinije;
	private JLabel lblOdeljenje;
	private JComboBox<OdeljenjeDispecera> cbOdeljenje;
	private JButton btnOk,btnCancel;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtJMBG;
	private JTextField txtAdresa;
	private JComboBox<PolOsobe> cbPol;
	private JTextField txtBrojTelefona;
	private JTextField txtKorisnickoIme;
	private JTextField txtLozinka;
	private JTextField txtPlata;
	private JTextField txtBrojTelLinije;
	private boolean zauzeto;
	
	private TaksiSluzba taksiSluzba;
	private Dispecer dispecerZaIzmenu;
	
	public DispeceriIzmenaProzor(TaksiSluzba taksiSluzba, Dispecer dispecerZaIzmenu ) {
		this.taksiSluzba = taksiSluzba;
		this.dispecerZaIzmenu = dispecerZaIzmenu;
		if(this.dispecerZaIzmenu == null) {
			setTitle("Dispeceri - Dodavanje");
		}else {
			setTitle("Dispeceri - Izmena");
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
		lblPlata = new JLabel("Plata");
		lblBrojTelLinije = new JLabel("Broj telefonske linije");
		lblOdeljenje = new JLabel("Odeljenje ");
		
		txtIme = new JTextField(20);
		txtPrezime = new JTextField(20);
		txtJMBG = new JTextField(20);
		txtAdresa = new JTextField(20);
		cbPol = new JComboBox<PolOsobe>();
		txtBrojTelefona = new JTextField(20);
		txtKorisnickoIme = new JTextField(20);
		txtLozinka = new JTextField(20);
		txtPlata = new JTextField(20);
		txtBrojTelLinije = new JTextField(20);
		cbOdeljenje = new JComboBox<OdeljenjeDispecera>();
		cbPol.addItem(PolOsobe.MUSKO);
		cbPol.addItem(PolOsobe.ZENSKO);
		cbOdeljenje.addItem(OdeljenjeDispecera.PRIJEM);
		cbOdeljenje.addItem(OdeljenjeDispecera.REKLAMACIJE);
		
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		
		if(dispecerZaIzmenu != null) {
			txtIme.setText(dispecerZaIzmenu.getIme());
			txtPrezime.setText(dispecerZaIzmenu.getPrezime());
			txtJMBG.setText(dispecerZaIzmenu.getJMBG());
			txtAdresa.setText(dispecerZaIzmenu.getAdresa());
			cbPol.setSelectedItem(dispecerZaIzmenu.getPol());
			txtBrojTelefona.setText(dispecerZaIzmenu.getBrojTelefona());
			txtKorisnickoIme.setText(dispecerZaIzmenu.getKorisnickoIme());
			txtLozinka.setText(dispecerZaIzmenu.getLozinka());
			txtPlata.setText(String.valueOf(dispecerZaIzmenu.getPlata()));
			txtBrojTelLinije.setText(dispecerZaIzmenu.getBrojTelLinije());
			cbOdeljenje.setSelectedItem(dispecerZaIzmenu.getOdeljenje());
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
		add(lblPlata);
		add(txtPlata);
		add(lblBrojTelLinije);
		add(txtBrojTelLinije);
		add(lblOdeljenje);
		add(cbOdeljenje);
		add(btnOk, "split 2");
		add(btnCancel);
	}
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DispeceriIzmenaProzor.this.dispose();
				DispeceriIzmenaProzor.this.setVisible(false);
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacijaPodataka(txtIme.getText().trim(), txtPrezime.getText().trim(),txtJMBG.getText().trim(), txtAdresa.getText().trim(),txtBrojTelefona.getText().trim(), txtKorisnickoIme.getText().trim(), txtLozinka.getText().trim(), txtPlata.getText().trim(), txtBrojTelLinije.getText().trim())) {
				try {
					TipOsobe tip = TipOsobe.DISPECER;
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String JMBG = txtJMBG.getText().trim();
					String adresa = txtAdresa.getText().trim();
					PolOsobe pol = PolOsobe.valueOf(cbPol.getSelectedItem().toString());
					String brojTelefona = txtBrojTelefona.getText().trim();
					String korisnickoIme = txtKorisnickoIme.getText().trim();toString();
					String lozinka = txtLozinka.getText().trim();
					int plata = Integer.parseInt(txtPlata.getText().trim());
					String brojTelLinije = txtBrojTelLinije.getText().trim();
					OdeljenjeDispecera odeljenje = OdeljenjeDispecera.valueOf(cbOdeljenje.getSelectedItem().toString());
					zauzeto = false;
						if (dispecerZaIzmenu == null) {
							if(taksiSluzba.isZauzetoKorisnickoIme(korisnickoIme)){
								zauzeto = true;
							}else {
								Dispecer dispecer = new Dispecer(tip, ime, prezime, JMBG, adresa, pol, brojTelefona, korisnickoIme, lozinka, plata, brojTelLinije, odeljenje);
								taksiSluzba.getDispeceri().add(dispecer);
								taksiSluzba.getOsobe().add(dispecer);
							}
							}else{
								if(taksiSluzba.isZauzetoKorisnickoIme(korisnickoIme) && !dispecerZaIzmenu.getKorisnickoIme().equals(korisnickoIme)) {
									zauzeto = true;
								}else {
								dispecerZaIzmenu.setIme(ime);
								dispecerZaIzmenu.setPrezime(prezime);
								dispecerZaIzmenu.setJMBG(JMBG);
								dispecerZaIzmenu.setAdresa(adresa);
								dispecerZaIzmenu.setPol(pol);
								dispecerZaIzmenu.setBrojTelefona(brojTelefona);
								dispecerZaIzmenu.setKorisnickoIme(korisnickoIme);
								dispecerZaIzmenu.setLozinka(lozinka);
								dispecerZaIzmenu.setPlata(plata);
								dispecerZaIzmenu.setBrojTelLinije(brojTelLinije);
								dispecerZaIzmenu.setOdeljenje(odeljenje);
								}
							}			
						
						if(zauzeto == true) {
							DispeceriIzmenaProzor.this.setVisible(true);
							JOptionPane.showMessageDialog(null, "Korisnicko ime zauzeto", "Greska" , JOptionPane.ERROR_MESSAGE);
						}else {
							taksiSluzba.snimiOsobe();
							DispeceriIzmenaProzor.this.dispose();
							DispeceriIzmenaProzor.this.setVisible(false);
						}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Podaci pogresno uneti", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				} else {
					JOptionPane.showMessageDialog(null, "Podaci pogresno uneti", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
	}
	
	public boolean validacijaPodataka(String ime,String prezime,String JMBG,String adresa,String brojTelefona,String korisnickoIme,String lozinka, String plata, String brojTelefonskeLinije) {
		if (ime.isEmpty()  == true || prezime.isEmpty() == true || JMBG.isEmpty() == true || adresa.isEmpty() == true || brojTelefona.isEmpty() == true || korisnickoIme.isEmpty() == true || lozinka.isEmpty() == true || plata.isEmpty() == true || brojTelefonskeLinije.isEmpty() == true) {
			return false;
		} else {
			try {
				Long.parseLong(JMBG);
				Long.parseLong(brojTelefona);
				Long.parseLong(plata);
				Long.parseLong(brojTelefonskeLinije);
				return true;
			} catch (Exception NumberFormatException) {
				System.out.println("greska");
				return false;
			}
		}
		}
	
}
