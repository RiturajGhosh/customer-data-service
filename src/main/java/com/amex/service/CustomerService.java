package com.amex.service;

import com.amex.dto.entity.Billing;
import com.amex.dto.entity.Customer;
import com.amex.dto.request.CreateCustomerRequest;
import com.amex.dto.response.CustomerResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public interface CustomerService {

    Customer create(Customer customerRequest);
    List<Customer> getCustomersByBillIds(List<Long> billIds);
    Mono<List<Customer>> getCustomerByBillStatus(Billing.Status status);
}
