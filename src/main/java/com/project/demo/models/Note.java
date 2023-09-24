package com.project.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @Column(name = "Id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "user_name")
    public Person onwer;

    @Column(name = "note_value")
    @Size(min = 0, max = 10000, message = "NoteTitle should be between 0 and 10000 characters")
    private String note;

    @Column(name = "Note_Title")
    @NotEmpty(message = "NoteTitle should not be empty")
    @Size(min = 5, max = 50, message = "NoteTitle should be between 5 and 50 characters")
    private String NoteTitle;

    public Note() {
    }

    public Note(int id, String note, String noteTitle) {
        this.id = id;
        this.note = note;
        this.NoteTitle = noteTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Person getOnwer() {
        return onwer;
    }

    public void setOnwer(Person onwer) {
        this.onwer = onwer;
    }
    public String getNoteTitle() {
        return NoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", onwer=" + onwer +
                ", note='" + note + '\'' +
                ", NoteTitle='" + NoteTitle + '\'' +
                '}';
    }
}
