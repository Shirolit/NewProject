package com.project.demo.entity;

import jakarta.persistence.*;

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
    private String note;

    public Note() {
    }

    public Note(int id, String note) {
        this.id = id;
        this.note = note;
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

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", onwer=" + onwer +
                ", note='" + note + '\'' +
                '}';
    }
}
