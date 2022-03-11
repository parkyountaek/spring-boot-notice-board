package com.example.demo.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// final로 선언한 변수들에 대해서 생성자 자동 생성
@RequiredArgsConstructor
public class HelloResponseDto {
  private final String name;
  private final int amount;
}
