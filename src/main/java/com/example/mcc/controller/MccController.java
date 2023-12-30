package com.example.mcc.controller;

<<<<<<< Updated upstream
import com.example.mcc.Dto.UserDto;
=======
import com.example.mcc.Dto.User;
>>>>>>> Stashed changes
import com.example.mcc.response.Response;
import com.example.mcc.service.MccService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.example.mcc.response.Message.*;
import static com.example.mcc.response.Response.*;


@RestController
@RequestMapping("/mcc")
@AllArgsConstructor
@Slf4j
public class MccController {

    private final MccService mccService;

    @PostMapping("/login")
<<<<<<< Updated upstream
    public Response login(@RequestBody UserDto inputuser){
=======
    public Response login(@RequestBody User inputuser){
>>>>>>> Stashed changes
        //로그인 아이디 비밀번호 입력 받기
        String userid = inputuser.getUserid();
        String password = inputuser.getPassword();

        //로그인 서비스 로직
<<<<<<< Updated upstream
        Optional<UserDto> loginUser = mccService.login(userid, password);
=======
        Optional<User> loginUser = mccService.login(userid, password);
>>>>>>> Stashed changes
        if(loginUser.isEmpty()){
            //로그인 실패 한다면
            log.info("로그인 실패");
            return fail(LOGIN_FAIL);
        }
        log.info("로그인 성공");
        return success(LOGIN_SUCCESS,loginUser);
    }
}
