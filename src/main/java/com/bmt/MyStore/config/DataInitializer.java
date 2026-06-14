package com.bmt.MyStore.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bmt.MyStore.models.Admin;
import com.bmt.MyStore.models.Event;
import com.bmt.MyStore.repositories.AdminRepository;
import com.bmt.MyStore.repositories.EventRepository;

/**
 * Seeds the data the app needs on first run:
 *  - the single admin account (Srichetan / Srichetan)
 *  - the six fixed events
 * Both checks are idempotent, so it is safe to run on every startup.
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

			if (eventRepository.count() == 0) {
				eventRepository.save(new Event(1, "Musical", LocalDate.of(2026, 7, 10)));
				eventRepository.save(new Event(2, "Dance",   LocalDate.of(2026, 7, 15)));
				eventRepository.save(new Event(3, "Art",     LocalDate.of(2026, 7, 20)));
				eventRepository.save(new Event(4, "Boxing",  LocalDate.of(2026, 7, 25)));
				eventRepository.save(new Event(5, "Chess",   LocalDate.of(2026, 8, 1)));
				eventRepository.save(new Event(6, "Coding",  LocalDate.of(2026, 8, 5)));
			}
		};
	}
}
