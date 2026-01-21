package com.jsp.RestAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.RestAPI.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	boolean existsByMobile(Long mobile);
	
	List<Student> findByResult(String result);
	
	List<Student> findByMathsGreaterThan(Integer marks);
	
	Optional<Student> findByMobile(Long mobile);
	
	List<Student> findByName(String name);
 
}
