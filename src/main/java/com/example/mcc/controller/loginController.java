package com.example.mcc.controller;

import com.example.mcc.Dto.memberDto;
import com.example.mcc.response.LoginResponse;
import com.example.mcc.service.MccLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import static com.example.mcc.response.Message.*;

@RestController
@RequestMapping("/mcc")
@Slf4j
public class loginController {

    private final MccLoginService mccLoginService;
    private final LoginResponse response;

    public loginController(MccLoginService mccLoginService, LoginResponse response) {
        this.mccLoginService = mccLoginService;
        this.response = response;
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody memberDto inputuser){

        String token = mccLoginService.login(inputuser.getMemberNumber(),inputuser.getMemberPassword());


        log.info("로그인 성공");
        response.setResult(LOGIN_SUCCESS , token);

        return ResponseEntity.ok().body(response);
    }


    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(){

        response.setResult(LOGOUT_SUCCESS,null);

        return ResponseEntity.ok().body(response);

    }

}
