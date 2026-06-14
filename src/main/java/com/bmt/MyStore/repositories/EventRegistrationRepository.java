package com.bmt.MyStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmt.MyStore.models.EventRegistration;

import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

	List<EventRegistration> findAll();

	List<EventRegistration> findByUserEmail(String userEmail);

	boolean existsByUserEmailAndEventNumber(String userEmail, int eventNumber);

	List<EventRegistration> findAllByOrderByUserEmailAsc();
}
