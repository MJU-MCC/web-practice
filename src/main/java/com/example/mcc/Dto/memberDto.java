package com.example.mcc.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class memberDto {

    private String memberNumber;
    private String memberPassword;
    private String role;
}
