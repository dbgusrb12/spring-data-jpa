package com.example.demospringdata.querydsl.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * Querydsl 을 연동하기 위해 QuerydslPredicateExecutor 를 상속받는다.
 */
public interface MemberRepository extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member> {

}
