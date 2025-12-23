package com.gymnastics.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Request {

    private LocalDate localDate;
    private String branch;
    private String fullName;
    private String phoneNumber;
    private String status;
    private String notes;

    public Request(String branch, String fullName, String phoneNumber, String status, String notes) {
        this.branch = branch;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.notes = notes;
    }
}
