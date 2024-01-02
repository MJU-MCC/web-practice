package com.example.mcc.repository;

import com.example.mcc.Dto.UserDto;
import com.example.mcc.entity.User;
import com.example.mcc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MccRepository extends JpaRepository<User,Long> {
    Optional<UserDto> findByuserid(String userid);
}
