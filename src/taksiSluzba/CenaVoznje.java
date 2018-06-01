package taksiSluzba;
import taksiSluzba.Voznja;
import taksiSluzba.TaksiSluzba;

public class CenaVoznje {
	public static final int START = 90;
	
	public int naplatiVoznju(int predjenaKilometraza){
		return START + (predjenaKilometraza * 70);
	}
	
}
