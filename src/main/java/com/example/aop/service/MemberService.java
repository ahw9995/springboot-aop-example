package com.example.aop.service;

import com.example.aop.model.Member;
import com.example.aop.model.MemberLoginHistory;
import com.example.aop.repository.Member.MemberRepository;
import com.example.aop.response.MemberResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member findMemeberByUserId(String userId) {
        return memberRepository.findMemberByUserId(userId);
    }

    public Member findMemberBySeq(Long seq) {
        return memberRepository.findMemberBySeq(seq);
    }

    public Member addMember(Member member) {
        return memberRepository.addMember(member);
    }

    public Member updateMember(Member member) {
        return memberRepository.updateMember(member);
    }

    public void removeMember(Member member) {
        memberRepository.deleteMember(member);
    }

    public List<MemberLoginHistory> getMemberLoginHistory(Long seq) {
        return memberRepository.getMemberLoginHistory(seq);
    }

    public List<MemberLoginHistory> addMemberLoginHistory(MemberLoginHistory memberLoginHistory) {
        return memberRepository.addMemberLoginHistory(memberLoginHistory);
    }

    public void removeMemberLoginHistory(Long memberLoginHistorySeq) {
        memberRepository.deleteMemberLoginHistory(memberLoginHistorySeq);
    }
}
