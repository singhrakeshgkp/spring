package com.mapstruct.entity;

import com.MapStruct.student.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {
	private long id;
	private String firstName;
	private String lastName;
	private Address address;
	private String schoolName;
	
}
