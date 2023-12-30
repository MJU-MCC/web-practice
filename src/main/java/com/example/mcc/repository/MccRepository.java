package com.example.mcc.repository;

<<<<<<< Updated upstream
import com.example.mcc.Dto.UserDto;
import com.example.mcc.entity.User;
=======
import com.example.mcc.Dto.User;
import com.example.mcc.entity.UserEntity;
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
<<<<<<< Updated upstream
public interface MccRepository extends JpaRepository<User,Long> {
    Optional<UserDto> findByuserid(String userid);
=======
public interface MccRepository extends JpaRepository<UserEntity,Long> {
    Optional<User> findByuserid(String userid);
>>>>>>> Stashed changes
}
