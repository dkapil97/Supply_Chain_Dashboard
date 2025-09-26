package com.kd.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kd.auth.Role;
import com.kd.auth.User;
import com.kd.repository.UserRepository;

@Configuration
public class SeedDataRunner {
	
	@Bean
	CommandLineRunner createAdminIfMissing(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args-> {
			if (!userRepository.existsByUsername("admin")) {
                User admin =  User.builder()
                        .username("admin")
                        .password(bCryptPasswordEncoder.encode("adminpass"))
                        .role(Role.ROLE_ADMIN)
                        .build();
                userRepository.save(admin);
                System.out.println("Seeded admin user -> username: admin / password: adminpass");
            }
		};
	}
}
