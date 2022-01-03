package com.example.demospringdata.springdatajpa.board.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Open Projection 을 사용하여 성능은 그대로지만,
 * query 에서 alias 를 사용하여 가져오는 것 처럼 원하는 값을 가져 올 수 있다.
 */
public interface OpenProjectionReplySummary {

    String getComment();

    int getUp();

    int getDown();

    @Value("#{target.up + ' ' + target.down}")
    String getVotes();
}
