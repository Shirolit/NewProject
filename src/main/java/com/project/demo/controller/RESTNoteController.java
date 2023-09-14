package com.project.demo.controller;

import com.project.demo.Service.NoteServiceImp;
import com.project.demo.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Note")
public class RESTNoteController {
    @Autowired
    private NoteServiceImp NoteService;

    @PostMapping("")
    public List<Note> ShowAllPerson (){
        List <Note> allNote = NoteService.getAllNote();
        return allNote;
    }

    @GetMapping("/{id}")
    public Note getPerson(@PathVariable int id){
        Note note = NoteService.getNote(id);
        return note;
    }

    @PostMapping("")
    public Note addNewPerson(@RequestBody Note note){
        NoteService.saveNote(note);

        return note;
    }

    @PutMapping("")
    public Note updatePerson(@RequestBody Note note){
        NoteService.saveNote(note);

        return note;
    }

    @DeleteMapping("")
    public String deletePerson(@PathVariable int id){
        Note note = NoteService.getNote(id);

        NoteService.deleteNote(id);

        return "Note with id = " + id + " was delete";
    }
}
