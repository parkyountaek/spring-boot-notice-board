package com.example.demo.web;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void 메인페이지_로딩() {
    // when
    String body = this.restTemplate.getForObject("/", String.class);
    //then
    Assertions.assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
  }
}
