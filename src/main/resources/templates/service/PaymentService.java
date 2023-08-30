package templates.service;



import com.tujuhsembilan.payment.resources.templates.model.Customer;
import com.tujuhsembilan.payment.resources.templates.model.DTO.PaymentRequestDTO;
import com.tujuhsembilan.payment.resources.templates.model.DTO.PaymentResponseDTO;
import com.tujuhsembilan.payment.resources.templates.model.PaymentTransaction;
import com.tujuhsembilan.payment.resources.templates.CustomerRepository;
import com.tujuhsembilan.payment.resources.templates.PaymentTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;

    public PaymentResponseDTO processPayment(PaymentRequestDTO paymentRequest) {
        // Authenticate and get authenticated user (use Spring Security context)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) authentication.getPrincipal(); /* Get authenticated user */;

        // Create PaymentTransaction
        PaymentTransaction paymentTransaction = new PaymentTransaction();
        paymentTransaction.setCustomer(customer);
        paymentTransaction.setAmount(paymentRequest.getAmount());
        paymentTransaction.setTimestamp(LocalDateTime.now());

        paymentTransactionRepository.save(paymentTransaction);

        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setStatus("Payment successful");
        // Other response data

        return response;
    }
}
