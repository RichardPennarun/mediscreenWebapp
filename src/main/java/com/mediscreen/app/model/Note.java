package com.mediscreen.app.model;

import java.util.Date;

public class Note {

	private String id;
	
	private Date date;
	
	private int patientId;
	
	private String recommendation;
	
	public Note() {
		super();
	}

	public Note(String id, Date date, int patientId, String recommendation) {
		super();
		this.id = id;
		this.date = date;
		this.patientId = patientId;
		this.recommendation = recommendation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date timestamp) {
		this.date = timestamp;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	

}
