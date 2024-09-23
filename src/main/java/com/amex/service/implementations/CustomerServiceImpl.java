package com.amex.service.implementations;

import com.amex.dto.entity.Billing;
import com.amex.dto.entity.Customer;
import com.amex.http.APIClient;
import com.amex.repository.CustomerRepository;
import com.amex.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final APIClient apiClient;
    private final String baseUri;
    private final String subUri;


    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               APIClient apiClient,
                               @Value("${uri.amex.base}") String baseUri,
                               @Value("${uri.amex.billing}") String subUri) {
        this.customerRepository = customerRepository;
        this.apiClient = apiClient;
        this.baseUri = baseUri;
        this.subUri = subUri;
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomersByBillIds(List<Long> billIds) {
        return customerRepository.findCustomerByCustomerBillIdIn(billIds);
    }

    @Override
    public Mono<List<Customer>> getCustomerByBillStatus(Billing.Status status) {
                return apiClient.callApiFluxResponse(baseUri + subUri, status, HttpMethod.GET, Billing.class, null)
                .map(Billing::getBillId).collectList()
                .map(customerRepository::findCustomerByCustomerBillIdIn);
    }
}
