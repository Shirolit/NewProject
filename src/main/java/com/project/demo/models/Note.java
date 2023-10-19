package com.project.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "note")
public class Note {
    @Id
    @Column(name = "Id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "user_name")
    private Person owner;

    @Column(name = "note_value")
    @Size(min = 0, max = 10000, message = "NoteTitle should be between 0 and 10000 characters")
    private String note;

    @Column(name = "Note_Title")
    @NotEmpty(message = "NoteTitle should not be empty")
    @Size(min = 5, max = 50, message = "NoteTitle should be between 5 and 50 characters")
    private String NoteTitle;

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", onwer=" + owner +
                ", note='" + note + '\'' +
                ", NoteTitle='" + NoteTitle + '\'' +
                '}';
    }
}
