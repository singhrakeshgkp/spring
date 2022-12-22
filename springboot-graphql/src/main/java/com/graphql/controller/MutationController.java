package com.graphql.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import com.graphql.entity.Article;
import com.graphql.entity.ArticleInput;
import com.graphql.entity.Profile;
import com.graphql.repository.ArticleRepository;
import com.graphql.repository.ProfileRepository;

import jakarta.transaction.Transactional;

@Controller
@Transactional
public class MutationController {

	@Autowired
	ProfileRepository profileRepository;
	
	@Autowired
	ArticleRepository articleRepository;
	
	/*Type1- with simple arguments*/
	@MutationMapping
	public Profile addProfile(
			@Argument String userName,
			@Argument String bio
			) {
		Profile profile = new Profile(null,userName,bio);
		Profile savedProfile=profileRepository.save(profile);
		 return savedProfile;
	}
	
	@MutationMapping
	public Article addArticle(
			@Argument String title,
			@Argument String text,
			@Argument Integer authorId
			){
		Article article = new Article(null, title, text, authorId);
		return articleRepository.save(article);
	}
	
	/*Type2- with input type arguments*/
	@MutationMapping
	public Article addArticleByArticleInput(@Argument ArticleInput articleInput){
		
		Article article = new Article(null, articleInput.getTitle(), articleInput.getText(), articleInput.getAuthorId());
		return articleRepository.save(article);
	}
}
