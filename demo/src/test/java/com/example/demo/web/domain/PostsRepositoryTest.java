package com.example.demo.web.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {
  @Autowired
  PostsRepository postsRepository;

  @AfterEach
  public void cleanup() {
    postsRepository.deleteAll();
  }

  @Test
  public void 게시글저장_불러오기() {
    // given
    String title = "title";
    String content = "content";

    postsRepository.save(Posts.builder()
      .title(title)
      .content(content)
      .author("test@gmail.com")
      .build());
    // when
    List<Posts> postList = postsRepository.findAll();

    // then
    Posts posts = postList.get(0);
    Assertions.assertThat(posts.getTitle()).isEqualTo(title);
    Assertions.assertThat(posts.getContent()).isEqualTo(content);
  }

}
