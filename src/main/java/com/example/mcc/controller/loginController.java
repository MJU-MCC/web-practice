package com.example.mcc.controller;

import com.example.mcc.Dto.memberDto;
import com.example.mcc.entity.member;
import com.example.mcc.response.memberResponse;
import com.example.mcc.service.MccLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.example.mcc.response.Message.*;
import static com.example.mcc.response.Message.LOGOUT_SUCCESS;
import static com.example.mcc.response.memberResponse.fail;
import static com.example.mcc.response.memberResponse.success;

@RestController
@RequestMapping("/mcc")
@Slf4j
public class loginController {


    private final MccLoginService mccLoginService;

    public loginController(MccLoginService mccLoginService) {
        this.mccLoginService = mccLoginService;
    }

    @PostMapping("/login")
    public memberResponse login(
            @RequestBody memberDto inputuser
            , HttpServletRequest request){
        //로그인 아이디 비밀번호 입력 받기
        String memberNumber = inputuser.getMemberNumber();
        String memberPassword = inputuser.getMemberPassword();

        //로그인 서비스 로직
        member loginMember = mccLoginService.login(memberNumber, memberPassword);

        if(loginMember == null){
            //로그인 실패 한다면
            log.info("로그인 실패");
            return fail(LOGIN_FAIL);
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);
        log.info("로그인 성공");
        return success(LOGIN_SUCCESS,loginMember);
    }

    @PostMapping("/logout")
    public memberResponse logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session == null)
            return fail(LOGOUT_FAIL);

        session.invalidate();
        return success(LOGOUT_SUCCESS);
    }

}
