package com.bmt.MyStore.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

	@Id
	private int id; // fixed 1..6, so it doubles as the event number

	private String name;

	private LocalDate eventDate;

	@Column(length = 500)
	private String description;

	private String location;

	private int capacity;

	public Event() {
	}

	public Event(int id, String name, LocalDate eventDate, String description, String location, int capacity) {
		this.id = id;
		this.name = name;
		this.eventDate = eventDate;
		this.description = description;
		this.location = location;
		this.capacity = capacity;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
