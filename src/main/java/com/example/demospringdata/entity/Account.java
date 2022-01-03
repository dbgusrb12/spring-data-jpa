package com.example.demospringdata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id // primary key 설정 어노테이션
    @GeneratedValue // seq 사용
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // 기본적으로 모든 멤버 변수에는 column 어노테이션이 추가된다.
    private String password;

    // Collection 일 때는 OneToMany, 단일 객체일 때는 ManyToOne
    // 양방향 관계를 정의 할 때는 OneToMany 에 mappedBy 로 주인의 관계를 설정해야 한다.
    // mappedBy 를 정의하지 않으면 단방향 관계가 2개인 것
    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    private int likeCount;

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

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

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    public void addStudy(Study study) {
        this.getStudies().add(study);
        study.setOwner(this);
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study);
        study.setOwner(null);
    }

    // Temporal 어노테이션으로 날짜의 타입을 지정 할 수 있다. (날짜만, 시간만, 날짜 시간 전부)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date created = new Date();

    // JPA 2.2 이상부터 java8에 DateTime 클래스 지원 (LocalData, LocalTime, LocalDateTime)
    private LocalDateTime created;

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
