package com.gymnastics.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private Long userId;
    private String name;
    private String branch;
    private String phoneNumber;
    private String address;
}
