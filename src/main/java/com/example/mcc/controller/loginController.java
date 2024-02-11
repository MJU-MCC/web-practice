package com.example.mcc.controller;

import com.example.mcc.Dto.LoginDto;
import com.example.mcc.Dto.memberDto;
import com.example.mcc.response.LoginResponse;
import com.example.mcc.service.MccLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static com.example.mcc.response.Message.*;

@Api(tags = "MCC 로그인 Api")
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
    @ApiOperation(value = "로그인 Api" , notes = "학번 , 휴대전화 뒷번호")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto inputmember){

        String memberNumber = inputmember.getMemberNumber();
        String memberPassword = inputmember.getMemberPassword();

        List<String> tokens = mccLoginService.login(memberNumber, memberPassword);

        if(tokens == null){
            response.setResult(LOGIN_FAIL , null , null);
            return ResponseEntity.badRequest().body(response);
        }
//        String accessToken = tokens.get(0);
//        String refreshToken = tokens.get(1);

        log.info("로그인 성공");
        response.setMessage(LOGIN_SUCCESS);
        response.setAccessToken(tokens.get(0));
        response.setRefreshToken(tokens.get(1));

        return ResponseEntity.ok().body(response);
    }


    // 로그아웃
    @ApiOperation(value = "로그아웃 Api" )
    @PostMapping("/logout")
    public ResponseEntity<LoginResponse> logout(){

        response.setResult(LOGOUT_SUCCESS,null,null);

        return ResponseEntity.ok().body(response);

    }

}
