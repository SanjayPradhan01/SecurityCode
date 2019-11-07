package com.dbs.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dbs.entity.UserEntity;
import com.dbs.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		/*
		 * List<SimpleGrantedAuthority> list = new ArrayList<>(); list.add(new
		 * SimpleGrantedAuthority("ROLE_" + "admins"));
		 * 
		 * if(username.equals("foo")) { return new User("foo",
		 * "$2a$10$PqbwFOkdK.X1gZ3dxmcgwuh8w7jeC.3uhBMkADNEYuf4avBVN57B2", new
		 * ArrayList<>()); }else { throw new UsernameNotFoundException(
		 * "User name not found"); }
		 */
		Optional<UserEntity> entity = userRepository.findById(username);
		if (entity.isPresent()) {
			UserEntity user = entity.get();
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User name not found");
		}
	}

}
