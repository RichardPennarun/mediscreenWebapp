package com.mediscreen.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="com.mediscreen.app")
public class CustomProp {

	private String patientApiUrl;

	private String noteApiUrl;

	private String assessmentApiUrl;
	
	public String getPatientApiUrl() {
		return patientApiUrl;
	}
	
	public void setPatientApiUrl(String patientApiUrl) {
		this.patientApiUrl = patientApiUrl;
	}
	
	public String getNoteApiUrl() {
		return noteApiUrl;
	}
	
	public void setNoteApiUrl(String noteApiUrl) {
		this.noteApiUrl = noteApiUrl;
	}

	public String getAssessmentApiUrl() {
		return assessmentApiUrl;
	}

	public void setAssessmentApiUrl(String assessmentApiUrl) {
		this.assessmentApiUrl = assessmentApiUrl;
	}

}
