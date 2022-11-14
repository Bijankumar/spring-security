package com.te.learnspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.learnspringsecurity.entity.Mentor;

public interface MentorRepository extends JpaRepository<Mentor, String>{

}
