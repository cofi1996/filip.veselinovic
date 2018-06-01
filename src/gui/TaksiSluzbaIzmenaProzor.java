package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import taksiSluzba.TaksiSluzba;

public class TaksiSluzbaIzmenaProzor extends JFrame {
	
	private JLabel lblNaziv;
	private JLabel lblPIB;
	private JLabel lblAdresa;
	private JTextField txtNaziv;
	private JTextField txtPIB;
	private JTextField txtAdresa;
	private JButton btnOk;
	
	
	private TaksiSluzba taksiSluzba;
	
	
	
	public TaksiSluzbaIzmenaProzor(TaksiSluzba taksiSluzba) {
		this.taksiSluzba = taksiSluzba;
		setTitle("Osnovne informacije vezane za taksi sluzbu");
		setSize(350,150);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
	}
	
	public void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		lblNaziv = new JLabel("Naziv");
		lblPIB = new JLabel("PIB");
		lblAdresa = new JLabel("Adresa");
		txtNaziv = new JTextField(10);
		txtPIB = new JTextField(10);
		txtAdresa = new JTextField(15);
		btnOk = new JButton("Sacuvaj izmene");
		
		txtNaziv.setText(taksiSluzba.getNaziv());
		txtPIB.setText(taksiSluzba.getPIB());
		txtAdresa.setText(taksiSluzba.getAdresa());
		
		add(lblNaziv);
		add(txtNaziv);
		add(lblPIB);
		add(txtPIB);
		add(lblAdresa);
		add(txtAdresa);
		add(new JLabel());
		add(btnOk);
	}
	
	public void initActions() {
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String naziv = txtNaziv.getText().trim();
				String PIB = txtPIB.getText().trim();
				String adresa = txtAdresa.getText().trim();
				taksiSluzba.setNaziv(naziv);
				taksiSluzba.setPIB(PIB);
				taksiSluzba.setAdresa(adresa);
				taksiSluzba.snimiTSpodatke();
				TaksiSluzbaIzmenaProzor.this.dispose();
				TaksiSluzbaIzmenaProzor.this.setVisible(false);
				
				
				
			}
		});
	}
}
