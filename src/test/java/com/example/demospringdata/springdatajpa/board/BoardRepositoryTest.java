package com.example.demospringdata.springdatajpa.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void save() {
        // save 메서드는 해당 객체가 존재하면 merge 메서드, 존재하지 않으면 persist 메서드를 호출한다.
        // persist 메서드는 파라미터로 전달 된 엔티티를 persist 상태로 만든 뒤 return
        // merge 메서드는 파라미터로 전달 된 엔티티의 복사본을 만들어, 해당 객체를 persist 상태로 만든 뒤 return
        // 따라서, save 메서드를 호출 한 뒤 다시 수정 할 일이 생기면, return 받은 객체를 사용해 수정하는 것을 권장한다.

        Board board = new Board();
        board.setTitle("spring-data-jpa");

        // board 의 id 값이 없으므로 persist() 호출 (insert 쿼리 발생)
        Board saveBoard = boardRepository.save(board);

        // persist 메서드를 사용하기 때문에, 파라미터로 전달된 board 와 return 된 saveBoard 는 같은 객체이다.
        assertThat(entityManager.contains(board)).isTrue();
        assertThat(entityManager.contains(saveBoard)).isTrue();
        assertThat(board == saveBoard).isTrue();

        Board boardUpdate = new Board();
        boardUpdate.setId(saveBoard.getId());
        boardUpdate.setTitle("hibernate");

        // boardUpdate 의 id 값이 있으므로 merge() 호출 (해당 엔티티가 존재하면 update, 존재하지 않으면 insert 쿼리 발생)
        Board updatedBoard = boardRepository.save(boardUpdate);

        // merge 메서드를 사용하기 때문에, 파라미터로 전달된 boardUpdate 와 return 된 updatedBoard 는 다른 객체이다.
        assertThat(entityManager.contains(boardUpdate)).isFalse();
        assertThat(entityManager.contains(updatedBoard)).isTrue();
        assertThat(boardUpdate == updatedBoard).isFalse();


        // findAll 메서드를 호출하는 이유? => 해당 메서드를 호출하지 않으면 insert 쿼리가 발생하지 않는다. 지금 시점에서 이 이유를 모른다면 다시 공부 해야 됨.
        List<Board> all = boardRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}