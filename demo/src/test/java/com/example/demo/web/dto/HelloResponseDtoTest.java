package com.example.demo.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloResponseDtoTest {
  @Test
  public void 롬복_기능_테스트() {
    // given
    String name = "test";
    int amount = 100;
    // when
    HelloResponseDto dto = new HelloResponseDto(name, amount);
    // then
    Assertions.assertThat(dto.getName()).isEqualTo("test");
    Assertions.assertThat(dto.getAmount()).isEqualTo(100);
  }
}
