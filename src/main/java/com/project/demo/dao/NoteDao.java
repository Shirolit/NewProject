package com.project.demo.dao;

import com.project.demo.entity.Note;
import com.project.demo.entity.Person;


import java.util.List;

public interface NoteDao {
    public void saveNote(Note note);

    public List<Note> getAllNote();

    public void updateNote(Note note);

    public Note getNote(int id);

    public Note getNoteForOwner(Person person);

    public void deleteNote(Note note);
}
