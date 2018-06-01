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

import osoba.Dispecer;
import osoba.Musterija;
import taksiSluzba.TaksiSluzba;

public class MusterijeProzor extends JFrame {
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable tblMusterije;
	public TaksiSluzba taksiSluzba;
	
	public MusterijeProzor(TaksiSluzba taksiSluzba) {
		this.taksiSluzba = taksiSluzba;
		setTitle("Musterije");
		setSize(800,400);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	public void initGUI() {
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime", "JMBG", "Adresa", "Pol", "Broj Telefona" , "Korisnicko ime", "Korisni mob. aplikaciju"};
		Object[][] sadrzaj = new Object[taksiSluzba.getMusterije().size()][zaglavlje.length];
		
		for(int i=0; i<taksiSluzba.getMusterije().size(); i++) {
			Musterija m = taksiSluzba.getMusterije().get(i);
			sadrzaj[i][0] = m.getIme();
			sadrzaj[i][1] = m.getPrezime();
			sadrzaj[i][2] = m.getJMBG();
			sadrzaj[i][3] = m.getAdresa();
			sadrzaj[i][4] = m.getPol();
			sadrzaj[i][5] = m.getBrojTelefona();
			sadrzaj[i][6] = m.getKorisnickoIme();
			sadrzaj[i][7] =  String.valueOf(m.isKoristiAplikaciju());
			}
		
		DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
		tblMusterije = new JTable(tableModel);
		tblMusterije.setRowSelectionAllowed(true);
		tblMusterije.setColumnSelectionAllowed(false);
		tblMusterije.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblMusterije.setDefaultEditor(Object.class, null);
		
		JScrollPane tableScroll = new JScrollPane(tblMusterije);
		add(tableScroll, BorderLayout.CENTER);
	}
	
	public void initActions(){
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MusterijeIzmenaProzor mip = new MusterijeIzmenaProzor(MusterijeProzor.this.taksiSluzba, null);
				mip.setVisible(true);
				
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Musterija m  = nadjiSelektovanuMusteriju();
				if(m != null) {
					MusterijeIzmenaProzor mip = new MusterijeIzmenaProzor(taksiSluzba, m);
					mip.setVisible(true);
				}
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Musterija m = nadjiSelektovanuMusteriju();
				if(m != null) {
					taksiSluzba.getMusterije().remove(m);
					taksiSluzba.getOsobe().remove(m);
					taksiSluzba.snimiOsobe();
					DefaultTableModel model = (DefaultTableModel) tblMusterije.getModel();
					model.removeRow(tblMusterije.getSelectedRow());
				}
				
			}
		});
	}
	
	public Musterija nadjiSelektovanuMusteriju() {
		int selektovaniRed = tblMusterije.getSelectedRow();
		if(selektovaniRed == -1) {
			JOptionPane.showMessageDialog(null, "Morate odabrati musteriju iz tabele", "Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) tblMusterije.getModel();
			String ime = model.getValueAt(selektovaniRed, 0).toString();
			Musterija m = taksiSluzba.pronadjiMusteriju(ime);
			if(m == null) {
				JOptionPane.showMessageDialog(null, "Musterija nije pronadjen", "Greska", JOptionPane.ERROR_MESSAGE);
			}
			return m;
		}	
	}
}
