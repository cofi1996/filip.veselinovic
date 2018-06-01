package main;

import gui.LoginProzor;
import taksiSluzba.Voznja;
import taksiSluzba.TaksiSluzba;

public class Main {

	public static void main(String[] args) {
		
		
		
		TaksiSluzba taksiSluzba = new TaksiSluzba();
		taksiSluzba.ucitajAutomobile();
		taksiSluzba.ucitajOsobe();
		//taksiSluzba.login("Jozi", "123");
		//taksiSluzba.ucitajVoznje();
		
		LoginProzor login = new LoginProzor(taksiSluzba);
		login.setVisible(true);
		
	}
}


