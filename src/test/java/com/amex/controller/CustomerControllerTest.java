package com.amex.controller;

import com.amex.mapper.CustomerMapper;
import com.amex.service.CustomerService;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static testutil.TestUtils.mockCustomerRequest;

@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    @Spy
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    @InjectMocks
    private CustomerController customerController;

    @Test
    void createCustomer() {
        var request = mockCustomerRequest();
        Mockito.doReturn(customerMapper.toCustomer(request))
                .when(customerService).create(any());
        var result = customerController.createCustomer(request);
        Assertions.assertEquals("Rituraj Ghosh", result.getCustomerName());
    }

    @Test
    void getCustomerByBillStatus() throws Exception {
        Mockito.doReturn(Mono.just(new ArrayList<>())).when(customerService).getCustomerByBillStatus(any());
        var result = customerController.getCustomerByBillStatus("due");
        Assertions.assertEquals(0, Objects.requireNonNull(result.block()).size());
    }
}