package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import automobil.Automobil;
import automobil.VrstaAutomobila;
import net.miginfocom.swing.MigLayout;
import taksiSluzba.TaksiSluzba;

public class AutomobiliIzmenaProzor extends JFrame {
	private JLabel lblModel;
	private JLabel lblProizvodjac;
	private JLabel lblGodinaProizvodnje;
	private JLabel lblBrojRegistarskeOznake;
	private JLabel lblBrojTaksiVozila;
	private JLabel lblVrstaAutomobila;
	
	private JButton btnOk, btnCancel;
	
	private JTextField txtModel;
	private JTextField txtProizvodjac;
	private JTextField txtGodinaProizvodnje;
	private JTextField txtBrojRegistarskeOznake;
	private JTextField txtBrojTaksiVozila;
	private JComboBox<String>cbVrstaAutomobila;
	
	private TaksiSluzba taksiSluzba;
	private Automobil automobilZaIzmenu;
	private boolean zauzeto;
	
	public  AutomobiliIzmenaProzor(TaksiSluzba taksiSluzba, Automobil automobilZaIzmenu) {
		this.taksiSluzba = taksiSluzba;
		this.automobilZaIzmenu = automobilZaIzmenu;
		if(this.automobilZaIzmenu == null) {
			setTitle("Automobili - dodavanje");
		}else {
			setTitle("Automobili - izmena");
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initActions();
		pack();
	}
	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2", "[][]","[][][]");
		setLayout(mig);
		lblModel = new JLabel("Model");
		lblProizvodjac = new JLabel("Proizvodjac");
		lblGodinaProizvodnje = new JLabel("Godina proizvodnje");
		lblBrojRegistarskeOznake = new JLabel("Broj registarske oznake");
		lblBrojTaksiVozila = new JLabel("Broj taksi vozila");
		lblVrstaAutomobila = new JLabel("Vrsta automobila");
		
		txtModel = new JTextField(15);
		txtProizvodjac = new JTextField(15);
		txtGodinaProizvodnje = new JTextField(15);
		txtBrojRegistarskeOznake = new JTextField(15);
		txtBrojTaksiVozila = new JTextField(15);
		cbVrstaAutomobila = new JComboBox<String>();
		cbVrstaAutomobila.addItem("PUTNICKO");
		cbVrstaAutomobila.addItem("KOMBI");
		
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		
		if(automobilZaIzmenu != null) {
			txtModel.setText(automobilZaIzmenu.getModel());
			txtProizvodjac.setText(automobilZaIzmenu.getProizvodjac());
			txtGodinaProizvodnje.setText(String.valueOf(automobilZaIzmenu.getGodinaProizvodnje()));
			txtBrojRegistarskeOznake.setText(automobilZaIzmenu.getBrojRegistarskeOznake());
			txtBrojTaksiVozila.setText(String.valueOf(automobilZaIzmenu.getBrojTaksiVozila()));
			cbVrstaAutomobila.setSelectedItem(automobilZaIzmenu.getVrstaAutomobila());
			}
		add(lblModel);
		add(txtModel);
		add(lblProizvodjac);
		add(txtProizvodjac);
		add(lblGodinaProizvodnje);
		add(txtGodinaProizvodnje);
		add(lblBrojRegistarskeOznake);
		add(txtBrojRegistarskeOznake);
		add(lblBrojTaksiVozila);
		add(txtBrojTaksiVozila);
		add(lblVrstaAutomobila);
		add(cbVrstaAutomobila);
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobiliIzmenaProzor.this.dispose();
				AutomobiliIzmenaProzor.this.setVisible(false);
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacijaPodataka(txtModel.getText().trim(), txtProizvodjac.getText().trim() , txtGodinaProizvodnje.getText().trim(), txtBrojRegistarskeOznake.getText().trim(), txtBrojTaksiVozila.getText().trim())) {
				try {
					String model = txtModel.getText().trim();
					String proizvodjac = txtProizvodjac.getText().trim();
					int godinaProizvodnje = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
					String brojRegistarskeOznake = txtBrojRegistarskeOznake.getText().trim();
					int brojTaksiVozila = Integer.parseInt(txtBrojTaksiVozila.getText().trim());
					VrstaAutomobila vrstaAutomobila = VrstaAutomobila.valueOf(cbVrstaAutomobila.getSelectedItem().toString());
					zauzeto = false;
					if (automobilZaIzmenu == null) {
						if(taksiSluzba.isZauzetAutomobil(brojRegistarskeOznake, brojTaksiVozila)) {
							zauzeto = true;
						}else {
						Automobil automobil = new Automobil(model, proizvodjac, godinaProizvodnje, brojRegistarskeOznake, brojTaksiVozila, vrstaAutomobila);
						taksiSluzba.getAutomobili().add(automobil);
						}
					}else{
						if(taksiSluzba.isZauzetAutomobil(brojRegistarskeOznake, brojTaksiVozila) && (!automobilZaIzmenu.getBrojRegistarskeOznake().equals(brojRegistarskeOznake)  || automobilZaIzmenu.getBrojTaksiVozila() != brojTaksiVozila)) {
							zauzeto = true;
						}else {
							automobilZaIzmenu.setModel(model);
							automobilZaIzmenu.setProizvodjac(proizvodjac);
							automobilZaIzmenu.setGodinaProizvodnje(godinaProizvodnje);
							automobilZaIzmenu.setBrojRegistarskeOznake(brojRegistarskeOznake);
							automobilZaIzmenu.setBrojTaksiVozila(brojTaksiVozila);
							automobilZaIzmenu.setVrstaAutomobila(vrstaAutomobila);
						}
					}
					
					
					
					if(zauzeto == true) {
						AutomobiliIzmenaProzor.this.setVisible(true);
						JOptionPane.showMessageDialog(null, "Postoji automobil sa unetim brojem registarske oznake ili brojem taksi vozila", "Greska" , JOptionPane.ERROR_MESSAGE);
						
					}else {
						taksiSluzba.snimiAutomobile();
						AutomobiliIzmenaProzor.this.dispose();
						AutomobiliIzmenaProzor.this.setVisible(false);
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Uneti podaci nisu validni", "Greska", JOptionPane.WARNING_MESSAGE);
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Uneti podaci nisu validni", "Greska", JOptionPane.WARNING_MESSAGE);
			}
			}
		});
	}
	
	public boolean validacijaPodataka(String model,String proizvodjac,String godinaProizvodnje,String brojRegOznake,String brojTaksiVozila) {
		if (model.isEmpty()  == true || proizvodjac.isEmpty() == true || godinaProizvodnje.isEmpty() == true || brojRegOznake.isEmpty() == true || brojTaksiVozila.isEmpty() == true) {
			return false;
		} else {
			try {
				Integer.parseInt(godinaProizvodnje);
				Integer.parseInt(brojTaksiVozila);
				return true;
			} catch (Exception NumberFormatException) {
				System.out.println("greska");
				return false;
			}
		}
		}
}