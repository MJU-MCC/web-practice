package com.example.mcc.controller;

import com.example.mcc.Dto.UserDto;
import com.example.mcc.entity.User;
import com.example.mcc.response.Response;
import com.example.mcc.service.MccService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Response login(
            @RequestBody UserDto inputuser
            , HttpServletRequest request){
        //로그인 아이디 비밀번호 입력 받기
        String userid = inputuser.getUserid();
        String password = inputuser.getPassword();

        //로그인 서비스 로직
        Optional<UserDto> loginUser = mccService.login(userid, password);

        if(loginUser.isEmpty()){
            //로그인 실패 한다면
            log.info("로그인 실패");
            return fail(LOGIN_FAIL);
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginUser",loginUser);
        log.info("로그인 성공");
        return success(LOGIN_SUCCESS,loginUser);
    }

    @PostMapping("/logout")
    public Response logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session == null)
            return fail(LOGOUT_FAIL);

        session.invalidate();
        return success(LOGOUT_SUCCESS);
    }
}
