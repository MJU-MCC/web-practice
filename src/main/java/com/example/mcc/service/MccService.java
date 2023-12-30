package com.example.mcc.service;

<<<<<<< Updated upstream
import com.example.mcc.Dto.UserDto;
import com.example.mcc.repository.MccRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
=======
import com.example.mcc.Dto.User;
import com.example.mcc.repository.MccRepository;
import lombok.AllArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
<<<<<<< Updated upstream
@Slf4j
=======
>>>>>>> Stashed changes
public class MccService {

    private final MccRepository mccRepository;

<<<<<<< Updated upstream
    public Optional<UserDto> login(String userid, String password){
        Optional<UserDto> findUser = mccRepository.findByuserid(userid);

        if(findUser.isPresent()){
            //저장소에 유저가 존재한다면
            UserDto successLoginUserDto = findUser.get();
            log.info("findUser 객체의 비밀번호 = {}", findUser.get().getPassword());

            if(successLoginUserDto.getPassword().equals(password)){
=======
    public Optional<User> login(String userid, String password){
        Optional<User> findUser = mccRepository.findByuserid(userid);

        if(findUser.isPresent()){
            //저장소에 유저가 존재한다면
            User successLoginUser = findUser.get();
            if(successLoginUser.getPassword().equals(password)){
>>>>>>> Stashed changes
                //회원이 입력한 비밀번호와 저장소에 있는 비밀번호가 같은지 확인하기
                return findUser;
            }else{
                //비밀번호가 다르다면
                return Optional.empty();
            }
        }else{
            return Optional.empty();
        }
}
}
