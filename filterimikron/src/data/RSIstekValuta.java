package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSIstekValuta {

	private int idfakture;
	private String brojFakture;
	private String naziv;
	private String datum;
	private String valuta;
	private double iznos;
	private int placeno;
	
	public RSIstekValuta(int idfakture, String brojFakture, String naziv, String datum, String valuta, double iznos, int placeno) {
		super();
		
		this.idfakture = idfakture;
		this.brojFakture=brojFakture;
		this.naziv=naziv;
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
			this.datum = new SimpleDateFormat("dd.MM.yyyy").format(date);
			Date valutaDate = new SimpleDateFormat("yyyy-MM-dd").parse(valuta);
			this.valuta = new SimpleDateFormat("dd.MM.yyyy").format(valutaDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.iznos = iznos;
		this.placeno = placeno;
		
		// TODO Auto-generated constructor stub
	}
	
	public int getIdfakture() {
		return this.idfakture;
	}

	public void setIdFakture(int idfakture) {
		this.idfakture = idfakture;
	}

	public String getBrojFakture() {
		return brojFakture;
	}

	public void setBrojFakture(String brojFakture) {
		this.brojFakture = brojFakture;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getValuta() {
		return valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	public int getPlaceno() {
		return placeno;
	}

	public void setPlaceno(int placeno) {
		this.placeno = placeno;
	}
	
	

	

}
