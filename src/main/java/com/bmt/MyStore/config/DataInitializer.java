package com.bmt.MyStore.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bmt.MyStore.models.Admin;
import com.bmt.MyStore.models.Event;
import com.bmt.MyStore.repositories.AdminRepository;
import com.bmt.MyStore.repositories.EventRepository;

/**
 * Seeds the data the app needs on startup:
 *  - the single admin account (Srichetan / Srichetan)
 *  - the six fixed events (kept in sync on every boot)
 * Idempotent, so it is safe to run on every startup.
 */
@Configuration
public class DataInitializer {

	@Bean
	CommandLineRunner seedData(AdminRepository adminRepository, EventRepository eventRepository) {
		return args -> {
			if (!adminRepository.existsById("Srichetan")) {
				Admin admin = new Admin();
				admin.setAdminName("Srichetan");
				admin.setPassword("Srichetan");
				adminRepository.save(admin);
			}

			// Canonical event catalogue. Re-saved every boot so schema changes
			// (new columns like capacity/description) get populated on existing rows.
			List<Event> events = List.of(
				new Event(1, "Musical", LocalDate.of(2026, 7, 10),
					"An evening of live music with student bands and solo performers.",
					"Main Auditorium", 50),
				new Event(2, "Dance", LocalDate.of(2026, 7, 15),
					"Solo and group dance showcase across styles.",
					"Dance Studio B", 40),
				new Event(3, "Art", LocalDate.of(2026, 7, 20),
					"Paint, sketch and exhibit your work in a live art jam.",
					"Art Hall", 30),
				new Event(4, "Boxing", LocalDate.of(2026, 7, 25),
					"Amateur boxing matches across weight categories.",
					"Sports Arena", 20),
				new Event(5, "Chess", LocalDate.of(2026, 8, 1),
					"A classic battle of minds — knockout chess tournament.",
					"Room 204", 16),
				new Event(6, "Coding", LocalDate.of(2026, 8, 5),
					"Competitive coding contest with timed problem solving.",
					"Computer Lab 1", 60)
			);
			eventRepository.saveAll(events);
		};
	}
}
