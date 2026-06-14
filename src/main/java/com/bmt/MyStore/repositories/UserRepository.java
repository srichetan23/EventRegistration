package com.bmt.MyStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmt.MyStore.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByName(String name);

	public User findByEmail(String email);
}
