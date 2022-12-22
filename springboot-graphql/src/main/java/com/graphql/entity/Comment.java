package com.graphql.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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