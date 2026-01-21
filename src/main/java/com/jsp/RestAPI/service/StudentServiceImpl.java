package com.jsp.RestAPI.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.jsp.RestAPI.controller.*;
import com.jsp.RestAPI.entity.Student;
import com.jsp.RestAPI.exception.DataExistsException;
import com.jsp.RestAPI.exception.DataNotFoundException;
import com.jsp.RestAPI.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

import com.jsp.RestAPI.exception.MyExceptionHandlingClass;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

	private final StudentRepository studentRepository;

	@Override
	public Map<String, Object> saveStudent(Student student) {
		if (studentRepository.existsByMobile(student.getMobile()))
			throw new DataExistsException(student.getMobile() + "Already Exists");
		double percentage = (student.getMaths() + student.getScience() + student.getEnglish()) / 3.0;
		student.setPercentage(percentage);
		student.setResult(percentage >= 85 ? "distinction"
				: percentage >= 60 ? "firstclass" : percentage >= 35 ? "pass" : "fail");
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("message", "Data Saved Success");
		map.put("data", studentRepository.save(student));
		return map;
	}

	@Override
	public Map<String, Object> fetchAllStudents() {
		List<Student> list = studentRepository.findAll();
		if (list.isEmpty())
			throw new DataNotFoundException("No Records Found");
		else {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("message", "Data Found");
			map.put("data", list);
			return map;
		}

	}

	@Override
	public Map<String, Object> fetchById(Integer id) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("No Data Found With ID:" + id));
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("message", "Data Found");
		map.put("data", student);
		return map;
	}

	@Override
	public Map<String, Object> fetchByResult(String result) {
		List<Student> list = studentRepository.findByResult(result);
		if (list.isEmpty())
			throw new DataNotFoundException("No Records Found With Result:" + result);
		else {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("message", "Data Found");
			map.put("data", list);
			return map;
		}
	}

	@Override
	public Map<String, Object> fetchByMathsGreater(Integer marks) {
		List<Student> list = studentRepository.findByMathsGreaterThan(marks);
		if (list.isEmpty())
			throw new DataNotFoundException("No Records Found For Marks Greater Than: " + marks);
		else {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("message", "Data Found");
			map.put("data", list);
			return map;

		}
	}

	@Override
	public Map<String, Object> fetchByName(String name) {
		List<Student> list = studentRepository.findByName(name);
		if (list.isEmpty())
			throw new DataNotFoundException("No Records Found With Name:" + name);
		else {
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			map.put("message", "Data Found");
			map.put("data", list);
			return map;
		}
	}

	@Override
	public Map<String, Object> fetchByMobile(Long mobile) {
		Student student = studentRepository.findByMobile(mobile)
				.orElseThrow(() -> new DataNotFoundException("No Data Found With mobile:" + mobile));
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("message", "Data Found");
		map.put("data", student);
		return map;
	}

	@Override
	public void deleteById(Integer id) {
		studentRepository.deleteById(id);

	}

	@Override
	public Map<String, Object> updateComplete(Student student, Integer id) {
		studentRepository.findById(id).orElseThrow(() -> new DataNotFoundException("No Data With Id:" + id));
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("message", "Data Updated Success");
		map.put("data", studentRepository.save(student));
		return map;
	}

	@Override
	public Map<String, Object> updatePartial(Student student, Integer id) {
		Student exStudent = studentRepository.findById(id)
				.orElseThrow(() -> new DataNotFoundException("No Data with Id : " + id));

		exStudent.setEnglish(student.getEnglish() != null ? student.getEnglish() : exStudent.getEnglish());
		exStudent.setMaths(student.getMaths() != null ? student.getMaths() : exStudent.getMaths());
		exStudent.setScience(student.getScience() != null ? student.getScience() : exStudent.getScience());
		exStudent.setName(student.getName() != null ? student.getName() : exStudent.getName());
		exStudent.setMobile(student.getMobile() != null ? student.getMobile() : exStudent.getMobile());

		double percentage = (exStudent.getMaths() + exStudent.getScience() + exStudent.getEnglish()) / 3.0;

		exStudent.setPercentage(percentage);

		exStudent.setResult(percentage >= 75 ? "distinction"
				: percentage >= 60 ? "firstclass" : percentage >= 35 ? "pass" : "fail");

		Map<String, Object> map = new LinkedHashMap<>();
		map.put("message", "Data Saved Success");
		map.put("data", studentRepository.save(exStudent));

		return map;
	}
}
