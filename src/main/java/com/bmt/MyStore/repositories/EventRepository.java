package com.bmt.MyStore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmt.MyStore.models.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {

	List<Event> findAllByOrderByIdAsc();
}
