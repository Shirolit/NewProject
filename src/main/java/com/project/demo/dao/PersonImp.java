package com.project.demo.dao;

import com.project.demo.entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonImp implements PersonDao{
    private EntityManager entityManager;

    @Override
    public void savePerson(Person person) {
        Person NewPerson = entityManager.merge(person);
        person.setUserName(NewPerson.getUserName());
    }

    @Override
    public List<Person> getAllPerson() {
        Query query = entityManager.createQuery("SELECT Person from Person ");
        List<Person> allPeople = query.getResultList();

        return allPeople;
    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public Person getPerson(String UserName) {
        Person GetPerson = entityManager.find(Person.class,UserName);
        return GetPerson;
    }

    @Override
    public void deletePerson(String UserName) {
        Query query = entityManager.createQuery("delete from Person where UserName =:DeletePerson");
        query.setParameter("DeletePerson", UserName);
        query.executeUpdate();
    }
}
