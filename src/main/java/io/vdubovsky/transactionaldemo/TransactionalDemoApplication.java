package io.vdubovsky.transactionaldemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@EnableAsync
@RequiredArgsConstructor
public class TransactionalDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TransactionalDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("##### Application started... #####");
    }
}
