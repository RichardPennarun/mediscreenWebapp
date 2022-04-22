package com.mediscreen.app.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mediscreen.app.config.CustomProp;
import com.mediscreen.app.model.DTO.PatientDTO;

@Component
public class AssessmentProxy {
	
	@Autowired
	private CustomProp props;
	
	public PatientDTO getAssessment(PatientDTO patientToAssess) {
		String baseApiUrl = props.getAssessmentApiUrl();
		String getAssessmentUrl = baseApiUrl + "/getAssessment";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<PatientDTO> request = new HttpEntity<PatientDTO>(patientToAssess);
		ResponseEntity<PatientDTO> response = restTemplate.exchange(
				getAssessmentUrl, 
				HttpMethod.POST, 
				request, 
				PatientDTO.class);
		
		return response.getBody();
	}

}
