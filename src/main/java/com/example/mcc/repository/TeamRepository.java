package com.example.mcc.repository;

import com.example.mcc.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    public boolean existsByTeamName(String teamName);
    public Team findTeamByTeamName(String teamName);
}
