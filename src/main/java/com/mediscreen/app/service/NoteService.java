package com.mediscreen.app.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
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
	
	public Note getNote(final int id) {
		return noteProxy.getNote(id);
	}
	
	public void deleteNote(final ObjectId id) {
		noteProxy.deleteNote(id);
	}
	
	public Note saveNote(Note note) {
		Note savedNote;

		savedNote = noteProxy.createNote(note);
		
		return savedNote;
	}

}
