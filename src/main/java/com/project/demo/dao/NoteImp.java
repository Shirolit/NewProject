package com.project.demo.dao;

import com.project.demo.entity.Note;
import com.project.demo.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class NoteImp implements NoteDao{

    private EntityManager entityManager;

    @Override
    public void saveNote(Note note) {
        Note newNote = entityManager.merge(note);
        note.setId(newNote.getId());
    }

    @Override
    public List<Note> getAllNote() {
        Query query = entityManager.createQuery("select note from Note ");// продумать как добавить владельца where user_id=:onwer
        List<Note> AllNote = query.getResultList();
        return AllNote;
    }

    @Override
    public void updateNote(Note note) {

    }

    @Override
    public Note getNote(int id) {
        Note note = entityManager.find(Note.class,id);
        return note;
    }

    @Override
    public Note getNoteForOwner(Person person) {
        Note note = entityManager.find(Note.class,person);
        return note;
    }

    @Override
    public void deleteNote(Note note) {
        Query query = entityManager.createQuery("delete from Note where id =:noteId");
        query.setParameter("noteId",note.getId());
    }
}
