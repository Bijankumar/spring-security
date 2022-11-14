package com.te.golms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.te.golms.entity.Admin;
import com.te.golms.entity.Batch;
import com.te.golms.entity.Request;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("SELECT b FROM Batch b")
	public List<Batch> findAllBatches();

	@Query("SELECT a FROM Admin a where a.email =:email")
	public Optional<Admin> findByEmail(String email);

	@Query("SELECT r FROM Request r where r.isRejected = FALSE")
	public List<Request> findAllRequests();

	@Query("SELECT a FROM Admin a where a.adminId =:adminId")
	public Optional<Admin> findByAdminId(String adminId);
}
