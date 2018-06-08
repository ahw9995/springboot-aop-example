package com.example.aop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberLoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq", nullable = false)
    private Long seq;

    @Column(name = "member_seq", nullable = false)
    private Long memberSeq;

    @Column(name = "login_ip", nullable = false)
    private String loginIp;

    @Column(name = "login_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date loginDate;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;
}
