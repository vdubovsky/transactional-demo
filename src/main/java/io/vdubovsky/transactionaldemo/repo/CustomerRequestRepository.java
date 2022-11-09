package io.vdubovsky.transactionaldemo.repo;

import io.vdubovsky.transactionaldemo.model.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, UUID> {
    //@Lock(LockModeType.PESSIMISTIC_WRITE)
    //@Lock(LockModeType.PESSIMISTIC_READ)
    Optional<CustomerRequest> findByTransactionId(UUID transactionId);
}
