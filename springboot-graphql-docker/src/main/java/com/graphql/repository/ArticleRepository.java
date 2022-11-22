package com.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graphql.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer>{

}
