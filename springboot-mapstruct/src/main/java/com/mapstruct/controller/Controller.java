package com.mapstruct.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.MapStruct.entity.Student;
import com.MapStruct.mapper.StudentMapper;
import com.MapStruct.student.model.StudentDetails;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Controller {

	@PostMapping("api/student")
	public ResponseEntity<Student> saveStudent(@RequestBody StudentDetails studentDetails){
		log.info("before imapping student details string is {}",studentDetails);
		StudentMapper mapper = StudentMapper.INSTANCE;
		Student student = mapper.studentDetailsToStudent(studentDetails);
		log.info("after mapping student string is {}",student);
		return new ResponseEntity<Student>(HttpStatus.CREATED);
	}
}
