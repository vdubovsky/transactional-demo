package io.vdubovsky.transactionaldemo.service;

import io.vdubovsky.transactionaldemo.dto.CustomerRequestStateChangeRecordDto;
import io.vdubovsky.transactionaldemo.model.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomerRequestServiceWrapper implements CustomerRequestService {

    private final CustomerRequestService customerRequestService;

    public synchronized CustomerRequest saveOrUpdateRequest(CustomerRequestStateChangeRecordDto dto) {
        return customerRequestService.saveOrUpdateRequest(dto);
    }
}
