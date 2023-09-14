package com.project.demo.controller;

import com.project.demo.Service.PersonService;
import com.project.demo.Service.PersonServiceImp;
import com.project.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Person")
public class RESTPersonController {
    @Autowired
    private PersonServiceImp personServiceImp;

    @PostMapping("")
    public List<Person> ShowAllPerson (){
        List <Person> allPerson = personServiceImp.getAllPerson();
        return allPerson;
    }

    @GetMapping("/{UserName}")
    public Person getPerson(@PathVariable String UserName){
        Person person = personServiceImp.getPerson(UserName);
        return person;
    }

    @PostMapping("")
    public Person addNewPerson(@RequestBody Person person){
        personServiceImp.savePerson(person);

        return person;
    }

    @PutMapping("")
    public Person updatePerson(@RequestBody Person person){
        personServiceImp.savePerson(person);

        return person;
    }

    @DeleteMapping("")
    public String deletePerson(@PathVariable String UserName){
        Person person = personServiceImp.getPerson(UserName);

        personServiceImp.deletePerson(UserName);

        return "Person with user name = " + UserName + " was delete";
    }

}
