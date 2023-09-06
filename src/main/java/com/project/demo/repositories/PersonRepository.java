package com.project.demo.repositories;

import com.project.demo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository extends JpaRepository<Person,String> {

    Optional<Person> findByUserName(String name);

}
