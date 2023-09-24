package com.project.demo.controller;

import com.project.demo.Service.NoteServiceImp;
import com.project.demo.models.Note;
import com.project.demo.models.Person;
import com.project.demo.util.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Note")
public class RESTNoteController {
    @Autowired
    private NoteServiceImp NoteService;

    @PostMapping("")
    public List<Note> ShowAllPerson (){
        List <Note> allNote = NoteService.findAll();
        return allNote;
    }

    @GetMapping("/{id}")
    public Note getPerson(@PathVariable("id") int id){
        Note note = NoteService.findNote(id);
        return note;
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> crate(@RequestBody @Valid Note note, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMsg.append(error.getField()).append(" _ ").append(error.getDefaultMessage()).append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }

        NoteService.save(note);

        return ResponseEntity.ok(HttpStatus.OK);

    }
    @ExceptionHandler
    private ResponseEntity<NoteErrorResponse> handleException(NoteErrorResponse e){
        NoteErrorResponse response = new NoteErrorResponse("Note with this id wasn't found!",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<NoteErrorResponse> handleException(NoteNotCreatedException e){
        NoteErrorResponse response = new NoteErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
