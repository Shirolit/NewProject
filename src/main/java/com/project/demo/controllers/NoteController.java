package com.project.demo.controllers;

import com.project.demo.dto.NoteDTO;
import com.project.demo.dto.PersonDTO;
import com.project.demo.models.Note;
import com.project.demo.services.NoteService;
import com.project.demo.util.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/Note")
@AllArgsConstructor
public class NoteController {
    private NoteService NoteService;

    @GetMapping("")
    public List<NoteDTO> ShowAllNote (){
        return NoteService.findAll().stream().map(this::convertToNoteDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public NoteDTO getNote(@PathVariable("id") int id){
        return convertToNoteDTO(NoteService.findNote(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HttpStatus> crate(@RequestBody @Valid NoteDTO noteDTO, @NotNull BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMsg.append(error.getField()).append(" _ ").append(error.getDefaultMessage()).append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }

        NoteService.save(convertToNote(noteDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id){
        NoteService.delete(NoteService.findNote(id));

        return "Person with id = " + id + " was delete";
    }


    @PutMapping("/update")
    public String updatePerson(@RequestBody NoteDTO noteDTO){
        NoteService.update(convertToNote(noteDTO));

        return "Note was update";
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

    private Note convertToNote(NoteDTO noteDTO) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(noteDTO,Note.class);
    }

    private NoteDTO convertToNoteDTO(Note note) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(note, NoteDTO.class);
    }
}
