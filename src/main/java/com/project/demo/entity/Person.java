package com.project.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person{
    @Id
    @Column(name = "user_name")
    private String UserName;

    @Column(name = "email")
    private String Email;

    @Column(name = "password")
    private String Password;

    @Column(name = "enable")
    private int enable;

    @OneToMany(mappedBy = "owner")
    private List<Note> notes;

    public Person(String UserName, String email, String password, int enable) {
        this.UserName = UserName;
        this.Email = email;
        this.Password = password;
        this.enable = enable;
    }

    public Person() {
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Person{" +
                "UserName=" + UserName +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", enable=" + enable +
                '}';
    }
}

