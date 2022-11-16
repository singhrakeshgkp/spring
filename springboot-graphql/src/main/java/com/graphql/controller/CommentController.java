package com.graphql.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.graphql.entity.Comment;
import com.graphql.entity.Profile;
import com.graphql.repository.ProfileRepository;

@Controller
@Transactional
public class CommentController {

	@Autowired
	ProfileRepository profileRepository;
	
	@SchemaMapping
	public Profile author(Comment comment) {
		Optional<Profile> optional = profileRepository.findById(comment.getAuthorId());
	    if(optional.isPresent())
	    	return optional.get();
	    else
	    	return null;
	}
}
