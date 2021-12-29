package com.example.demospringdata.custom.notice;

import org.springframework.context.ApplicationListener;

/**
 * custom event 를 listen 할 listener 객체를 생성 한다. (Bean 으로 등록을 해야된다.
 * 이 때 ApplicationListener 를 구현하고, custom event 객체를 타입으로 넣어준다.
 * 아니면, ApplicationListener 를 구현하지 않고, 해당 이벤트 리스너 메서드에 @EventListener 를 붙여줘도 된다.
 */
public class NoticeListener implements ApplicationListener<NoticePublishedEvent> {

    // ApplicationListener 를 구현 받기 싫다면 어노테이션으로 구현 가능하다.
//    @EventListener
    @Override
    public void onApplicationEvent(NoticePublishedEvent event) {
        System.out.println("======================");
        System.out.println(event.getNotice().getTitle() + " is published");
        System.out.println("======================");
    }
}
