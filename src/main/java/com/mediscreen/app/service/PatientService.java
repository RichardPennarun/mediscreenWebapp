package com.mediscreen.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.app.model.Patient;
import com.mediscreen.app.proxy.PatientProxy;

@Service
public class PatientService {

    @Autowired
    private PatientProxy patientProxy;

	
    public Iterable<Patient> getPatients() {
        return patientProxy.getPatients();
    }
	
	public Patient getPatient(final int id) {
		return patientProxy.getPatient(id);
	}
	
	public void deletePatient(final int id) {
		patientProxy.deletePatient(id);
	}
	
	public Patient savePatient(Patient patient) {
		Patient savedPatient;

		if(patient.getId() == 0) {
			savedPatient = patientProxy.createPatient(patient);
		} else {
			savedPatient = patientProxy.updatePatient(patient);
		}
		
		return savedPatient;
	}

}
