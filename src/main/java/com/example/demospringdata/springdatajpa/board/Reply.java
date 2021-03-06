package com.example.demospringdata.springdatajpa.board;

import javax.persistence.*;

// 연관 관계를 매핑해 fetch 전략을 바꿀 수 있다.
@NamedEntityGraph(name = "Reply.board",
        attributeNodes = @NamedAttributeNode("board"))
@Entity
public class Reply {

    @Id
    @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private int up;

    private int down;

    private boolean best;

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public boolean isBest() {
        return best;
    }

    public void setBest(boolean best) {
        this.best = best;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
