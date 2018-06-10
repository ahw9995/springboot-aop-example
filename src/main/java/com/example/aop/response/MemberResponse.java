package com.example.aop.response;

import com.example.aop.model.Member;
import com.example.aop.model.MemberLoginHistory;
import lombok.*;

@Data
@Getter
@Setter
public class MemberResponse {
    private boolean result = false;
    private String message;
    private Member member;
    private MemberLoginHistory memberLoginHistory;

    public MemberResponse() {}

    public MemberResponse(boolean result, String message, Member member, MemberLoginHistory memberLoginHistory) {
        this.result = result;
        this.message = message;
        this.member = member;
        this.memberLoginHistory = memberLoginHistory;
    }
}
