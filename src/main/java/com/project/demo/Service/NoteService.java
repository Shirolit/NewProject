package com.project.demo.Service;

import com.project.demo.entity.Note;
import com.project.demo.entity.Person;

import java.util.List;

public interface NoteService {

    public void saveNote(Note note);

    public List<Note> getAllNote();

    public void updateNote(Note note);

    public Note getNote(int id);

    public Note getNoteForOwner(Person person);

    public void deleteNote(int id);

}
