package data;

public class RSDuznici {
	
	private String naziv;
	private double iznos;
	
	public RSDuznici(String naziv, double iznos) {
		super();
		this.naziv = naziv;
		this.iznos = iznos;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getIznos() {
		return iznos;
	}

	public void setIznos(double iznos) {
		this.iznos = iznos;
	}
	
	
	
	

}
