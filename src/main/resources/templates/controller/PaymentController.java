package templates.controller;

import com.tujuhsembilan.payment.resources.templates.service.PaymentService;
import com.tujuhsembilan.payment.resources.templates.model.dto.PaymentRequestDTO;
import com.tujuhsembilan.payment.resources.templates.repository.PaymentTransactionRepository;
// import templates.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    PaymentTransactionRepository repo;

    @PostMapping("/process")
    public PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        return paymentService.processPayment(paymentRequest);
    }

    @GetMapping("/coba")
    public ResponseEntity<?> processPayment() {
        return repo.findAll();
    }
}
