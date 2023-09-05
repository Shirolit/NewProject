package com.project.demo.dao;

import com.project.demo.entity.Person;

import java.util.List;

public interface PersonDao {
    public void savePerson(Person person);

    public List<Person> getAllPerson();

    public void updatePerson(Person person);

    public Person getPerson(Person person);

    public void deletePerson(Person person);
}
