package com.kd.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kd.repository.UserRepository;


@Service
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}
	public String register(String username,String password,Role role) {
		if(userRepository.existsByUsername(username)) {
			throw new IllegalArgumentException("Username already exists");
		}
		User user = User.builder().username(username).password(passwordEncoder.encode(password))
		.role(role).build();
		userRepository.save(user);
		return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
	}
	public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }
        return jwtUtil.generateToken(user.getUsername(), user.getRole().name());
    }
}
