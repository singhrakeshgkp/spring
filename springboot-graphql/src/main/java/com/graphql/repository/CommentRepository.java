package com.graphql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graphql.entity.Comment;
@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{

	List<Comment> findByArticleId(Integer articleId);
}
