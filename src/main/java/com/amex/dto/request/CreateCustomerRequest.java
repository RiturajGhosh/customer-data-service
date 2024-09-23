package com.amex.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    private String customerName;
    private Long mobileNumber;
    private Long customerBillId;
}
