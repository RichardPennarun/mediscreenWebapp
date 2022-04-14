package com.mediscreen.app.controller;

import java.io.FileNotFoundException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.mediscreen.app.model.Note;
import com.mediscreen.app.model.Patient;
import com.mediscreen.app.service.NoteService;
import com.mediscreen.app.service.PatientService;
import com.mediscreen.app.service.AssessmentService;


@Controller
public class AppController {
	
	@Autowired
	private PatientService patientService;

	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private AssessmentService assessmentService;

	

	/**
	 * Test
	 * @throws Exception 
	 */
	@RequestMapping("/assessPatient/{patientId}")
	public String assessPatient(@PathVariable("patientId") final int patientId, Model model) {
		 // Récupère du patient
		Patient patientToAssess = patientService.getPatient(patientId);
		
		// Récupère les notes du patient
		Iterable<Note> patientNotes = noteService.getPatientNotes(patientId);
		patientToAssess.setNotes(patientNotes);

		// Récupère son évluaion
		Patient assessedPatient = assessmentService.getAssessment(patientToAssess);

		//model.addAttribute("assessment", assessedPatient);
		//model.addAttribute("patientNotes", patientNotes);
		model.addAttribute("patient", assessedPatient);
				
		return "assessment";
	}
	
	@GetMapping("/assessment")
	public String assessment(@ModelAttribute("assessment") Patient patient) {
		
		// Récupère son évluaion
		// String assessment = assessmentService.getAssessment(patient.getId(), patientNotes);
		
		
		return "assessment";
	}
	
	

	/**
	 * Patient
	 */
	@RequestMapping("/patients")
	public String patients(Model model) {
		Iterable<Patient> patients = patientService.getPatients();
		model.addAttribute("patients", patients);
		return "patients";
	}

	@GetMapping("/createPatient")
	public String createPatient(Model model) {
		Patient p = new Patient();
		model.addAttribute("patient", p);
		return "formNewPatient";
	}

	@GetMapping("/updatePatient/{id}")
	public String updatePatient(@PathVariable("id") Integer id, Model model) {
		Patient p = patientService.getPatient(id);
		model.addAttribute("patient", p);
		return "formUpdatePatient";
	}

	@GetMapping("/deletePatient/{id}")
	public String deletePatient(@PathVariable("id") Integer id) {
		patientService.deletePatient(id);
		return "redirect:/patients";
	}

	@PostMapping("/savePatient")
	public String validateAdd(@ModelAttribute Patient patient) {
		patientService.savePatient(patient);
		return "redirect:/patients";
	}

	

	/**
	 * Note
	 */
	@RequestMapping("/notes")
	public String notes(Model model) {
		Iterable<Note> notes = noteService.getNotes();
		model.addAttribute("notes", notes);
		return "notes";
	}

	@GetMapping("/createNote")
	public String createNote(Model model) {
		Note n = new Note();
		model.addAttribute("note", n);
		return "formNewNote";
	}

	/*
	 * @GetMapping("/updateNote/{id}") public String updateNote(@PathVariable("id")
	 * Integer id, Model model) { Note n = noteService.getNote(id);
	 * model.addAttribute("note", n); return "formUpdateNote"; }
	 */

	@GetMapping("/deleteNote/{id}")
	public String deleteNote(@PathVariable("id") ObjectId id) {
		noteService.deleteNote(id);
		return "redirect:/notes";
	}

	@PostMapping("/saveNote")
	public String validateAdd(@ModelAttribute Note note) {
		noteService.saveNote(note);
		return "redirect:/notes";
	}
	
}
