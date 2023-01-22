package com.graphql.controller;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
public class MutationTestController {

	@Autowired
	private GraphQlTester graphQlTester;
	
	
	
	@Test
	public void createArticle() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("title", "this is test article");
		map.put("text", "tesfsjdkfdsff");
		map.put("authorId", 2);
		
		/* Map will be equivalent to below input
		 * 
			{
			     "title":"additing article by input type",
			     "text":"article text will fdf here",
			    "authorId": 2
			  }
		 * */
		graphQlTester.documentName("test-mutation")
		.operationName("createArticleOperation")
		.variable("input", map)
		.executeAndVerify();
		
	}
	
}
