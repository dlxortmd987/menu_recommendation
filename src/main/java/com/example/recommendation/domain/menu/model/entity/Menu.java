package com.example.recommendation.domain.menu.model.entity;

import com.example.recommendation.domain.global.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Menu extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	protected Menu() {
	}

	public Menu(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
