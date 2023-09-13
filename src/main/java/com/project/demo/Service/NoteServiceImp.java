package com.project.demo.Service;

import com.project.demo.dao.NoteDao;
import com.project.demo.entity.Note;
import com.project.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class NoteServiceImp implements NoteService{

    @Autowired
    NoteDao noteDao;
    @Override
    @Transactional
    public void saveNote(Note note) {
        noteDao.saveNote(note);
    }

    @Override
    @Transactional
    public List<Note> getAllNote() {
        return noteDao.getAllNote();
    }

    @Override
    @Transactional
    public void updateNote(Note note) {

    }

    @Override
    @Transactional
    public Note getNote(int id) {
        return noteDao.getNote(id);
    }

    @Override
    @Transactional
    public Note getNoteForOwner(Person person) {
        return noteDao.getNoteForOwner(person);
    }

    @Override
    @Transactional
    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }
}
