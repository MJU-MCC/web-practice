package com.example.mcc.Dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistVoteDto {

    @ApiModelProperty(
            name = "투표 제목",
            example = "2024학년도 우수 프로젝트 투표"
    )
    private String voteName;

    @Schema(name = "평가 항목들")
    private List<String> evaluationName;

    @ApiModelProperty(
            name = "평가 받을 팀들"
    )
    private List<String> teamName;
}
