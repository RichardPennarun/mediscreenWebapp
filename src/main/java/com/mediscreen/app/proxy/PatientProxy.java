package com.mediscreen.app.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mediscreen.app.config.CustomProp;
import com.mediscreen.app.model.Patient;

@Component
public class PatientProxy {

	@Autowired
	private CustomProp props;

	public Iterable<Patient> getPatients() {

		String baseApiUrl = props.getPatientApiUrl();
		String getPatientsUrl = baseApiUrl + "/patients";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Patient>> response = restTemplate.exchange(getPatientsUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<Patient>>() {
				});

		return response.getBody();
	}

	public Patient getPatient(int id) {
		String baseApiUrl = props.getPatientApiUrl();
		String getPatientUrl = baseApiUrl + "/patient/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Patient> response = restTemplate.exchange(getPatientUrl, HttpMethod.GET, null, Patient.class);

		return response.getBody();
	}

	public Patient createPatient(Patient e) {
		String baseApiUrl = props.getPatientApiUrl();
		String createPatientUrl = baseApiUrl + "/patient";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Patient> request = new HttpEntity<Patient>(e);
		ResponseEntity<Patient> response = restTemplate.exchange(createPatientUrl, HttpMethod.POST, request,
				Patient.class);

		return response.getBody();
	}

	public Patient updatePatient(Patient e) {
		String baseApiUrl = props.getPatientApiUrl();
		String updatePatientUrl = baseApiUrl + "/patient/" + e.getId();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Patient> request = new HttpEntity<Patient>(e);
		ResponseEntity<Patient> response = restTemplate.exchange(updatePatientUrl, HttpMethod.PUT, request,
				Patient.class);

		return response.getBody();
	}

	public void deletePatient(int id) {
		String baseApiUrl = props.getPatientApiUrl();
		String deletePatientUrl = baseApiUrl + "/patient/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(deletePatientUrl, HttpMethod.DELETE, null, Void.class);
	}

}
