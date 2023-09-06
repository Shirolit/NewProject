package com.project.demo.repositories;

import com.project.demo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NoteRepository extends JpaRepository<Note,Integer> {
    Optional<Note> findByNoteTitle(String Title);
}
