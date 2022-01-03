package com.example.demospringdata.springdatajpa.board.projection;

/**
 * Projection 을 Class 기반으로 만들어, 쿼리 성능 최적화를 할 수 있다.
 */
public class ReplySummary {
    private String comment;

    private int up;

    private int down;

    public ReplySummary(String comment, int up, int down) {
        this.comment = comment;
        this.up = up;
        this.down = down;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

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

    public String getVotes() {
        return this.up + " " + this.down;
    }
}
