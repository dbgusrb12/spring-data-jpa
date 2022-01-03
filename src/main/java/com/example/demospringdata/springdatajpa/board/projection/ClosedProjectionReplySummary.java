package com.example.demospringdata.springdatajpa.board.projection;

/**
 * Closed Projection 과 default 메서드를 이용하여 쿼리 성능 최적화 가능하다.
 */
public interface ClosedProjectionReplySummary {

    String getComment();

    int getUp();

    int getDown();

    // java 8 부터 지원되는 default method 를 사용하여 closed projection 처럼 해당 쿼리만 가져오는 projection 을 만들 수 있다.
    default String getVotes() {
        return getUp() + " " + getDown();
    }
}
