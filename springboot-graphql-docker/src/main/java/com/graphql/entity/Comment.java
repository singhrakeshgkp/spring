package com.graphql.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
public class Comment  implements BaseEntity{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="text")
    private String text;
    
    @Column(name="article_id")
    private Integer articleId;
    
    @Column(name="author_id")
    private Integer authorId;
}