package com.example.demospringdata.springdatajpa.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void getReply() {
        replyRepository.getReplyById(1l);

        System.out.println("=========================");

        replyRepository.findById(1l);

        System.out.println("=========================");

        replyRepository.getReplyByIdOrderByIdAsc(1l);
    }
}