package com.graphql.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.graphql.entity.Article;

@SpringBootTest
@AutoConfigureGraphQlTester
public class QueryControllerTest {
	
	@Autowired
	GraphQlTester graphQlTester;
	
	//@Import({ArticleRepository.class,ProfileRepository.class,CommentRepository.class})// it will inject ArticleRepository, performing slice testing
	//@GraphQlTest(QueryController.class)

	//@Test
	public void getArticleById_Approach1() {
		
		String document = "query articleById($id: Int!){\r\n"
				+ "  article(id:$id){\r\n"
				+ "    id\r\n"
				+ "    title\r\n"
				+ "    author{\r\n"
				+ "      id\r\n"
				+ "      userName\r\n"
				+ "    }\r\n"
				+ "    \r\n"
				+ "  }\r\n"
				+ "}";
				
				
		graphQlTester.document(document)
		.variable("id", 1)
		.execute()
		.path("article")
		.entity(Article.class)
		.get()
		.getId()
		.equals(1);
	}
	
	@Test
	public void getArticleById_Approach2() {
		
		graphQlTester.documentName("test-query")
		.operationName("articleById")
		.variable("id", 1)
		.execute()
		.path("article")
		.entity(Article.class)
		.get()
		.getId()
		.equals(1);
	}
	
	@Test
	public void getAllArticles() {
		
		graphQlTester.documentName("test-query")
		.operationName("getAllArticles")
		.execute()
		.path("articles")
		.entityList(Article.class)
		.hasSizeGreaterThan(1);
	}
	
	

}
