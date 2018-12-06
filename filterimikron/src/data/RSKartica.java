package data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RSKartica {

	private String svrha;
	private String brojFakture;
	private String datum;
	private String pozivNaBr;
	private double iznos;

	public RSKartica(String svrha, String brojFakture, String datum,
			String pozivNaBr, double iznos) {
		super();
		this.svrha = svrha;
		this.brojFakture = brojFakture;
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
			this.datum = new SimpleDateFormat("dd.MM.yyyy").format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pozivNaBr = pozivNaBr;
		this.iznos = iznos;
	}

	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	public String getBrojFakture() {
		return brojFakture;
	}

	public void setBrojFakture(String brojFakture) {
		this.brojFakture = brojFakture;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		try {
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
			this.datum = new SimpleDateFormat("dd.MM.yyyy").format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getPozivNaBr() {
		return pozivNaBr;
	}

	public void setPozivNaBr(String pozivNaBr) {
		this.pozivNaBr = pozivNaBr;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}

	@Override
	public String toString() {
		return "RSKartica [svrha=" + this.svrha + ", brojfakture="
				+ this.brojFakture + ", datum=" + this.datum + ", pozivNaBr="
				+ this.pozivNaBr + ", iznos=" + this.iznos + "]";
	}

}
