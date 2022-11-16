package com.graphql.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.graphql.entity.Article;
import com.graphql.entity.Comment;
import com.graphql.entity.Profile;
import com.graphql.repository.ArticleRepository;
import com.graphql.repository.CommentRepository;
import com.graphql.repository.ProfileRepository;

@Controller
@Transactional
public class QueryController {

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ProfileRepository profileRepository;
	
	@SchemaMapping(typeName = "Query", value = "articles")
	public List<Article> articles(){
		return articleRepository.findAll();
	}
	
	@QueryMapping
	public List<Profile> profiles(){
		return profileRepository.findAll();
	}
	
	@SchemaMapping(typeName = "Query", value = "comments")
	public List<Comment> comments(){
		return commentRepository.findAll();
	}
	
	@QueryMapping
	public Article article(@Argument Integer id) {
		Optional<Article> optional = articleRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			return null;
		}
	}
}
