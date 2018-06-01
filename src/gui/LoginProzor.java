package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import osoba.Osoba;
import taksiSluzba.TaksiSluzba;

public class LoginProzor extends JFrame {
	
	private JLabel lblPoruka;
	private JLabel lblKorisnickoime;
	private JTextField txtKorisnickoIme;
	private JLabel lblSifra;
	private JPasswordField pfSifra;
	private JButton btnOk;
	private JButton btnCancel;
	public TaksiSluzba taksiSluzba;
	protected int rezim;
	
	public LoginProzor(TaksiSluzba taksiSluzba) {
		this.taksiSluzba = taksiSluzba;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		isFinished();
		pack();
		
	}
	
	
	public boolean isFinished() {
		return true;
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][] []20[][]20[]");
		setLayout(mig);
		lblPoruka = new JLabel("Molimo, prijavite se");
		lblKorisnickoime = new JLabel("Korisnicko ime");
		txtKorisnickoIme = new JTextField(20);
		lblSifra = new JLabel("Sifra");
		pfSifra = new JPasswordField(20);
		btnOk = new JButton("OK");
		btnCancel = new JButton("Cancel");
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoime);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);	
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println(taksiSluzba.getAutomobili());
				String korisnickoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword()).trim();
				if(korisnickoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste uneli login podatke", "Greska", JOptionPane.WARNING_MESSAGE);
				} else {
					rezim = LoginProzor.this.taksiSluzba.login(korisnickoIme, sifra);
					//System.out.println(rezim);
					if( rezim == 1 || rezim == 2 || rezim == 3) {
						for(int i = 0; i < taksiSluzba.getOsobe().size(); i++) {
							Osoba osoba = taksiSluzba.getOsobe().get(i);
							if(osoba.getKorisnickoIme().equals(korisnickoIme)) {
								taksiSluzba.setUlogovan(osoba);
								//taksiSluzba.ucitajAutomobile();
								taksiSluzba.ucitajVoznje();
							}
						}
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false); 
						GlavniProzor glavni = new GlavniProzor(LoginProzor.this.taksiSluzba, rezim);
						glavni.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Pogresni login podaci","Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
		);
}
}
