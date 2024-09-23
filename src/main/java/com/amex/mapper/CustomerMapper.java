package com.amex.mapper;

import com.amex.dto.entity.Customer;
import com.amex.dto.request.CreateCustomerRequest;
import com.amex.dto.response.CustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CreateCustomerRequest customerRequest);
    CustomerResponse toCustomerResponse(Customer customer);
    List<CustomerResponse> tpCustomerResponseList(List<Customer> customerList);
}
