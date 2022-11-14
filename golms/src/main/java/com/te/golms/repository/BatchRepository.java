package com.te.golms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.te.golms.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

	@Query("SELECT b FROM Batch b where b.batchName =:batchName")
	public abstract Optional<Batch> findByBatchName(String batchName);

}
