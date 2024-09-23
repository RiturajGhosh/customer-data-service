package testutil;

import com.amex.dto.request.CreateCustomerRequest;

public class TestUtils {

    public static CreateCustomerRequest mockCustomerRequest(){
        var customerRequest = new CreateCustomerRequest();
        customerRequest.setCustomerName("Rituraj Ghosh");
        customerRequest.setMobileNumber(324612L);
        customerRequest.setCustomerBillId(2L);
        return  customerRequest;
    }
}
