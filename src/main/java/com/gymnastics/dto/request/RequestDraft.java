package com.gymnastics.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDraft {
    private String branch;
    private String fullName;
    private String phone;
    private String status;
    private String comment;
}
