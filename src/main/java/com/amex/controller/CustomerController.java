package com.amex.controller;

import com.amex.dto.entity.Billing;
import com.amex.dto.request.CreateCustomerRequest;
import com.amex.dto.response.CustomerResponse;
import com.amex.mapper.CustomerMapper;
import com.amex.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper mapper;

    @Autowired
    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.mapper = customerMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse createCustomer(@RequestBody CreateCustomerRequest customerRequest) {
        log.info("path: {}, request: {}, method: {}", "/v1/customer", customerRequest, HttpMethod.POST);
        return mapper.toCustomerResponse(customerService
                .create(mapper.toCustomer(customerRequest)));
    }

    @GetMapping("/{billStatus}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<List<CustomerResponse>> getCustomerByBillStatus(@PathVariable String billStatus) throws Exception {
        log.info("path: {}, request: {}, method: {}", "/v1/customer", billStatus, HttpMethod.GET);
        return customerService.getCustomerByBillStatus(Billing.Status.fromName(billStatus))
                .map(mapper::tpCustomerResponseList);
    }
}
