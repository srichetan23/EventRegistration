package com.bmt.MyStore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bmt.MyStore.models.User;
import com.bmt.MyStore.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user=repo.findByName(name);
		
		if(user!=null) {
			var springUser=org.springframework.security.core.userdetails.User.withUsername(user.getName())
					.password(user.getPassword())
					.build();
			return springUser;
			
		}
		
		throw new UsernameNotFoundException("User not found with name: " + name);
	}

}
