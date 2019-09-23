package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.FormEvent;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	
	Database db = new Database();

	public void addPerson(FormEvent ev) {
		String name = ev.getName();
		String occupation = ev.getOccupation();
		int ageCategoryId = ev.getAgeCategory();
		String empCat = ev.getEmploymentCategory();
		boolean isDanishCitizen = ev.isDanishCitizen();
		String taxId = ev.getTaxId();
		String gender = ev.getGender();
		
		AgeCategory ageCategory = null;
		
		switch(ageCategoryId) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
			break;
		default:
			break;
		}
		
		EmploymentCategory employmentCategory;
		
		if(empCat == "employed") {
			employmentCategory = EmploymentCategory.employed;
		}
		else if(empCat == "self-employed") {
			employmentCategory = EmploymentCategory.selfEmployed;
		}
		else if(empCat == "unemployed") {
			employmentCategory = EmploymentCategory.unemployed;
		}
		else {
			employmentCategory = EmploymentCategory.other;
		}
		
		Gender genderCat;
		
		if(gender == "man") {
			genderCat = Gender.male;
		}
		else {
			genderCat = Gender.female;
		}
		
		Person person = new Person(name, occupation, ageCategory, employmentCategory, isDanishCitizen, taxId, genderCat);

		db.addPerson(person);
		
	}
	
	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	public void saveToFile(File file) throws IOException {
		db.saveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException{
		db.loadFromFile(file);
	}
	
	public void deleteRow(int index) {
		db.deleteRow(index);
	}
}
