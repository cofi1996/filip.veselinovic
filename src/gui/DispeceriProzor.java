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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import osoba.Dispecer;
import taksiSluzba.TaksiSluzba;

public class DispeceriProzor extends JFrame {
	private JToolBar mainToolBar;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JTable tblDispeceri;
	public TaksiSluzba taksiSluzba;
	
	public DispeceriProzor(TaksiSluzba taksiSluzba)  {
		this.taksiSluzba = taksiSluzba;
		setTitle("Dispeceri");
		setSize(1200,200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		
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
		
		String[] zaglavlje = new String[] {"Ime", "Prezime" , "JMBG", "Adresa", "Pol", "Broj Telefona" , "Korisnicko ime", "Broj telefonske linije", "Odeljenje"};
		Object[][] sadrzaj = new Object[taksiSluzba.getDispeceri().size()][zaglavlje.length];
		//System.out.println(sadrzaj);
		
		for(int i=0; i<taksiSluzba.getDispeceri().size(); i++) {
			Dispecer d = taksiSluzba.getDispeceri().get(i);
			sadrzaj[i][0] = d.getIme();
			sadrzaj[i][1] = d.getPrezime();
			sadrzaj[i][2] = d.getJMBG();
			sadrzaj[i][3] = d.getAdresa();
			sadrzaj[i][4] = d.getPol();
			sadrzaj[i][5] = d.getBrojTelefona();
			sadrzaj[i][6] = d.getKorisnickoIme();
			sadrzaj[i][7] = d.getBrojTelLinije();
			sadrzaj[i][8] = d.getOdeljenje();
			}
		
		DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
		tblDispeceri = new JTable(tableModel);
		tblDispeceri.setRowSelectionAllowed(true);
		tblDispeceri.setColumnSelectionAllowed(false);
		tblDispeceri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDispeceri.setDefaultEditor(Object.class, null);
		
		JScrollPane tableScroll = new JScrollPane(tblDispeceri);
		add(tableScroll, BorderLayout.CENTER);
		}
	
	public void initActions() {
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DispeceriIzmenaProzor dip = new DispeceriIzmenaProzor(DispeceriProzor.this.taksiSluzba, null);
				dip.setVisible(true);
				
				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dispecer d  = nadjiSelektovanogDispecera();
				if(d != null) {
					DispeceriIzmenaProzor dip = new DispeceriIzmenaProzor(taksiSluzba, d);
					dip.setVisible(true);
				}
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Dispecer d = nadjiSelektovanogDispecera();
				if(d != null) {
					taksiSluzba.getDispeceri().remove(d);
					taksiSluzba.getOsobe().remove(d);
					taksiSluzba.snimiOsobe();
					DefaultTableModel model = (DefaultTableModel) tblDispeceri.getModel();
					model.removeRow(tblDispeceri.getSelectedRow());
				}
				
			}
		});
	}
		
			public Dispecer nadjiSelektovanogDispecera() {
			int selektovaniRed = tblDispeceri.getSelectedRow();
			if(selektovaniRed == -1) {
				JOptionPane.showMessageDialog(null, "Morate odabrati dispecera iz tabele", "Greska", JOptionPane.WARNING_MESSAGE);
				return null;
			}else {
				DefaultTableModel model = (DefaultTableModel) tblDispeceri.getModel();
				String ime = model.getValueAt(selektovaniRed, 0).toString();
				Dispecer d = taksiSluzba.pronadjiDispecera(ime);
				if(d == null) {
					JOptionPane.showMessageDialog(null, "Dispecer nije pronadjen", "Greska", JOptionPane.ERROR_MESSAGE);
				}
				return d;
			}
		}
	}

