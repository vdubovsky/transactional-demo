package io.vdubovsky.transactionaldemo.service;

import io.vdubovsky.transactionaldemo.dto.CustomerRequestStateChangeRecordDto;
import io.vdubovsky.transactionaldemo.model.CustomerRequest;
import io.vdubovsky.transactionaldemo.repo.CustomerRequestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerRequestServiceImpl implements CustomerRequestService {

    private final CustomerRequestRepository customerRequestRepository;

    @Override
    public CustomerRequest saveOrUpdateRequest(CustomerRequestStateChangeRecordDto dto) {
        Optional<CustomerRequest> existingRequestOpt = customerRequestRepository.findByTransactionId(dto.getTransactionId());

        if (existingRequestOpt.isPresent()) {
            // Update existing request
            CustomerRequest existingRequest = existingRequestOpt.get();

            if (existingRequest.getRequestStartedAt().isAfter(dto.getRecordAt())){
                existingRequest.setRequestStartedAt(dto.getRecordAt());
            }

            if (existingRequest.getStateUpdatedAt().isBefore(dto.getRecordAt())){
                existingRequest.setState(dto.getState()).setStateUpdatedAt(dto.getRecordAt());
            }

            return customerRequestRepository.save(existingRequest);
        }

        // Save new request
        CustomerRequest newCustomerRequest = new CustomerRequest()
                .setId(UUID.randomUUID())
                .setRequestStartedAt(dto.getRecordAt())
                .setStateUpdatedAt(dto.getRecordAt())
                .setState(dto.getState())
                .setTransactionId(dto.getTransactionId());

        return customerRequestRepository.save(newCustomerRequest);
    }
}
