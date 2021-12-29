package com.example.demospringdata.springdatajpa.board;

import javax.persistence.*;
import java.util.Date;

@Entity
// JPQL 방식
@NamedQuery(name = "Board.findByTitleWithNamedQuery", query = "SELECT b FROM Board AS b WHERE b.title = ?1")
// Native Query 방식
@NamedNativeQuery(name = "Board.findByTitleWithNamedNativeQuery", query = "SELECT * FROM BOARD WHERE title = ?1", resultClass = Board.class)
public class Board {

    @Id @GeneratedValue
    private Long id;

    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
