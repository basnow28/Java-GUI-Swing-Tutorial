package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {
	private List<Person> people;
	
	public Database() {
		people = new LinkedList<Person>();
	}
	
	public void addPerson(Person person) {
		if(person.getId() == 1 && people.size() > 0) {
			Person.setCount(people.size() + 2);
			person.setId(people.size() + 1);
		}
		
		people.add(person);
	}
	
	public void deleteRow(int index) {
		people.remove(index);
	}
	
	public List<Person> getPeople(){
		return Collections.unmodifiableList(people);
	}
	
	public void saveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		Person[] persons = people.toArray(new Person[people.size()]);
		
		oos.writeObject(persons);
		
		fos.close();
		oos.close();
	}
	
	public void loadFromFile(File file) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		try {
			Person[] persons = (Person[]) ois.readObject();
			
			people.clear();
			
			people.addAll(Arrays.asList(persons));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ois.close();
	}
}
