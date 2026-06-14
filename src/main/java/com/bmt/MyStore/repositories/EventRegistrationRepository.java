package com.bmt.MyStore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bmt.MyStore.models.EventRegistration;

import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

	List<EventRegistration> findAll();

	List<EventRegistration> findByUserEmail(String userEmail);

	boolean existsByUserEmailAndEventNumber(String userEmail, int eventNumber);

	List<EventRegistration> findAllByOrderByUserEmailAsc();

	long countByEventNumber(int eventNumber);

	@Transactional
	void deleteByUserEmailAndEventNumber(String userEmail, int eventNumber);
}
