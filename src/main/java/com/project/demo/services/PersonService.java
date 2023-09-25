package com.project.demo.services;

import com.project.demo.models.Person;
import com.project.demo.repositories.PersonRepository;
import com.project.demo.util.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(String UserName) {
        Optional<Person> foundPerson = personRepository.findById(UserName);
        return foundPerson.orElseThrow(PersonNotFoundException::new);
    }
    @Transactional
    public void save(Person person){
        personRepository.save(person);
    }
    @Transactional
    public void update(Person person){
        personRepository.save(person);
    }
    @Transactional
    public void delete(Person person){
        personRepository.delete(person);
    }
}
