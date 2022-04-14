package com.mediscreen.app.model;

import org.bson.types.ObjectId;

public class Note {

	private String id;
	
	private int patientId;
	
	private String recommandation;
	
	public Note() {
		super();
	}

	public Note(int patientId, String recommandation) {
		this.patientId = patientId;
		this.recommandation = recommandation;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getRecommandation() {
		return recommandation;
	}

	public void setRecommandation(String recommandation) {
		this.recommandation = recommandation;
	}
	

}
