package com.amex.repository;

import com.amex.dto.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findCustomerByCustomerBillIdIn(List<Long> customerBillIds);
}
