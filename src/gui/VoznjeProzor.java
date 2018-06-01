package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.accessibility.Accessible;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JComponent.AccessibleJComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.omg.CORBA.ULongLongSeqHelper;

import net.miginfocom.swing.MigLayout;
import osoba.Dispecer;
import osoba.Musterija;
import osoba.OdeljenjeDispecera;
import taksiSluzba.CenaVoznje;
import taksiSluzba.TaksiSluzba;
import taksiSluzba.Voznja;

public class VoznjeProzor extends JFrame {
	private JToolBar mainToolBar;
	private JLabel lblKorisnickoIme;
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton[] btns = new JButton[10];
	private JTable tblVoznje;
	public TaksiSluzba taksiSluzba;
	public CenaVoznje cenaVoznje;
	private ArrayList<Voznja> telefonskeVoznje;
	
	
	public VoznjeProzor(TaksiSluzba taksiSluzba, int rezim) {
		this.taksiSluzba = taksiSluzba;
		this.telefonskeVoznje = new ArrayList<Voznja>();
		setTitle("Voznje");
		setSize(400,200);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(rezim);
		initActions(rezim);
	}
	
	public void initGUI( int rezim) {
		if(rezim == 1) {
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
			String[] zaglavlje = new String[] {"Datum i vreme","Adresa polaska", "Musterija", "Vozac", "Dispecer",};
			for(Voznja voznja: taksiSluzba.getVoznje()) {
				if(voznja.getNadmorskaDuzina() == 0.0) {
					telefonskeVoznje.add(voznja);
				}
			}
			Object[][] sadrzaj = new Object[telefonskeVoznje.size()][zaglavlje.length];
			
			for(int i=0; i<telefonskeVoznje.size(); i++) {
				Voznja vt = telefonskeVoznje.get(i);
				if(vt.getNadmorskaDuzina() == 0.0) {
				sadrzaj[i][0] = vt.getVreme();
				sadrzaj[i][1] = vt.getAdresaPolaska();
				sadrzaj[i][2] = vt.getMusterija();
				sadrzaj[i][3] = vt.getVozac();
				sadrzaj[i][4] = vt.getDispecer();
				}
			}
			
			DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
			tblVoznje = new JTable(tableModel);
			tblVoznje.setRowSelectionAllowed(true);
			tblVoznje.setColumnSelectionAllowed(false);
			tblVoznje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			tblVoznje.setDefaultEditor(Object.class, null);
			
			JScrollPane tableScroll = new JScrollPane(tblVoznje);
			add(tableScroll, BorderLayout.CENTER);
		
		
		}else if (rezim == 2 || rezim == 4) {
			
			if(rezim == 2) {
				mainToolBar = new JToolBar();
				ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
				btnAdd = new JButton(addIcon);
				mainToolBar.add(btnAdd);
				add(mainToolBar, BorderLayout.NORTH);
				String[] zaglavlje = new String[] {"Datum i vreme","Adresa polaska", "Musterija", "Nadmorska Duzina-Sirina", "Napomena"};
				Object[][] sadrzaj = new Object[taksiSluzba.getNedodeljeneVoznje().size()][zaglavlje.length];
				for (int i=0; i<taksiSluzba.getNedodeljeneVoznje().size(); i++) {
					Voznja t = taksiSluzba.getNedodeljeneVoznje().get(i);
					sadrzaj[i][0] = t.getVreme();
					sadrzaj[i][1] = t.getAdresaPolaska();
					sadrzaj[i][2] = t.getMusterija();
					sadrzaj[i][3] = String.valueOf(t.getNadmorskaDuzina()) +"-"+ String.valueOf(t.getNadmorskaSirina());
					sadrzaj[i][4] = t.getNapomena();
				}
				DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
				tblVoznje = new JTable(tableModel);
				tblVoznje.setRowSelectionAllowed(true);
				tblVoznje.setColumnSelectionAllowed(false);
				tblVoznje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblVoznje.setDefaultEditor(Object.class, null);
				
				JScrollPane tableScroll = new JScrollPane(tblVoznje);
				add(tableScroll, BorderLayout.CENTER);
			}else {
				mainToolBar = new JToolBar();
				ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/bill.gif"));
				btnAdd = new JButton(addIcon);
				mainToolBar.add(btnAdd);
				add(mainToolBar, BorderLayout.NORTH);
				String[] zaglavlje = new String[] {"Datum i vreme","Adresa polaska", "Musterija"};
				Object[][] sadrzaj = new Object[taksiSluzba.getVoznjeVozaca().size()][zaglavlje.length];
				for (int i=0; i<taksiSluzba.getVoznjeVozaca().size(); i++) {
					Voznja t = taksiSluzba.getVoznjeVozaca().get(i);
					sadrzaj[i][0] = t.getVreme();
					sadrzaj[i][1] = t.getAdresaPolaska();
					sadrzaj[i][2] = t.getMusterija();
				}
				DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
				tblVoznje = new JTable(tableModel);
				tblVoznje.setRowSelectionAllowed(true);
				tblVoznje.setColumnSelectionAllowed(false);
				tblVoznje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tblVoznje.setDefaultEditor(Object.class, null);
				
				JScrollPane tableScroll = new JScrollPane(tblVoznje);
				add(tableScroll, BorderLayout.CENTER);
			}
			
			}else {
				
				if(ulogovanaMusterijaKoristiApp()) {
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
					String[] zaglavlje = new String[] {"Datum i vreme","Adresa polaska", "Nadmorska Duzina-Sirina", "Napomena"};
					Object[][] sadrzaj = new Object[taksiSluzba.getVoznjeMusterije().size() ][zaglavlje.length];
					for(int t=0; t< taksiSluzba.getVoznjeMusterije().size() ; t++) {
						Voznja nt = taksiSluzba.getVoznjeMusterije().get(t);
						sadrzaj[t][0] = nt.getVreme();
						sadrzaj[t][1] = nt.getAdresaPolaska();
						sadrzaj[t][2] =String.valueOf(nt.getNadmorskaDuzina()) +"-"+ String.valueOf(nt.getNadmorskaSirina());
						sadrzaj[t][3] = nt.getNapomena();
						}
							
					DefaultTableModel tableModel = new DefaultTableModel(sadrzaj, zaglavlje);
					tblVoznje = new JTable(tableModel);
					tblVoznje.setRowSelectionAllowed(true);
					tblVoznje.setColumnSelectionAllowed(false);
					tblVoznje.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tblVoznje.setDefaultEditor(Object.class, null);
					
					JScrollPane tableScroll = new JScrollPane(tblVoznje);
					add(tableScroll, BorderLayout.CENTER);
				
				
			}else {
				MigLayout mig = new MigLayout("wrap 2");
				setLayout(mig);
				setTitle("Pozovi taksi");
				setSize(200,200);
				for(int i=0; i<taksiSluzba.getDispeceri().size(); i++) {
					if(taksiSluzba.getDispeceri().get(i).getOdeljenje() == OdeljenjeDispecera.PRIJEM) {
						btns[i] = new JButton(taksiSluzba.getDispeceri().get(i).getBrojTelLinije());
						add(btns[i]);
						
					}
				}
				for(JButton btn: btns) {
					if(btn != null) {
						btn.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								String adresaPolaska = (String)JOptionPane.showInputDialog(null, "Dispecer: Vasa lokacije je?", "Unesite adresu polaska ", JOptionPane.PLAIN_MESSAGE, null, null, "");
								GregorianCalendar vreme = new GregorianCalendar();
								SimpleDateFormat sablon = new SimpleDateFormat("dd.MM.yyyy-kk:mm");
								sablon.format(vreme.getTime());
								for(Dispecer dispecer : taksiSluzba.getDispeceri()) {
									if(dispecer.getBrojTelLinije() == btn.getText()) {
										Voznja voznja = new Voznja(String.valueOf(sablon.format(vreme.getTime())), adresaPolaska, taksiSluzba.getUlogovan().getKorisnickoIme(), "", dispecer.getKorisnickoIme());
										taksiSluzba.getVoznje().add(voznja);
										taksiSluzba.snimiVoznje();
									}
								}
							}
						});
					}
				}
				
				
				
				
			}
		}
	}
	
	
	public void initActions(int rezim) {
		if(rezim == 1) {
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					VoznjeIzmenaProzor mip = new VoznjeIzmenaProzor(VoznjeProzor.this.taksiSluzba, null, rezim);
					mip.setVisible(true);
				}
			});
			
			btnEdit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Voznja v  = nadjiSelektovanuVoznju();
					if(v != null) {
						VoznjeIzmenaProzor vip = new VoznjeIzmenaProzor(taksiSluzba, v, rezim);
						vip.setVisible(true);
					}
					
				}
			});
			
			btnDelete.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					Voznja v = nadjiSelektovanuVoznju();
					if(v != null) {
						taksiSluzba.getVoznje().remove(v);
						taksiSluzba.snimiVoznje();
						DefaultTableModel model = (DefaultTableModel) tblVoznje.getModel();
						model.removeRow(tblVoznje.getSelectedRow());
					}
					
				}
			});
		}else if(rezim == 2 || rezim == 4) {
			if(rezim == 2) {
			btnAdd.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try{
						Voznja v = nadjiSelektovanuVoznju();
						v.setVozac(taksiSluzba.getUlogovan().getKorisnickoIme());
						taksiSluzba.getVoznjeVozaca().add(v);
						DefaultTableModel model = (DefaultTableModel) tblVoznje.getModel();
						model.removeRow(tblVoznje.getSelectedRow());
						taksiSluzba.snimiVoznje();
					}catch (NullPointerException e) {
						JOptionPane.showMessageDialog(null, "Nema trenutno slobodnih voznji", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			}else{
				btnAdd.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Voznja v = nadjiSelektovanuVoznju();
						if(v == null) {
							JOptionPane.showMessageDialog(null, "Nemate trenutno ni jednu voznju" , "Greska", JOptionPane.ERROR_MESSAGE);
						}else {
						String predjenaKilometraza = (String)JOptionPane.showInputDialog(null, "Unesite predjenu kilometrazu", "Naplatite voznju ", JOptionPane.PLAIN_MESSAGE, null, null, "");
						JOptionPane.showMessageDialog( null, "Cena voznje je: " + String.valueOf(90 + (70 * Integer.parseInt(predjenaKilometraza)))+ "dinara" ,"Cena voznje",JOptionPane.INFORMATION_MESSAGE);
						DefaultTableModel model = (DefaultTableModel) tblVoznje.getModel();
						model.removeRow(tblVoznje.getSelectedRow());
						taksiSluzba.getVoznje().remove(v);
						taksiSluzba.snimiVoznje();
						}
					}
				});
			}
		}
		else {
			if(ulogovanaMusterijaKoristiApp()) {
						btnAdd.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								VoznjeIzmenaProzor vip = new VoznjeIzmenaProzor(VoznjeProzor.this.taksiSluzba, null, rezim);
								vip.setVisible(true);
							}
						});
						
						btnEdit.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent arg0) {
								Voznja v  = nadjiSelektovanuVoznju();
								if(v != null) {
									VoznjeIzmenaProzor vip = new VoznjeIzmenaProzor(taksiSluzba, v, rezim);
									vip.setVisible(true);
								}
							}
						});
						
						btnDelete.addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								Voznja v = nadjiSelektovanuVoznju();
								if(v != null) {
									taksiSluzba.getVoznje().remove(v);
									taksiSluzba.snimiVoznje();
									DefaultTableModel model = (DefaultTableModel) tblVoznje.getModel();
									model.removeRow(tblVoznje.getSelectedRow());
								}
							}
						});
				}else {
				}
		}
	}
	
	public Voznja nadjiSelektovanuVoznju() {
		int selektovaniRed = tblVoznje.getSelectedRow();
		if(selektovaniRed == -1) {
			JOptionPane.showMessageDialog(null, "Morati odabrati voznju", "Greska", JOptionPane.WARNING_MESSAGE);
			return null;
		}else {
			DefaultTableModel model = (DefaultTableModel) tblVoznje.getModel();
			String datum = model.getValueAt(selektovaniRed, 0).toString();
			Voznja v = taksiSluzba.pronadjiVoznju(datum);
			if(v == null) {
				JOptionPane.showMessageDialog(null, ",Voznja nije pronadjena", "Greska", JOptionPane.ERROR_MESSAGE);
			}
			return v;
		}
	}
	
	public boolean ulogovanaMusterijaKoristiApp() {
		for(Musterija musterija : taksiSluzba.getMusterije()) {
			if(musterija.equals(taksiSluzba.getUlogovan())) {
				if(musterija.isKoristiAplikaciju() == true) {
					return true;
				}
			}
		}
		return false;
	}
}
