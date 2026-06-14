package com.bmt.MyStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmt.MyStore.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

	Admin findByAdminNameAndPassword(String adminName, String password);
	
}
