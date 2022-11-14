package com.te.golms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.golms.entity.Request;

public interface RequestListRepository extends JpaRepository<Request, Integer>{

}
