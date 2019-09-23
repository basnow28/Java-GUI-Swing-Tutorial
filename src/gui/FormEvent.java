package gui;
import java.awt.event.ActionEvent;
import java.util.EventObject;

public class FormEvent extends EventObject {
	private String name;
	private String occupation;
	private int ageCategory;
	private String emplCategory;
	private boolean isDanishCitizen;
	private String taxId;
	private String gender;
	
	public FormEvent(Object source) {
		super(source);
	};
	
	public FormEvent(Object source, String name, String occupation, 
		int ageCategory, String empCat, String taxId, boolean isDanCit,
		String gender) {
		
		super(source);
		
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;	
		this.emplCategory = empCat;
		this.taxId = taxId;
		this.isDanishCitizen = isDanCit;
		this.gender = gender;
	};
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public void setAgeCategory(int ageCat) {
		this.ageCategory = ageCat;
	}
	
	public void setEmploymentCategory(String empCat) {
		this.emplCategory = empCat;
	}
	
	public void setTaxID(String taxId) {
		this.taxId = taxId;
	}
	
	public void setIsDanishCitizen(boolean isDanishCitizen) {
		this.isDanishCitizen = isDanishCitizen;
	}
	
	public boolean isDanishCitizen() {
		return isDanishCitizen;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOccupation() {
		return occupation;
	}
	
	public int getAgeCategory() {
		return ageCategory;
	}
	
	public String getEmploymentCategory() {
		return this.emplCategory;
	}
	
	public String getTaxId() {
		return this.taxId;
	}
	
	public String toString() {
		return this.name + " " + this.emplCategory + this.gender + "\n";
	}
	


}
