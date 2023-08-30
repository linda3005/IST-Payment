package templates.service;

// import com.tujuhsembilan.payment.resources.templates.model.Customer;
import com.tujuhsembilan.payment.resources.templates.repository.CustomerRepository;

import templates.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    // Other methods as needed
}
