package io.vdubovsky.transactionaldemo;

import io.vdubovsky.transactionaldemo.dto.CustomerRequestStateChangeRecordDto;
import io.vdubovsky.transactionaldemo.service.CustomerRequestServiceWrapper;
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

    private final CustomerRequestServiceWrapper customerRequestServiceWrapper;

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

            executor.execute(() -> customerRequestServiceWrapper.saveOrUpdateRequest(started));
            executor.execute(() -> customerRequestServiceWrapper.saveOrUpdateRequest(inProgress));
            executor.execute(() -> customerRequestServiceWrapper.saveOrUpdateRequest(completed));
        }
    }
}
