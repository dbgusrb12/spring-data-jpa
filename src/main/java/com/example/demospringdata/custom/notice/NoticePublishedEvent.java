package com.example.demospringdata.custom.notice;

import org.springframework.context.ApplicationEvent;

/**
 * Notice publish event 를 생성하기 위해 ApplicationEvent 를 상속받은 클래스를 선언 한다.
 */
public class NoticePublishedEvent extends ApplicationEvent {

    private final Notice notice;

    public NoticePublishedEvent(Object source) {
        super(source);
        this.notice = (Notice) source;
    }

    public Notice getNotice() {
        return notice;
    }
}
