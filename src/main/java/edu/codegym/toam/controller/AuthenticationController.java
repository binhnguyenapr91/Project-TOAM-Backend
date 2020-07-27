package edu.codegym.toam.controller;

import edu.codegym.toam.model.Account;
import edu.codegym.toam.model.ERole;
import edu.codegym.toam.model.Role;
import edu.codegym.toam.payload.request.*;
import edu.codegym.toam.payload.response.*;
import edu.codegym.toam.repository.AccountRepository;
import edu.codegym.toam.repository.RoleRepository;
import edu.codegym.toam.security.jwt.JwtUtils;
import edu.codegym.toam.service.account.AccountDetailService;
import edu.codegym.toam.service.account.CustomAccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AccountDetailService accountDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect username or password", e);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = this.jwtUtil.generateJwtToken(authentication);
        CustomAccountDetail accountDetail = (CustomAccountDetail) authentication.getPrincipal();
        List<String> roles = accountDetail.getAuthorities().stream()
                .map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                accountDetail.getId(),
                accountDetail.getUsername(),
                accountDetail.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@Valid @RequestBody SignupRequest signupRequest){
        if (accountRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (accountRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Account account = new Account(signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_RENTER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "host":
                        Role hostRole = roleRepository.findByName(ERole.ROLE_HOST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(hostRole);
                        break;
                    default:
                        Role renterRole = roleRepository.findByName(ERole.ROLE_RENTER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(renterRole);
                }
            });
        }
        account.setRoles(roles);
        accountRepository.save(account);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
