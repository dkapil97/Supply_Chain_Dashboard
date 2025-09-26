package com.kd.auth;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kd.exception.UserNotFoundException;
import com.kd.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service

public class UserServiceImple implements UserDetailsService{

	private final UserRepository userRepository;
	//private final ModelMapper modelMapper;

	
	
	public UserServiceImple(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/*private UserDTO convertDto(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}
	
	private User convertEntity(UserDTO userDTO) {
		return modelMapper.map(userDTO, User.class);
	}*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User u = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

	        return org.springframework.security.core.userdetails.User
	                .withUsername(u.getUsername())
	                .password(u.getPassword())
	                .authorities(new SimpleGrantedAuthority(u.getRole().name()))
	                .accountExpired(false)
	                .accountLocked(false)
	                .credentialsExpired(false)
	                .disabled(false)
	                .build();
	}
}
