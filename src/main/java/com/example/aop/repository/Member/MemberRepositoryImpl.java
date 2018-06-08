package com.example.aop.repository.Member;

import com.example.aop.model.Member;
import com.example.aop.model.MemberLoginHistory;
import com.example.aop.model.QMember;
import com.example.aop.model.QMemberLoginHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public MemberRepositoryImpl() {
        super(Member.class);
    }

    @Override
    public Member findMemberByUserId(String userId) {
        try {
            JPAQueryFactory f = new JPAQueryFactory(this.entityManager);
            QMember qMember = QMember.member;
            return f.selectFrom(qMember).where(qMember.userId.eq(userId)).fetchOne();
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }

    @Override
    public Member findMemberBySeq(Long seq) {
        try {
            JPAQueryFactory f = new JPAQueryFactory(this.entityManager);
            QMember qMember = QMember.member;
            return f.selectFrom(qMember).where(qMember.seq.eq(seq)).fetchOne();
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }

    @Override
    public Member addMember(Member member) {
        try {
            this.entityManager.persist(member);
            return member;
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }

    @Override
    public Member updateMember(Member member) {
        try {
            Member origin = this.findMemberBySeq(member.getSeq());
            origin.setAddress(member.getAddress());
            origin.setModifiedDate(new Date());
            return origin;
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }

    @Override
    public void deleteMember(Member member) {
        try {
            Member origin = this.findMemberBySeq(member.getSeq());
            List<MemberLoginHistory> memberLoginHistories = origin.getMemberLoginHistories();
            if (!Objects.isNull(memberLoginHistories) && !memberLoginHistories.isEmpty()) {
                member.setMemberLoginHistories(null);
            }
            this.entityManager.remove(member);
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
    }

    @Override
    public List<MemberLoginHistory> getMemberLoginHistory(Long seq) {
        try {
            JPAQueryFactory f = new JPAQueryFactory(this.entityManager);
            QMember qMember = QMember.member;
            Member member = f.selectFrom(qMember).where(qMember.seq.eq(seq)).fetchOne();
            return member.getMemberLoginHistories();
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }

    @Override
    public List<MemberLoginHistory> addMemberLoginHistory(MemberLoginHistory memberLoginHistory) {
        try {
            this.entityManager.persist(memberLoginHistory);
            return this.findMemberBySeq(memberLoginHistory.getMemberSeq()).getMemberLoginHistories();
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
        return null;
    }

    @Override
    public void deleteMemberLoginHistory(Long memberLoginHistorySeq) {
        try {
            JPAQueryFactory f = new JPAQueryFactory(this.entityManager);
            QMemberLoginHistory qMemberLoginHistory = QMemberLoginHistory.memberLoginHistory;
            MemberLoginHistory memberLoginHistory = f.selectFrom(qMemberLoginHistory).where(qMemberLoginHistory.seq.eq(memberLoginHistorySeq)).fetchOne();
            this.entityManager.remove(memberLoginHistory);
        } catch (PersistenceException e) {
            ExceptionUtils.getStackTrace(e);
        }
    }
}
