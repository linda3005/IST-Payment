package templates.controller;


// import resources.templates.model.DTO.AuthRequestDTO;
// import resources.templates.model.DTO.AuthResponseDTO;
import templates.service.CustomerService;
import templates.util.JwtUtil;

import templates.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequestDTO authRequest) {
        // Check if the username is available
        if (customerService.findByUsername(authRequest.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        Customer customer = new Customer();
        customer.setUsername(authRequest.getUsername());
        customer.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        customerService.save(customer);
        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(new AuthResponseDTO(null, "Invalid credentials"));
        }

        final UserDetails userDetails = customerService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponseDTO(jwt, null));
    }
}
