package com.example.demo.web;

import java.util.List;

import com.example.demo.web.domain.Posts;
import com.example.demo.web.domain.PostsRepository;
import com.example.demo.web.dto.PostsSaveRequestDto;
import com.example.demo.web.dto.PostsUpdateRequestDto;

import org.junit.jupiter.api.AfterEach;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostsRepository postsRepository;

  @AfterEach
  public void tearDown() throws Exception {
    postsRepository.deleteAll();
  }

  @Test
  public void Posts_등록된다() throws Exception {
    //given
    String title = "title";
    String content = "content";
    PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                      .title(title)
                                      .content(content)
                                      .author("author")
                                      .build();

    String url = "http://localhost:" + port + "/api/v1/posts";
    
    // when
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

    // then
    Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> all = postsRepository.findAll();
    Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
    Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  public void Posts_수정된다() throws Exception {
    // given
    Posts savedPosts = postsRepository.save(Posts.builder()
                                              .title("title")
                                              .content("content")
                                              .author("author")
                                              .build());
    Long updateId = savedPosts.getId();
    String expectedTitle = "updateTitle";
    String expectedContent = "updateContent";
    PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                                        .title(expectedTitle)
                                        .content(expectedContent)
                                        .build();
    String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
    HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

    // when
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
    // then
    Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Posts> all = postsRepository.findAll();
    Assertions.assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
    Assertions.assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
  }
}
