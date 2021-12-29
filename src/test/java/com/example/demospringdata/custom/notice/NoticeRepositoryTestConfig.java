package com.example.demospringdata.custom.notice;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Custom Event Listener 를 bean 으로 등록하는 부분 (단위 테스트를 위해 Bean 설정 파일을 따로 만듬)
 */
@Configuration
public class NoticeRepositoryTestConfig {

    @Bean
    public NoticeListener noticeListener() {
        return new NoticeListener();
    }

    /**
     * Event Listener 를 따로 구현하기 싫으면,
     * ApplicationListener 를 직접 구현해 bean 으로 만드는 방법 도 있다.
     *
     * @return
     */
    @Bean
    public ApplicationListener<NoticePublishedEvent> customNoticeListener() {
//        return new ApplicationListener<NoticePublishedEvent>() {
//            @Override
//            public void onApplicationEvent(NoticePublishedEvent event) {
//                System.out.println("======================");
//                System.out.println(event.getNotice().getTitle() + " is published");
//                System.out.println("======================");
//            }
//        };
        // 위의 코드를 람다를 이용해 처리
        return event -> {
            System.out.println("======================");
            System.out.println(event.getNotice().getTitle() + " is published");
            System.out.println("======================");
        };
    }
}
