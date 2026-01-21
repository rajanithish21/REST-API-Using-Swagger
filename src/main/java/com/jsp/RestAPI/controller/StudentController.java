package com.jsp.RestAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.jsp.RestAPI.entity.Student;
import com.jsp.RestAPI.service.StudentService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private final StudentService studentService;

	//Save a record
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, Object> saveStudent(@RequestBody Student student) {

		return studentService.saveStudent(student);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Map<String, Object> fetchStudents() {
		return studentService.fetchAllStudents();
	}

	@GetMapping("/id/{id}")
	public Map<String, Object> fetchById(@PathVariable Integer id) {
		return studentService.fetchById(id);
	}

	@GetMapping("/result/{result}")
	public Map<String, Object> fetchByResult(@PathVariable String result) {
		return studentService.fetchByResult(result);
	}

	@GetMapping("/maths/greater/{marks}")
	public Map<String, Object> fetchByMathsGreater(@PathVariable Integer marks) {
		return studentService.fetchByMathsGreater(marks);
	}

	@GetMapping("/name/{name}")
	public Map<String, Object> fetchByName(@PathVariable String name) {
		return studentService.fetchByName(name);
	}

	@GetMapping("/mobile/{mobile}")
	public Map<String, Object> fetchByMobile(@PathVariable Long mobile) {
		return studentService.fetchByMobile(mobile);
	}

	// Delete By ID
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Integer id) {
		studentService.deleteById(id);
	}

	// Complete Update

	@PutMapping("/{id}")
	public Map<String, Object> updateComplete(@RequestBody Student student, @PathVariable Integer id) {
		return studentService.updateComplete(student, id);

	}

	// Partial Update

	@PatchMapping("/{id}")
	public Map<String, Object> updatePartial(@RequestBody Student student, @PathVariable Integer id) {
		return studentService.updatePartial(student, id);
	}

}
