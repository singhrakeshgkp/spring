package com.graphql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.graphql.entity.Article;
import com.graphql.entity.Comment;
import com.graphql.entity.Profile;
import com.graphql.repository.CommentRepository;
import com.graphql.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class ArticleController {

	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@SchemaMapping
	public Profile author(Article article) {
		Optional<Profile> optional = profileRepository.findById(article.getAuthorId());
		if(optional.isPresent())
			return optional.get();
		else
		return null;
	}
	
 @SchemaMapping
 public List<Comment> comments(Article article){
	return  commentRepository.findByArticleId(article.getId());
 }

}
