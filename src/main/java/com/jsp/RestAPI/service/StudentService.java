package com.jsp.RestAPI.service;

import java.util.Map;

import com.jsp.RestAPI.entity.Student;

public interface StudentService {

	public Map<String, Object> saveStudent(Student student);

	public Map<String, Object> fetchAllStudents();

	public Map<String, Object> fetchById(Integer id);

	public Map<String, Object> fetchByResult(String Result);

	public Map<String, Object> fetchByMathsGreater(Integer marks);
	
	public Map<String, Object> fetchByName(String name);
	
	public Map<String, Object> fetchByMobile(Long mobile);

	public void deleteById(Integer id);

	public Map<String, Object> updateComplete(Student student, Integer id);

	public Map<String, Object> updatePartial(Student student, Integer id);


}
