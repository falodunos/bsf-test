package com.bsf.resource;

import com.bsf.resource.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableJpaAuditing()
@SpringBootApplication()
@Slf4j
public class BsfTestApplication implements AsyncConfigurer { //implements CommandLineRunner {

	@Autowired
	private AppConfig appConfig;

	public static void main(String[] args) {
		SpringApplication.run(BsfTestApplication.class, args);
	}

//	@Override
	public void run(String... args) throws Exception {
		log.info("AuthServer Url :: {}", appConfig.getUserAuthorizationUri());
	}
}
