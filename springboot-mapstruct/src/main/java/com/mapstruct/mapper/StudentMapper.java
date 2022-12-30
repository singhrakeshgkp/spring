package com.mapstruct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.mapstruct.entity.Student;
import com.mapstruct.student.model.StudentDetails;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class StudentMapper {
	
	public static final StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

	String name = "\"us/en\"";
	
	  @Mappings(value = { @Mapping(target = "firstName",source="fName"),
	  @Mapping(target = "lastName",source = "lName"),
	  @Mapping(target = "id", source = "id")})
	 
	//@Mapping(target = "firstName", source = "fName")
	public abstract Student studentDetailsToStudent(StudentDetails studentDetails);
}
