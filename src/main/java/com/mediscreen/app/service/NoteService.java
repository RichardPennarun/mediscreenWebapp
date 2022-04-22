package com.mediscreen.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediscreen.app.model.Note;
import com.mediscreen.app.proxy.NoteProxy;

@Service
public class NoteService {

    @Autowired
    private NoteProxy noteProxy;


    public Iterable<Note> getPatientNotes(final int patientId) {
        return noteProxy.getPatientNotes(patientId);
    }
    
    public Iterable<Note> getNotes() {
        return noteProxy.getNotes();
    }
	
	public Note getNote(final String string) {
		return noteProxy.getNote(string);
	}
	
	public void deleteNote(final String id) {
		noteProxy.deleteNote(id);
	}
	
	public Note saveNote(Note note) {
		return noteProxy.createNote(note);
	}

	public void deletePatientNotes(final int patientId) {
		noteProxy.deleteNotes(patientId);
		
	}

}
