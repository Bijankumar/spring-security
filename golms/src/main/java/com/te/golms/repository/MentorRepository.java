package com.te.golms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.te.golms.entity.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer> {

	@Query("SELECT m FROM Mentor m where m.mentorId =:mentorId")
	public Optional<Mentor> findByMentorId(String mentorId);

	@Query("SELECT m FROM Mentor m where m.email =:email")
	public Optional<Mentor> findByEmail(String email);
}
