package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import osoba.Musterija;
import taksiSluzba.TaksiSluzba;

public class GlavniProzor extends JFrame {
	
	private JMenuBar mainMenu;
	private JMenu zaposleniMenu;
	private JMenuItem dispeceriItem;
	private JMenuItem vozaciItem;
	private JMenu musterijeMenu;
	private JMenuItem musterijeItem;
	private JMenu voznjeMenu;
	private JMenuItem voznjeItem;
	private JMenuItem voznjeItem2;
	private JMenu automobiliMenu;
	private JMenuItem automobiliItem;
	private JMenuItem taksiSluzbaInfo;
	
	public TaksiSluzba taksiSluzba;
	
	public GlavniProzor(TaksiSluzba taksiSluzba, int rezim) {
		//System.out.println(rezim);
		this.taksiSluzba = taksiSluzba;
		taksiSluzba.ucitajTSpodatke();
		setTitle(taksiSluzba.getNaziv() + ", " + taksiSluzba.getPIB() + ", " + taksiSluzba.getAdresa());
		setSize(400,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		initGUI(rezim);
		initActions(rezim);
	}
	
	public void initGUI(int rezim) {
		//System.out.println(rezim);
		if (rezim == 1) {
			
			mainMenu = new JMenuBar();
			zaposleniMenu = new JMenu("Zaposleni");
			dispeceriItem = new JMenuItem("Dispeceri");
			vozaciItem = new JMenuItem("Vozaci");
			zaposleniMenu.add(dispeceriItem);
			zaposleniMenu.add(vozaciItem);
			
			musterijeMenu = new JMenu("Musterije");
			musterijeItem = new JMenuItem("Musterije");
			musterijeMenu.add(musterijeItem);
			
			voznjeMenu = new JMenu("Voznje");
			voznjeItem = new JMenuItem("Voznje");
			voznjeMenu.add(voznjeItem);
			
			automobiliMenu = new JMenu("Automobili");
			automobiliItem = new JMenuItem("Automobili");
			automobiliMenu.add(automobiliItem);
			
			taksiSluzbaInfo = new JMenuItem("Taksi sluzba info.");
			
			
			mainMenu.add(zaposleniMenu);
			mainMenu.add(musterijeMenu);
			mainMenu.add(voznjeMenu);
			mainMenu.add(automobiliMenu);
			mainMenu.add(taksiSluzbaInfo);
			setJMenuBar(mainMenu);
			
		} else if(rezim == 2) {
			
			mainMenu = new JMenuBar();
			voznjeMenu = new JMenu("Voznje");
			voznjeItem = new JMenuItem("Preuzmi voznju");
			voznjeItem2 = new JMenuItem("Naplati voznju");
			voznjeMenu.add(voznjeItem);
			voznjeMenu.add(voznjeItem2);
			mainMenu.add(voznjeMenu);
			setJMenuBar(mainMenu);
			
		} else {
			for(Musterija musterija : taksiSluzba.getMusterije()) {
				if(musterija.getKorisnickoIme().equals(taksiSluzba.getUlogovan().getKorisnickoIme())) {
					if(musterija.isKoristiAplikaciju() == true) {
						mainMenu = new JMenuBar();
						voznjeMenu = new JMenu("Voznje");
						voznjeItem = new JMenuItem("Voznje");
						voznjeMenu.add(voznjeItem);
						mainMenu.add(voznjeMenu);
						setJMenuBar(mainMenu);
					}else {
						mainMenu = new JMenuBar();
						voznjeItem = new JMenuItem("Naruci voznju");
						mainMenu.add(voznjeItem);
						setJMenuBar(mainMenu);
					}
				}
			}
		}	
	}
	
	public void initActions(int rezim) {
		if(rezim == 1 ) {
			dispeceriItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					DispeceriProzor dp = new DispeceriProzor(GlavniProzor.this.taksiSluzba);
					dp.setVisible(true);
				}
			});
			
			vozaciItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VozaciProzor vp = new VozaciProzor(GlavniProzor.this.taksiSluzba);
					vp.setVisible(true);
					
				}
			});
			
			musterijeItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					MusterijeProzor mp = new MusterijeProzor(GlavniProzor.this.taksiSluzba);
					mp.setVisible(true);
					
				}
			});
			
			automobiliItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					AutomobiliProzor ap = new AutomobiliProzor(GlavniProzor.this.taksiSluzba);
					ap.setVisible(true);
					
				}
			});
			
			voznjeItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					VoznjeProzor vp = new VoznjeProzor(GlavniProzor.this.taksiSluzba, rezim);
					vp.setVisible(true);
					
				}
			});
			
			taksiSluzbaInfo.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					TaksiSluzbaIzmenaProzor  tsip = new TaksiSluzbaIzmenaProzor(GlavniProzor.this.taksiSluzba);
					tsip.setVisible(true);
					
				}
			});
			
			
			
		}else if(rezim == 2) {
			voznjeItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VoznjeProzor vp = new VoznjeProzor(GlavniProzor.this.taksiSluzba, rezim);
					vp.setVisible(true);
					
				}
			});
			
			voznjeItem2.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					VoznjeProzor vp = new VoznjeProzor(GlavniProzor.this.taksiSluzba, 4);
					vp.setVisible(true);
					
				}
			});
		} else {
			voznjeItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					VoznjeProzor vp = new VoznjeProzor(GlavniProzor.this.taksiSluzba, rezim);
					vp.setVisible(true);
					
				}
			});
		}
	}
	
	
}
