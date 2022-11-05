package io.vdubovsky.transactionaldemo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Accessors(chain = true)
@Data
public class CustomerRequestStateChangeRecordDto {

    private UUID transactionId;

    private String state;

    private LocalDateTime recordAt;
}
