package com.example.mcc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class participant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberVoteId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;

    //투표한 날
    private LocalDate createdAt;

    //투표 하였는지
    @Column(columnDefinition = "BOOLEAN")
    private Boolean isCheck;

}
