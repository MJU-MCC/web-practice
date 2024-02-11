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
public class RegistVoteDto {

    //투표 제목
    @ApiModelProperty(example = "2024학년도 우수 프로젝트 투표")
    private String VoteName;

    //평가 : 평가항목
    @ApiModelProperty(example = "창의성")
    private String EvaluationName;

    //평가 : 평가 받을 팀
    @ApiModelProperty(example = "개똥이팀")
    private String TeamName;
}
