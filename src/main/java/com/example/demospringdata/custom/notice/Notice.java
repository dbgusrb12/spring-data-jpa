package com.example.demospringdata.custom.notice;

import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.Date;

/**
 * Spring Data common 이 지원하는 Domain Event Publisher 를 상속 받으면,
 * 따로 설정 할 필요 없이 이벤트를 생성하고, 발생시킬 수 있다.
 */
@Entity
public class Notice extends AbstractAggregateRoot<Notice> {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Notice publish() {
        this.registerEvent(new NoticePublishedEvent(this));
        return this;
    }
}
