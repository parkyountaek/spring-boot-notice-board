package com.example.demo.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
  // api test, mvc test, get/post/put etc test
  @Autowired
  private MockMvc mvc;
  
  @Test
  public void hello가_리턴된다() throws Exception {
    String hello = "hello";
    mvc.perform(get("/hello"))
      .andExpect(status().isOk())
      .andExpect(content().string(hello));
  }

  @Test
  public void helloResponseDto가_리턴된다() throws Exception {
    String name = "test";
    int amount = 100;

    mvc.perform(get("/hello/dto")
      .param("name", name)
      .param("amount", String.valueOf(amount)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.name", is(name)))
      .andExpect(jsonPath("$.amount", is(amount)));
  }
}
