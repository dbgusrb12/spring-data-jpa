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


//    // Temporal 어노테이션으로 날짜의 타입을 지정 할 수 있다. (날짜만, 시간만, 날짜 시간 전부)
//    @Temporal(TemporalType.TIME)
//    private Date created = new Date();
//
//    private String yes;
//
//    // DB에 컬럼으로 추가 하고 싶지 않은 경우
//    @Transient
//    private String no;
//
//    @Embedded
//    // AttributeOverrides 로 해당 컬럼에 대한 name 을 변경 할 수 있다.
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
//    })
//    private Address address;
}
