package com.gymnastics.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
    private String branch;
    private String fullName;
    private String phoneNumber;
    private String status;
    private String notes;
}
