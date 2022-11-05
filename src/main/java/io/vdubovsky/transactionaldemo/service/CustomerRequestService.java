package io.vdubovsky.transactionaldemo.service;

import io.vdubovsky.transactionaldemo.dto.CustomerRequestStateChangeRecordDto;
import io.vdubovsky.transactionaldemo.model.CustomerRequest;


public interface CustomerRequestService {
    CustomerRequest saveOrUpdateRequest(CustomerRequestStateChangeRecordDto customerRequestDto);
}
