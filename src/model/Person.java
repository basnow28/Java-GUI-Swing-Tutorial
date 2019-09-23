package model;

import java.io.Serializable;

public class Person implements Serializable{
	private int id;
	private String name;
	private String occupation;
	private AgeCategory ageCategory;
	private EmploymentCategory emplCategory;
	private boolean isDanishCitizen;
	private String taxId;
	private Gender gender;
	
	private static int count = 1;
	
	
	public Person(String name, String occupation, AgeCategory ageCat, 
			EmploymentCategory empCat, boolean isDanishCitizen, String taxId, Gender gender) {
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCat;
		this.emplCategory = empCat;
		this.isDanishCitizen = isDanishCitizen;
		this.taxId = taxId;
		this.gender = gender;		
		
		this.id = count;
		count++;
	}
	public static void setCount(int newCount) {
		count = newCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public AgeCategory getAgeCategory() {
		return ageCategory;
	}
	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}
	public EmploymentCategory getEmplCategory() {
		return emplCategory;
	}
	public void setEmplCategory(EmploymentCategory emplCategory) {
		this.emplCategory = emplCategory;
	}
	public boolean isDanishCitizen() {
		return isDanishCitizen;
	}
	public void setDanishCitizen(boolean isDanishCitizen) {
		this.isDanishCitizen = isDanishCitizen;
	}
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
