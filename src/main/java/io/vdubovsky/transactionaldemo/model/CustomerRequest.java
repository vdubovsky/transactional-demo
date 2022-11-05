package io.vdubovsky.transactionaldemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "customer_request")
@Entity
@Data
@Accessors(chain = true)
public class CustomerRequest {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "transaction_id")
    private UUID transactionId;

    @Column(name = "state")
    private String state;

    @Column(name = "request_started_at")
    private LocalDateTime requestStartedAt;

    @Column(name = "state_updated_at")
    private LocalDateTime stateUpdatedAt;
}
