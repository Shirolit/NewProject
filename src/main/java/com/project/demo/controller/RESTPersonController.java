package com.project.demo.controller;

import com.project.demo.Service.PersonServiceImp;
import com.project.demo.models.Person;
import com.project.demo.util.NoteErrorResponse;
import com.project.demo.util.PersonErrorResponse;
import com.project.demo.util.PersonNotCreatedException;
import com.project.demo.util.PersonNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Person")
public class RESTPersonController {
    @Autowired
    private PersonServiceImp personServiceImp;

    @PostMapping("")
    public List<Person> ShowAllPerson (){
        return personServiceImp.findAll();
    }

    @GetMapping("/{UserName}")
    public Person getPerson(@PathVariable("UserName") String UserName){
        return personServiceImp.findOne(UserName);
    }


    @PostMapping("")
    public ResponseEntity<HttpStatus> crate(@RequestBody @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errorMsg.append(error.getField()).append(" _ ").append(error.getDefaultMessage()).append(";");
            }

            throw new PersonNotCreatedException(errorMsg.toString());
        }

        personServiceImp.save(person);

        return ResponseEntity.ok(HttpStatus.OK);

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
//
//    @PutMapping("")
//    public Person updatePerson(@RequestBody Person person){
//        personServiceImp.savePerson(person);
//
//        return person;
//    }
//
//    @DeleteMapping("")
//    public String deletePerson(@PathVariable String UserName){
//        Person person = personServiceImp.getPerson(UserName);
//
//        personServiceImp.deletePerson(UserName);
//
//        return "Person with user name = " + UserName + " was delete";
//    }

}
