package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import osoba.Dispecer;
import osoba.Vozac;
import taksiSluzba.TaksiSluzba;

public class VozaciProzor extends JFrame {
	
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable tblVozaci;
	public TaksiSluzba taksiSluzba;
	
	public VozaciProzor(TaksiSluzba taksiSluzba) {
		this.taksiSluzba = taksiSluzba;
		setTitle("Vozaci");
		setSize(800,400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	public void initGUI() {
		//System.out.println(taksiSluzba.getAutomobili().size());
		mainToolBar = new JToolBar();
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd = new JButton(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit = new JButton(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete = new JButton(deleteIcon);
		
		mainToolBar.add(btnAdd);
		mainToolBar.add(btnEdit);
		mainToolBar.add(btnDelete);
		add(mainToolBar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "JMBG", "Adresa", "Pol", "Broj Telefona" , "Korisnicko ime", "Plata", "Broj clanske karte", "Automobil"};
		Object[][] sadrzaj = new Object[taksiSluzba.getVozaci().size()][zaglavlje.length];
		
		for(int i=0; i<taksiSluzba.getVozaci().size(); i++) {
			Vozac d = taksiSluzba.getVozaci().get(i);
			sadrzaj[i][0] = d.getIme();
			sadrzaj[i][1] = d.getPrezime();
			sadrzaj[i][2] = d.getJMBG();
			sadrzaj[i][3] = d.getAdresa();
			sadrzaj[i][4] = d.getPol();
			sadrzaj[i][5] = d.getBrojTelefona();
			sadrzaj[i][6] = d.getKorisnickoIme();
			sadrzaj[i][7] = d.getPlata();
			sadrzaj[i][8] = d.getBrojClanskeKarte();
			sadrzaj[i][9] = d.getAutomobil();
			}
		
		DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
		tblVozaci = new JTable(tableModel);
		tblVozaci.setRowSelectionAllowed(true);
		tblVozaci.setColumnSelectionAllowed(false);
		tblVozaci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblVozaci.setDefaultEditor(Object.class, null);
		
		JScrollPane tableScroll = new JScrollPane(tblVozaci);
		add(tableScroll, BorderLayout.CENTER);
		
	}
	
	public void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				VozaciIzmenaProzor vip = new VozaciIzmenaProzor(VozaciProzor.this.taksiSluzba, null);
				vip.setVisible(true);
				
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Vozac v  = nadjiSelektovanogVozaca();
				if(v != null) {
					VozaciIzmenaProzor vip = new VozaciIzmenaProzor(taksiSluzba, v);
					vip.setVisible(true);
				}
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vozac v = nadjiSelektovanogVozaca();
				if(v != null) {
					taksiSluzba.getVozaci().remove(v);
					taksiSluzba.getOsobe().remove(v);
					taksiSluzba.snimiOsobe();
					DefaultTableModel model = (DefaultTableModel) tblVozaci.getModel();
					model.removeRow(tblVozaci.getSelectedRow());
				}
				
			}
		});
		
	}
	
	public Vozac nadjiSelektovanogVozaca() {
		int selektovaniRed = tblVozaci.getSelectedRow();
		if(selektovaniRed == -1) {
			JOptionPane.showMessageDialog(null, "Morate odabrati vozaca iz tabele", "Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) tblVozaci.getModel();
			String ime = model.getValueAt(selektovaniRed, 0).toString();
			Vozac v = taksiSluzba.pronadjiVozaca(ime);
			if(v == null) {
				JOptionPane.showMessageDialog(null, "Dispecer nije pronadjen", "Greska", JOptionPane.ERROR_MESSAGE);
			}
			return v;
		}
	}
}
