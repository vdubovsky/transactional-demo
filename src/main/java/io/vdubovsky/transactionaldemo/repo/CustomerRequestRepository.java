package io.vdubovsky.transactionaldemo.repo;

import io.vdubovsky.transactionaldemo.model.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, UUID> {
    Optional<CustomerRequest> findByTransactionId(UUID transactionId);
}
