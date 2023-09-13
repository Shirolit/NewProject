package com.project.demo.Service;

import com.project.demo.dao.PersonDao;
import com.project.demo.entity.Person;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImp implements PersonService {

    @Autowired
    public PersonDao personDao;

    @Override
    @Transactional
    public void savePerson(Person person) {
        personDao.savePerson(person);
    }

    @Override
    @Transactional
    public List<Person> getAllPerson() {
        return personDao.getAllPerson();
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        personDao.updatePerson(person);
    }

    @Override
    @Transactional
    public Person getPerson(Person person) {
        return personDao.getPerson(person);
    }

    @Override
    @Transactional
    public void deletePerson(Person person) {
        personDao.deletePerson(person);
    }
}
