package com.example.aop.repository.Member;

import com.example.aop.model.Member;
import com.example.aop.model.MemberLoginHistory;

import java.util.List;

public interface MemberRepository {
    Member findMemberByUserId(String userId);
    Member findMemberBySeq(Long seq);
    Member addMember(Member member);
    Member updateMember(Member member);
    void deleteMember(Member member);
    List<MemberLoginHistory> getMemberLoginHistory(Long seq);
    List<MemberLoginHistory> addMemberLoginHistory(MemberLoginHistory memberLoginHistory);
    void deleteMemberLoginHistory(Long memberLoginHistorySeq);
}
