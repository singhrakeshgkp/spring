package com.graphql;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.graphql.entity.Article;
import com.graphql.entity.Comment;
import com.graphql.entity.Profile;
import com.graphql.repository.ArticleRepository;
import com.graphql.repository.CommentRepository;
import com.graphql.repository.ProfileRepository;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Transactional
public class PersistData  implements CommandLineRunner{

	private CommentRepository commentRepository;
    private ArticleRepository articleRepository;
    private ProfileRepository profileRepository;
	@Override
	public void run(String... args) throws Exception {
	
		Profile author = profileRepository.save(new Profile(null, "g00glen00b", "The author of this blog"));
        Profile admin = profileRepository.save(new Profile(null, "admin", "The administrator of this blog"));
        Article article1 = articleRepository.save(new Article(null, "Hello wold", "This is a hello world", author.getId()));
        Article article2 = articleRepository.save(new Article(null, "Foo", "Bar", admin.getId()));
        commentRepository.save(new Comment(null, "Do you like this article?", article1.getId(), author.getId()));
        commentRepository.save(new Comment(null, "This is a great article", article1.getId(), admin.getId()));
        commentRepository.save(new Comment(null, "This is a comment", article2.getId(), admin.getId()));

		
	}

}
