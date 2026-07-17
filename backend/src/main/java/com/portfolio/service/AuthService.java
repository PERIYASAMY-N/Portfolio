package com.portfolio.service;

import com.portfolio.dto.LoginRequest;
import com.portfolio.dto.JwtResponse;
import com.portfolio.entity.Admin;
import com.portfolio.repository.AdminRepository;
import com.portfolio.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AdminRepository adminRepository;

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        String token = tokenProvider.generateToken(authentication);
        Admin admin = adminRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername()).orElseThrow();
        return new JwtResponse(token, admin.getUsername(), admin.getEmail());
    }
}
