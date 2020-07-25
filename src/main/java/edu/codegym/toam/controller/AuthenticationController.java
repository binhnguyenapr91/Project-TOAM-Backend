package edu.codegym.toam.controller;

import edu.codegym.toam.model.jwt.AuthenticationRequest;
import edu.codegym.toam.model.jwt.AuthenticationResponse;
import edu.codegym.toam.service.account.AccountDetailService;
import edu.codegym.toam.service.account.CustomAccountDetail;
import edu.codegym.toam.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AccountDetailService accountDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authReq) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authReq.getUsername(),
                            authReq.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Incorrect username or password", e);
        }

        final CustomAccountDetail customAccountDetail = accountDetailService.loadUserByUsername(authReq.getUsername());
        final String jwt = this.jwtUtil.generateToken(customAccountDetail,customAccountDetail.getAuthorities());

        return new AuthenticationResponse(jwt);
    }
}
