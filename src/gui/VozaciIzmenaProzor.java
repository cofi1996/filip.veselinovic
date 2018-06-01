package gui;

import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.print.attribute.IntegerSyntax;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.TabableView;

import net.miginfocom.swing.MigLayout;
import osoba.Dispecer;
import osoba.Osoba;
import osoba.Vozac;
import taksiSluzba.TaksiSluzba;
import osoba.PolOsobe;
import osoba.TipOsobe;
import automobil.Automobil;

public class VozaciIzmenaProzor extends JFrame {
	private JLabel lblIme;
	private JLabel lblPrezime;
	private JLabel lblJMBG;
	private JLabel lblAdresa;
	private JLabel lblPol;
	private JLabel lblBrojTelefona;
	private JLabel lblKorisnickoIme;
	private JLabel lblLozinka;
	private JLabel lblPlata;
	private JLabel lblBrojClanskeKarte;
	private JComboBox<String> cbAutomobil;
	private JComboBox<PolOsobe> cbPol;
	private JButton btnOk,btnCancel;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtJMBG;
	private JTextField txtAdresa;
	private JTextField txtBrojTelefona;
	private JTextField txtKorisnickoIme;
	private JTextField txtLozinka;
	private JTextField txtPlata;
	private JTextField txtBrojClanskeKarte;
	private boolean zauzeto;
	
	private TaksiSluzba taksiSluzba;
	private Vozac vozacZaIzmenu;
	
	public VozaciIzmenaProzor(TaksiSluzba taksiSluzba, Vozac vozacZaIzmenu) {
		this.taksiSluzba = taksiSluzba;
		this.vozacZaIzmenu = vozacZaIzmenu;
		if(this.vozacZaIzmenu == null) {
			setTitle("Vozaci - Dodavanje");
		}else {
			setTitle("Vozaci - Izmena");
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
		lblBrojClanskeKarte = new JLabel("Broj clanske karte");
		
		txtIme = new JTextField(20);
		txtPrezime = new JTextField(20);
		txtJMBG = new JTextField(20);
		txtAdresa = new JTextField(20);
		txtBrojTelefona = new JTextField(20);
		txtKorisnickoIme = new JTextField(20);
		txtLozinka = new JTextField(20);
		txtPlata = new JTextField(20);
		txtBrojClanskeKarte = new JTextField(20);
		cbAutomobil = new JComboBox<String>();
		cbPol = new JComboBox<PolOsobe>();
		cbPol.addItem(PolOsobe.MUSKO);
		cbPol.addItem(PolOsobe.ZENSKO);
		
		for(Automobil automobil : taksiSluzba.getAutomobili()) {
			cbAutomobil.addItem(String.valueOf(automobil.getBrojTaksiVozila()));
		}
 		
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		
		if(vozacZaIzmenu != null) {
			txtIme.setText(vozacZaIzmenu.getIme());
			txtPrezime.setText(vozacZaIzmenu.getPrezime());
			txtJMBG.setText(vozacZaIzmenu.getJMBG());
			txtAdresa.setText(vozacZaIzmenu.getAdresa());
			cbPol.setSelectedItem(vozacZaIzmenu.getPol());
			txtBrojTelefona.setText(vozacZaIzmenu.getBrojTelefona());
			txtKorisnickoIme.setText(vozacZaIzmenu.getKorisnickoIme());
			txtLozinka.setText(vozacZaIzmenu.getLozinka());
			txtPlata.setText(String.valueOf(vozacZaIzmenu.getPlata()));
			txtBrojClanskeKarte.setText(vozacZaIzmenu.getBrojClanskeKarte());
			cbAutomobil.setSelectedItem(String.valueOf(vozacZaIzmenu.getAutomobil().getBrojTaksiVozila()));
			for(int i=0; i<cbAutomobil.getItemCount(); i++) {
				if(taksiSluzba.isZauzetAutomobil(Integer.parseInt(cbAutomobil.getItemAt(i))) && vozacZaIzmenu.getAutomobil().getBrojTaksiVozila() != Integer.parseInt(cbAutomobil.getItemAt(i))) {
					cbAutomobil.removeItem(cbAutomobil.getItemAt(i));
				}
			}
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
		add(lblBrojClanskeKarte);
		add(txtBrojClanskeKarte);
		add(cbAutomobil);
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VozaciIzmenaProzor.this.dispose();
				VozaciIzmenaProzor.this.setVisible(false);
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validacijaPodataka(txtIme.getText().trim(), txtPrezime.getText().trim(),txtJMBG.getText().trim(), txtAdresa.getText().trim(),txtBrojTelefona.getText().trim(), txtKorisnickoIme.getText().trim(), txtLozinka.getText().trim(), txtPlata.getText().trim())) {
				try {
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String JMBG = txtJMBG.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String brojTelefona = txtBrojTelefona.getText().trim();
					PolOsobe pol = PolOsobe.valueOf(cbPol.getSelectedItem().toString());
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = txtLozinka.getText().trim();
					int plata = Integer.parseInt(txtPlata.getText().trim());
					String brojClanskeKarte = txtBrojClanskeKarte.getText().trim();
					int automobilInt = Integer.parseInt(cbAutomobil.getSelectedItem().toString());
					TipOsobe tipOsobe = TipOsobe.getTipOsobe(2);
					Automobil automobil = taksiSluzba.pronadjiAutomobilPoBroju(automobilInt);
					zauzeto = false;
					if (vozacZaIzmenu == null) {
						if(taksiSluzba.isZauzetoKorisnickoIme(korisnickoIme)) {
							zauzeto = true;
						}else {
							Vozac vozac= new Vozac(tipOsobe, ime, prezime, JMBG, adresa,pol, brojTelefona, korisnickoIme, lozinka, plata, brojClanskeKarte, automobil);
							taksiSluzba.getVozaci().add(vozac);
							taksiSluzba.getOsobe().add(vozac);
						}
					}else{
						if(taksiSluzba.isZauzetoKorisnickoIme(korisnickoIme) && !vozacZaIzmenu.getKorisnickoIme().equals(korisnickoIme)) {
							zauzeto = true;
						}else {
							vozacZaIzmenu.setIme(ime);
							vozacZaIzmenu.setPrezime(prezime);
							vozacZaIzmenu.setAdresa(adresa);
							vozacZaIzmenu.setJMBG(JMBG);
							vozacZaIzmenu.setBrojTelefona(brojTelefona);
							vozacZaIzmenu.setKorisnickoIme(korisnickoIme);
							vozacZaIzmenu.setPol(pol);
							vozacZaIzmenu.setLozinka(lozinka);
							vozacZaIzmenu.setPlata(plata);
							vozacZaIzmenu.setBrojClanskeKarte(brojClanskeKarte);
							vozacZaIzmenu.setAutomobil(automobil);
						}
			}
					
					
					if(zauzeto == true) {
						VozaciIzmenaProzor.this.setVisible(true);
						JOptionPane.showMessageDialog(null, "Korisnicko ime zauzeto", "Greska" , JOptionPane.ERROR_MESSAGE);
					}else{
						taksiSluzba.snimiOsobe();
						VozaciIzmenaProzor.this.dispose();
						VozaciIzmenaProzor.this.setVisible(false);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Podaci pogresno uneti", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				}else {
					JOptionPane.showMessageDialog(null, "Podaci pogresno uneti", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
	}
	
	public boolean validacijaPodataka(String ime,String prezime,String JMBG,String adresa,String brojTelefona,String korisnickoIme,String lozinka, String plata) {
		if (ime.isEmpty()  == true || prezime.isEmpty() == true || JMBG.isEmpty() == true || adresa.isEmpty() == true || brojTelefona.isEmpty() == true || korisnickoIme.isEmpty() == true || lozinka.isEmpty() == true || plata.isEmpty() == true) {
			return false;
		} else {
			try {
				Long.parseLong(JMBG);
				Long.parseLong(brojTelefona);
				Long.parseLong(plata);
				return true;
			} catch (Exception NumberFormatException) {
				System.out.println("greska");
				return false;
			}
		}
		}
}
