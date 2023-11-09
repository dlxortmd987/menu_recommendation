package com.example.recommendation.domain.menu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.recommendation.domain.menu.model.entity.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

	Optional<Menu> findByName(String name);
}
