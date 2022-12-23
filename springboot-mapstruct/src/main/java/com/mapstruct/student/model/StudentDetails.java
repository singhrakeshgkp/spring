package com.mapstruct.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDetails {
	private Integer id;
	private String fName;
	private String lName;
	private Address address;
	private String schoolName;
	
	
	
	
	

}
