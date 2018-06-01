package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import automobil.Automobil;
import osoba.Dispecer;
import osoba.Musterija;
import taksiSluzba.TaksiSluzba;

public class AutomobiliProzor extends JFrame {
	
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable tblAutomobili;
	public TaksiSluzba taksiSluzba;
	
	public AutomobiliProzor(TaksiSluzba taksiSluzba)  {
		this.taksiSluzba = taksiSluzba;
		setTitle("Automobili");
		setSize(200,200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
		}
	
	public void initGUI() {
		mainToolBar = new JToolBar();
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnAdd = new JButton(addIcon);
		btnEdit = new JButton(editIcon);
		btnDelete = new JButton(deleteIcon);
		
		mainToolBar.add(btnAdd);
		mainToolBar.add(btnEdit);
		mainToolBar.add(btnDelete);
		add(mainToolBar, BorderLayout.NORTH);
		
		String[] zaglavlje = new String[] {"Model", "Proizvodjac" , "Godina proizvodnje", "Registarska oznaka", "Broj taksi vozila", "Vrsta automobila"};
		Object[][] sadrzaj = new Object[taksiSluzba.getAutomobili().size()][zaglavlje.length];
		
		for(int i=0; i<taksiSluzba.getAutomobili().size(); i++) {
			Automobil a = taksiSluzba.getAutomobili().get(i);
			sadrzaj[i][0] = a.getModel();
			sadrzaj[i][1] = a.getProizvodjac();
			sadrzaj[i][2] = a.getGodinaProizvodnje();
			sadrzaj[i][3] = a.getBrojRegistarskeOznake();
			sadrzaj[i][4] = a.getBrojTaksiVozila();
			sadrzaj[i][5] = a.getVrstaAutomobila();
			}
		
		DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
		tblAutomobili = new JTable(tableModel);
		tblAutomobili.setRowSelectionAllowed(true);
		tblAutomobili.setColumnSelectionAllowed(false);
		tblAutomobili.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblAutomobili.setDefaultEditor(Object.class, null);
		
		JScrollPane tableScroll = new JScrollPane(tblAutomobili);
		add(tableScroll, BorderLayout.CENTER);
	}
	
	public void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AutomobiliIzmenaProzor aip = new AutomobiliIzmenaProzor(AutomobiliProzor.this.taksiSluzba, null);
				aip.setVisible(true);
				
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Automobil a  = nadjiSelektovaniAutomobil();
				if(a != null) {
					AutomobiliIzmenaProzor aip = new AutomobiliIzmenaProzor(taksiSluzba, a);
					aip.setVisible(true);
				}
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Automobil a = nadjiSelektovaniAutomobil();
				if(a != null) {
					taksiSluzba.getAutomobili().remove(a);
					taksiSluzba.snimiAutomobile();
					DefaultTableModel model = (DefaultTableModel) tblAutomobili.getModel();
					model.removeRow(tblAutomobili.getSelectedRow());
				}
				
			}
		});
	}
	
	private Automobil nadjiSelektovaniAutomobil() {
		int selektovaniRed = tblAutomobili.getSelectedRow();
		if(selektovaniRed == -1) {
			JOptionPane.showConfirmDialog(null, "Morate selektovati automobil" , "Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) tblAutomobili.getModel();
			String brojRegOznake = model.getValueAt(selektovaniRed, 3).toString();
			Automobil a = taksiSluzba.pronadjiAutomobil(brojRegOznake);
			if(a == null) {
				JOptionPane.showMessageDialog(null, "Automobil nije pronadjen", "Greska", JOptionPane.ERROR_MESSAGE);
			}
			return a;
		}
	}
}