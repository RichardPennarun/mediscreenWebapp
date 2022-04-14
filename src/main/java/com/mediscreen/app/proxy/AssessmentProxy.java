package com.mediscreen.app.proxy;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mediscreen.app.config.CustomProp;
import com.mediscreen.app.model.Note;
import com.mediscreen.app.model.Patient;

@Component
public class AssessmentProxy {
	
	@Autowired
	private CustomProp props;
	
	public Patient getAssessment(Patient patientToAssess) {
		String baseApiUrl = props.getAssessmentApiUrl();
		String getAssessmentUrl = baseApiUrl + "/assessment";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Patient> request = new HttpEntity<Patient>(patientToAssess);
		ResponseEntity<Patient> response = restTemplate.exchange(
				getAssessmentUrl, 
				HttpMethod.POST, 
				request, 
				Patient.class);
		
		return response.getBody();
	}

}
