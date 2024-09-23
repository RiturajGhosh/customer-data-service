package com.amex.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private Long customerId;
    private String customerName;
    private Integer mobileNumber;
    private Long customerBillId;
}
