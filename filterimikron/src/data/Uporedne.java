package data;

public class Uporedne {
	
	private Integer id;
	private String label;
	public Integer getId() {
		return id;
	}
	
	public Uporedne(){      
	   }
	    
    public Uporedne(Integer id, String label){
    	this.id = id;
    	this.label = label;
    }
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	 @Override
	   public String toString()
	   {
	      return "Uporedne [id=" + id + ", label=" + label + "]"; 
	   }
	
}
