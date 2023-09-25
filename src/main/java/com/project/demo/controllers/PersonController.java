package com.project.demo.controllers;

import com.project.demo.services.PersonService;
import com.project.demo.dto.PersonDTO;
import com.project.demo.models.Person;
import com.project.demo.util.PersonErrorResponse;
import com.project.demo.util.PersonNotCreatedException;
import com.project.demo.util.PersonNotFoundException;
import jakarta.validation.Valid;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/Person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @PostMapping("")
    public List<PersonDTO> ShowAllPerson (){
        return personService.findAll().stream().map(this::convertToPersonDTO).collect(Collectors.toList());
    }

    @GetMapping("/{UserName}")
    public PersonDTO getPerson(@PathVariable("UserName") String UserName){
        return convertToPersonDTO(personService.findOne(UserName));
    }


    @PostMapping("")
    public ResponseEntity<HttpStatus> crate(@RequestBody @Valid PersonDTO personDTO, @NotNull BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMsg.append(error.getField()).append(" _ ").append(error.getDefaultMessage()).append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }

        personService.save(convertToPerson(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("")
    public String deletePerson(@PathVariable String UserName){
        personService.delete(personService.findOne(UserName));

        return "Person with user name = " + UserName + " was delete";
    }

    @PutMapping("")
    public PersonDTO updatePerson(@RequestBody PersonDTO personDTO){
        personService.update(convertToPerson(personDTO));

        return personDTO;
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotFoundException e){
        PersonErrorResponse response = new PersonErrorResponse("Person with this user name wasn't found!",System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(PersonNotCreatedException e){
        PersonErrorResponse response = new PersonErrorResponse(e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Person convertToPerson(PersonDTO personDTO) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(personDTO,Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(person, PersonDTO.class);
    }

}
