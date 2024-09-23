package com.amex.dto.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private Integer mobileNumber;
    private Long customerBillId;

   /* @OneToOne
    @JoinColumn(name = "customerBillId", referencedColumnName = "billId", insertable = false, updatable = false)
    private Billing billing;*/
}
