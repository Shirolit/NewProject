package com.project.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person{
    @Id
    @Column(name = "user_name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String UserName;

    @Column(name = "email")
    @Email
    @NotEmpty(message = "Email should not be empty")
    private String Email;

    @Column(name = "password")
    @NotEmpty(message = "Password should not be empty")
    @Size(min = 6, max = 30, message = "Password should be between 6 and 30 characters")
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

