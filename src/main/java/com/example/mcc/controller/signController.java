package com.example.mcc.controller;

import com.example.mcc.Dto.memberDto;
import com.example.mcc.response.memberResponse;
import com.example.mcc.service.MccSignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.mcc.response.Message.SIGN_FAIL;
import static com.example.mcc.response.Message.SIGN_SUCCESS;
import static com.example.mcc.response.memberResponse.fail;
import static com.example.mcc.response.memberResponse.success;

@RestController
@RequestMapping("/mcc")
@Slf4j
public class signController {


    private final MccSignService mccSignService;

    public signController(MccSignService mccSignService) {
        this.mccSignService = mccSignService;
    }

    @PostMapping("/sign")
    public memberResponse sign(@RequestBody  memberDto member){
        log.info("sign 컨트롤러 호출");
        String number = member.getMemberNumber();
        String password = member.getMemberPassword();
        log.info("요청받은 데이터 = {} , {}", member.getMemberNumber(), member.getMemberPassword());

        String result = mccSignService.sign(number, password);

        if(result.equals(SIGN_FAIL))
            return fail(SIGN_FAIL);

        return success(SIGN_SUCCESS);
    }

}
