package com.example.demospringdata.springdatajpa.board;

import com.example.demospringdata.springdatajpa.board.projection.ClosedProjectionReplySummary;
import com.example.demospringdata.springdatajpa.board.projection.OpenProjectionReplySummary;
import com.example.demospringdata.springdatajpa.board.projection.ReplyOnly;
import com.example.demospringdata.springdatajpa.board.projection.ReplySummary;
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

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void getReply() {
        replyRepository.getReplyById(1l);

        System.out.println("=========================");

        replyRepository.findById(1l);

        System.out.println("=========================");

        replyRepository.getReplyByIdOrderByIdAsc(1l);
    }

    @Test
    public void getReplySummary() {
        Board board = new Board();
        board.setTitle("jpa");
        Board savedBoard = boardRepository.save(board);
        Reply reply = new Reply();
        reply.setBoard(savedBoard);
        reply.setComment("Spring data jpa projection");
        reply.setUp(10);
        reply.setDown(1);
        replyRepository.save(reply);

        // Closed Projection => 쿼리 성능 최적화 가능
        replyRepository.findByBoard_Id(1l, ClosedProjectionReplySummary.class).forEach(r -> {
            System.out.println("===================");
            System.out.println(r.getVotes());
        });

        // Open Projection => 쿼리 성능 최적화는 하지 못하지만, 원하는 값 추출 가능
        replyRepository.findByBoard_Id(1l, OpenProjectionReplySummary.class).forEach(r -> {
            System.out.println("====================");
            System.out.println(r.getVotes());
        });

        // Class 기반 Projection => 쿼리 성능 최적화 가능 (interface 로 사용하는게 코드가 더 깔끔하다. 비추천.)
        replyRepository.findByBoard_Id(1l, ReplySummary.class).forEach(r -> {
            System.out.println("====================");
            System.out.println(r.getVotes());
        });

        replyRepository.findByBoard_Id(1l, ReplyOnly.class).forEach(r -> {
            System.out.println("===================");
            System.out.println(r.getComment());
        });
    }
}