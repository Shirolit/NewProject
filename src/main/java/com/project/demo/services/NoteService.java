package com.project.demo.services;

import com.project.demo.models.Note;
import com.project.demo.models.Person;
import com.project.demo.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NoteService {

    private final NoteRepository noteRepository;
    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note findNote(int id) {
        Optional<Note> foundNote = noteRepository.findById(id);
        return foundNote.orElseThrow();
    }
    @Transactional
    public void save(Note note){
        noteRepository.save(note);
    }
    @Transactional
    public void update(Note note){
        noteRepository.save(note);
    }
    @Transactional
    public void delete(Note note){
        noteRepository.delete(note);
    }

}
