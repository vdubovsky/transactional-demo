package io.vdubovsky.transactionaldemo;

import io.vdubovsky.transactionaldemo.dto.CustomerRequestStateChangeRecordDto;
import io.vdubovsky.transactionaldemo.service.CustomerRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class TransactionalDemoApplication implements CommandLineRunner {

    private final CustomerRequestService customerRequestService;

    private final Executor executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        SpringApplication.run(TransactionalDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("##### Application started... #####");
        while (true) {
            Thread.sleep(100);

            UUID txId = UUID.randomUUID();
            LocalDateTime now = LocalDateTime.now();

            CustomerRequestStateChangeRecordDto started = new CustomerRequestStateChangeRecordDto()
                    .setTransactionId(txId)
                    .setState("STARTED")
                    .setRecordAt(now.minus(20, ChronoUnit.MILLIS));

            CustomerRequestStateChangeRecordDto inProgress = new CustomerRequestStateChangeRecordDto()
                    .setTransactionId(txId)
                    .setState("IN_PROGRESS")
                    .setRecordAt(now.minus(10, ChronoUnit.MILLIS));

            CustomerRequestStateChangeRecordDto completed = new CustomerRequestStateChangeRecordDto()
                    .setTransactionId(txId)
                    .setState("COMPLETED")
                    .setRecordAt(now);

            executor.execute(() -> customerRequestService.saveOrUpdateRequest(started));
            executor.execute(() -> customerRequestService.saveOrUpdateRequest(inProgress));
            executor.execute(() -> customerRequestService.saveOrUpdateRequest(completed));
        }
    }
}
