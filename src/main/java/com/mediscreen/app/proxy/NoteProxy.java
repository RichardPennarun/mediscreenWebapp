package com.mediscreen.app.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mediscreen.app.config.CustomProp;
import com.mediscreen.app.model.Note;

@Component
public class NoteProxy {

	@Autowired
	private CustomProp props;

	public Iterable<Note> getPatientNotes(int patientId) {

		String baseApiUrl = props.getNoteApiUrl();
		String getPatientNotesUrl = baseApiUrl + "/notes/" + patientId;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Note>> response = restTemplate.exchange(getPatientNotesUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<Note>>() {
				});

		return response.getBody();
	}

	public Note getNote(String id) {
		String baseApiUrl = props.getNoteApiUrl();
		String getNoteUrl = baseApiUrl + "/note/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Note> response = restTemplate.exchange(getNoteUrl, HttpMethod.GET, null, Note.class);

		return response.getBody();
	}

	public Iterable<Note> getNotes() {

		String baseApiUrl = props.getNoteApiUrl();
		String getNotesUrl = baseApiUrl + "/notes";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Note>> response = restTemplate.exchange(getNotesUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<Iterable<Note>>() {
				});

		return response.getBody();
	}

	public Note createNote(Note note) {
		String baseApiUrl = props.getNoteApiUrl();
		String createNoteUrl = baseApiUrl + "/note";

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Note> request = new HttpEntity<Note>(note);
		ResponseEntity<Note> response = restTemplate.exchange(createNoteUrl, HttpMethod.POST, request, Note.class);

		return response.getBody();
	}

	public void deleteNote(String id) {
		String baseApiUrl = props.getNoteApiUrl();
		String deleteNoteUrl = baseApiUrl + "/note/" + id;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(deleteNoteUrl, HttpMethod.DELETE, null, Void.class);
	}

	public void deleteNotes(int patientId) {
		String baseApiUrl = props.getNoteApiUrl();
		String deleteNotesUrl = baseApiUrl + "/notes/" + patientId;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(deleteNotesUrl, HttpMethod.DELETE, null, Void.class);
	}

}
