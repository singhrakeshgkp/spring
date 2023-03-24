package com.readtracker.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.readtracker.persistent.Author;
import com.readtracker.repo.AuthorRepo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("persist/")
@Slf4j
public class DumpDataController {

	@Autowired 
	private AuthorRepo authorRepo;
	
	@Value("${source.location.authorfile}")
	private String authorFileLocation;
	@Value("${source.location.worksfile}")
	private String workFileLocaton;
	
	
	
	
	@GetMapping("/authordata")
	public ResponseEntity<String> persistAuthorData(){
		
		intitAuthorData();
		//authorRepo.saveAll(null);
		return null;
	}
	
	
	public void intitAuthorData() {
		Path path = Paths.get(authorFileLocation);
		try(Stream<String> lines=Files.lines(path)){
		
			lines.forEach(line->{
				String jsonString = line.substring(line.indexOf("{"));
				JSONObject jsonObject = new JSONObject(jsonString);
				Author author = new Author();
				author.setId(jsonObject.optString("key").replace("/authors/", ""));
				author.setName(jsonObject.optString("name"));
				author.setPersonalName(jsonObject.optString("personal_name"));
				authorRepo.save(author);
			});
			
		}catch(IOException ex) {
			log.error("DumpDataController.intitAuthorData() error occurred{}",ex.getCause());
		}

	}
}
