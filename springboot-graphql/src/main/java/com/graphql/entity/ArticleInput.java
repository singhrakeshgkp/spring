package com.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleInput {

	private Integer id;

	private String title;

	private String text;

	private Integer authorId;
}
