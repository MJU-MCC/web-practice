package com.example.mcc.controller;

import com.example.mcc.Dto.memberDto;
import com.example.mcc.response.SignResponse;
import com.example.mcc.service.MccSignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.mcc.response.Message.SIGN_FAIL;
import static com.example.mcc.response.Message.SIGN_SUCCESS;

@Api(value = "MCC 회원가입 API")
@RestController
@RequestMapping("/mcc")
@Slf4j
public class signController {

    private final MccSignService mccSignService;
    private final SignResponse response;

    public signController(MccSignService mccSignService, SignResponse response) {
        this.mccSignService = mccSignService;
        this.response = response;
    }


    // 회원가입
    @ApiOperation(value = "회원가입 Api" ,notes = "학번 , 휴대전화 뒷자리 , role")
    @PostMapping("/sign")
    public ResponseEntity<SignResponse> sign(@RequestBody memberDto member){

        log.info("sign 컨트롤러 호출");

        String number = member.getMemberNumber();
        String password = member.getMemberPassword();
        String role = member.getRole();

        log.info("회원 가입을 시도하는 학번 , 비밀번호 = {} , {}", member.getMemberNumber(), member.getMemberPassword());

        if(!mccSignService.isDuplicateAndSignUp(number, password, role)){
            response.setMessage(SIGN_SUCCESS);
            return ResponseEntity.ok().body(response);
        }
        response.setMessage(SIGN_FAIL);
        return ResponseEntity.badRequest().body(response);
    }

}
