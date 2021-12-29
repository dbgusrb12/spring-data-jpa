package com.example.demospringdata.custom.notice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Custom Repository 생성하는 방법: custom 하게 구현 할 interface 를 만들고,
 * 해당 interface 를 구현한 구현체를 만든 뒤, JpaRepository 와 custom 하게 만든 해당 Repository 를 상속받으면 된다.
 *
 * 기본 Repository 를 커스터마이징 한 경우, 해당 Repository 를 상속받으면 된다.
 */
public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeCustomRepository<Notice> {

}
