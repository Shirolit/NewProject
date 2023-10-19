package com.project.demo.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteDTO {
    @Size(min = 0, max = 10000, message = "NoteTitle should be between 0 and 10000 characters")
    private String note;

    @NotEmpty(message = "NoteTitle should not be empty")
    @Size(min = 5, max = 50, message = "NoteTitle should be between 5 and 50 characters")
    private String NoteTitle;

}
