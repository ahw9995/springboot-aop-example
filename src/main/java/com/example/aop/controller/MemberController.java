package com.example.aop.controller;

import com.example.aop.model.Member;
import com.example.aop.response.MemberResponse;
import com.example.aop.service.MemberService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/member/{seq}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MemberResponse> findMember(@PathVariable("seq") Long seq) {
        HttpStatus httpStatus = HttpStatus.ACCEPTED;
        MemberResponse memberResponse = new MemberResponse();
        try {
            if (Objects.isNull(seq) || seq == 0) {
                httpStatus = HttpStatus.CONFLICT;
                memberResponse = new MemberResponse(false, "There was a missing or invalid parameter. [seq: " + seq + "]", null, null);
            } else {
                Member member = memberService.findMemberBySeq(seq);
                httpStatus = HttpStatus.OK;
                memberResponse = new MemberResponse(true, "Success find member.", member, null);
                return new ResponseEntity<>(memberResponse, httpStatus);
            }

        } catch (Exception e) {
            ExceptionUtils.getStackTrace(e);
            memberResponse = new MemberResponse(false, "An error occurred while find user.[error: " + e.getMessage() + "]", null, null);
            httpStatus = HttpStatus.CONFLICT;
        }

        return new ResponseEntity<>(memberResponse, httpStatus);
    }
}
