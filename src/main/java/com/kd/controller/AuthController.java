package com.kd.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kd.auth.AuthService;
import com.kd.auth.Role;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    
    public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest req) {
        // by default register as USER; admin should be seeded
        String token = authService.register(req.getUsername(), req.getPassword(), Role.ROLE_USER);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        String token = authService.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @Data static class AuthRequest { private String username; private String password; }
    @Data static class AuthResponse { private final String token; }
}
