package com.te.golms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.golms.entity.MockDetails;

public interface MockDetailsRepository extends JpaRepository<MockDetails, Integer> {

}
