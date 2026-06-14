package com.bmt.MyStore.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

	@Id
	private int id;

	private String name;

	private LocalDate eventDate;

	public Event() {
	}

	public Event(int id, String name, LocalDate eventDate) {
		this.id = id;
		this.name = name;
		this.eventDate = eventDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
}
