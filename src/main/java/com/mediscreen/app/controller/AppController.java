package com.mediscreen.app.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mediscreen.app.model.Note;
import com.mediscreen.app.model.Patient;
import com.mediscreen.app.model.DTO.PatientDTO;
import com.mediscreen.app.service.NoteService;
import com.mediscreen.app.service.PatientService;
import com.mediscreen.app.util.ReadTriggersDataFromFile;
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
	 * 
	 * 
	 * Home
	 * 
	 * 
	 */
	@RequestMapping("/home")
	public String home(Model model) {
		Iterable<Patient> patients = patientService.getPatients();
		model.addAttribute("patients", patients);
		return "home";
	}

	/**
	 * 
	 * 
	 * Assessment
	 * 
	 * 
	 */
	@RequestMapping("/assessPatient/{patientId}")
	public String assessPatient(@PathVariable("patientId") final int patientId, Model model) {
		
		// Récupère du patient
		Patient patient = patientService.getPatient(patientId);

		// Création du DTO
		PatientDTO patientToAssess = new PatientDTO();
		patientToAssess.setId(patient.getId());
		patientToAssess.setLastName(patient.getLastName());
		patientToAssess.setFirstName(patient.getFirstName());
		patientToAssess.setDob(patient.getDob());
		patientToAssess.setSex(patient.getSex());
		
		// Récupère les notes du patient
		Iterable<Note> patientNotes = noteService.getPatientNotes(patientId);
		patientToAssess.setNotes(patientNotes);

		// Récupère la liste des triggers

		ReadTriggersDataFromFile triggers = new ReadTriggersDataFromFile("triggers.txt");
		ArrayList<String> triggersList = (ArrayList<String>) triggers.GetTriggers();
		patientToAssess.setTriggersList(triggersList);
		
		// Récupère son évaluaion
		PatientDTO AssessedPatient = assessmentService.getAssessment(patientToAssess);

		model.addAttribute("patient", AssessedPatient);

		return "assessment";
	}
	
	@GetMapping("/assessment")
	public String assessment(@ModelAttribute("assessment") Patient patient) {
		
		// Récupère son évluaion
		// String assessment = assessmentService.getAssessment(patient.getId(), patientNotes);
		
		
		return "assessment";
	}

	/**
	 * 
	 * 
	 * Patient
	 * 
	 * 
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

	@PostMapping("/savePatient")
	public String validateAdd(@ModelAttribute Patient patient) {
		patientService.savePatient(patient);
		return "redirect:/patients";
	}

	/**
	 * 
	 * 
	 * Patient & Note : delete all patient's notes
	 * 
	 * 
	 */
	@GetMapping("/deletePatient/{id}")
	public String deletePatient(@PathVariable("id") Integer id) {
		patientService.deletePatient(id);
		noteService.deletePatientNotes(id);
		return "redirect:/patients";
	}

	/**
	 * 
	 * 
	 * Note
	 * 
	 * 
	 */
	@RequestMapping("/notes")
	public String notes(Model model) {
		Iterable<Note> notes = noteService.getNotes();
		model.addAttribute("notes", notes);
		return "notes";
	}

	@RequestMapping("/notes/{patientId}")
	public String notes(@PathVariable("patientId") final int patientId, Model model) {
		Iterable<Note> notes = noteService.getPatientNotes(patientId);
		model.addAttribute("notes", notes);
		Patient patient = patientService.getPatient(patientId);
		model.addAttribute("patient", patient);
		return "patientNotes";
	}

	@GetMapping("/createNote")
	public String createNote(Model model) {
		Note n = new Note();
		model.addAttribute("note", n);
		return "formNewNote";
	}

	@GetMapping("/createPatientNote/{patientId}")
	public String createPatientNote(@PathVariable("patientId") final int patientId, Model model) {
		Note n = new Note();
		n.setPatientId(patientId);
		model.addAttribute("note", n);
		return "formNewPatientNote";
	}

	@GetMapping("/deleteNote/{id}")
	public String deleteNote(@PathVariable("id") String id) {
		noteService.deleteNote(id);
		return "redirect:/notes";
	}

	@GetMapping("/updateNote/{id}")
	public String updateNote(@PathVariable("id") String id, Model model) {
		Note n = noteService.getNote(id);
		model.addAttribute("note", n);
		return "formUpdateNote";
	}

	@PostMapping("/saveNote")
	public String validateAdd(@ModelAttribute Note note) {
		Optional<Note> n = Optional.ofNullable(noteService.getNote(note.getId()));
		if (n.isPresent()) {
			Note updatedNote = n.get();
			updatedNote.setPatientId(note.getPatientId());
			updatedNote.setRecommendation(note.getRecommendation());
			noteService.saveNote(updatedNote);
		} else {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			note.setDate(timestamp);
			noteService.saveNote(note);
		}
		return "redirect:/notes";
	}

}
