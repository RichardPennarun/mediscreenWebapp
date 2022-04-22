package com.mediscreen.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.app.model.DTO.PatientDTO;
import com.mediscreen.app.proxy.AssessmentProxy;

@Service
public class AssessmentService {

	@Autowired
	private AssessmentProxy assessmentProxy;

	public PatientDTO getAssessment(PatientDTO patientToAssess) {

		PatientDTO assessedPatient = assessmentProxy.getAssessment(patientToAssess);

		return assessedPatient;

	}

}
