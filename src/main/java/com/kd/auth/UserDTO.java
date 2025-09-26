package com.kd.auth;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;

	private String username;

	private String password; // hashed

	private Role role;
}
