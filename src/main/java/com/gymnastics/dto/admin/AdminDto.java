package com.gymnastics.dto.admin;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
    private String name;
    private String branch;
    private String phoneNumber;
    private String address;
}
