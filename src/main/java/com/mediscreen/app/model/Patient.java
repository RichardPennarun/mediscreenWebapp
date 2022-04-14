package com.mediscreen.app.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;



public class Patient {
	
private int id;
	
	private String lastName;
	
	private String firstName;
	
	private String dob;
	
	private String sex;
	
	private String address;
	
	private String phone;

	private Iterable<Note> notes;
	
	private String assessment;

	

	public Patient() {
		super();
	}
	
	public Patient(int id, String lastName, String firstName, String dob, String sex, 
			String address, String phone, Iterable<Note> notes, String assessment) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dob = dob;
		this.sex = sex;
		this.address = address;
		this.phone = phone;
		this.notes = notes;
		this.assessment = assessment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	

	public String getAssessment() {
		return assessment;
	}

	public void setAssessment(String assessment) {
		this.assessment = assessment;
	}	
	
	public Iterable<Note> getNotes() {
		return notes;
	}
	
	public void setNotes(Iterable<Note> notes) {
		this.notes = notes;
	}
	

}
