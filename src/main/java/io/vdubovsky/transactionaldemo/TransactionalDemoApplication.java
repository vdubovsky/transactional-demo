package io.vdubovsky.transactionaldemo;

import io.vdubovsky.transactionaldemo.service.CustomerRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class TransactionalDemoApplication implements CommandLineRunner {

    private final CustomerRequestService customerRequestService;

    public static void main(String[] args) {
        SpringApplication.run(TransactionalDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("##### Application started... #####");
    }
}
