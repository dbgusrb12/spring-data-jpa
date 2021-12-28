package com.example.demospringdata.custom.notice;

import com.example.demospringdata.custom.MyRepository;

/**
 * 기본 Repository 를 커스터마이징 한 경우, 해당 Repository 를 상속받으면 된다.
 */
public interface DefaultNoticeRepository extends MyRepository<Notice, Long> {
}
