package com.amex.dto.entity;

import com.amex.exception.CustomerServiceException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billing {

    private Long billId;
    private Long amount;
    private Status status;


    @Getter
    public static enum Status {
        PAID("paid", 1), DUE("due", 0);

        private final String name;
        private final int value;

        Status(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public static Status fromName(String name) throws Exception {
            return Arrays.stream(Status.values())
                    .filter(statusEnum -> StringUtils.equalsIgnoreCase(statusEnum.getName(), name))
                    .findFirst().orElseThrow(() -> new CustomerServiceException("unsupported value##101"));
        }

    }
}
