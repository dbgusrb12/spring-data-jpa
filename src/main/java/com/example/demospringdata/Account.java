package com.example.demospringdata;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {


    @Id // primary key 설정 어노테이션
    @GeneratedValue // seq 사용
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // 기본적으로 모든 멤버 변수에는 column 어노테이션이 추가된다.
    private String password;

    @Temporal(TemporalType.TIME)
    private Date created = new Date();

    private String yes;

//    DB에 컬럼으로 추가 하고 싶지 않은 경우
    @Transient
    private String no;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
