package com.te.golms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.te.golms.entity.Technology;

public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

	@Query("SELECT t FROM Technology t WHERE t.technologyName =:technologyName")
	Optional<Technology> findByTechnologyName(String technologyName);
}
