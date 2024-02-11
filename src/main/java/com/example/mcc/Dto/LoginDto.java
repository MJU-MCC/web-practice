package com.example.mcc.Dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

        @ApiModelProperty(example = "60205678")
        private String memberNumber;

        @ApiModelProperty(example = "1234")
        private String memberPassword;
}
