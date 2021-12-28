package com.example.demospringdata.custom.notice;

import java.util.List;

/**
 * Custom Repository 를 생성
 */
public interface NoticeCustomRepository<T> {

    /**
     * 원하는 메서드를 선언한다.
     * @return
     */
    List<Notice> findMyNotice();

    /**
     * 기본적으로 제공하는 메서드를 재정의 할 수 있다. (custom repository 가 우선 순위 적용)
     * @param entity
     */
    void delete(T entity);

}
