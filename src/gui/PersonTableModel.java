package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Person;


public class PersonTableModel extends AbstractTableModel {
	private List<Person> db;
	private String[] names = {"Id" , "Name", "Occupation", "Age Category", "Employment Category", "Tax ID", "Gender"};
	
	
	@Override
	public String getColumnName(int column) {
		return names[column];
	}



	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		
		switch(col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getAgeCategory();
		case 4:
			return person.getEmplCategory();
		case 5:
			return person.getTaxId();
		case 6: 
			return person.getGender();
		}
		return null;
	}
	
	public void setData(List<Person> db) {
		this.db = db;
	}

}
