package com.example.demospringdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAsync
// CustomRepository 의 기본 postfix 는 Impl 이지만, 변경 하고 싶으면 설정 값을 바꿔준다.
@EnableJpaRepositories(repositoryImplementationPostfix = "Default")
public class DemospringdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemospringdataApplication.class, args);
    }

}
