package com.example.demospringdata;

import com.example.demospringdata.custom.SimpleMyRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableAsync
// CustomRepository 의 기본 postfix 는 Impl 이지만, 변경 하고 싶으면 설정 값을 바꿔준다.
// 기본 Repository 를 커스터마이징 한 경우 repositoryBaseClass 를 명시해준다.
@EnableJpaRepositories(repositoryImplementationPostfix = "Default", repositoryBaseClass = SimpleMyRepository.class)
public class DemospringdataApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemospringdataApplication.class, args);
    }

}
