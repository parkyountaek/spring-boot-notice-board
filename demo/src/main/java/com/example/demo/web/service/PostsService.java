package com.example.demo.web.service;

import javax.transaction.Transactional;

import com.example.demo.web.domain.PostsRepository;
import com.example.demo.web.dto.PostsSaveRequestDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostsService {
  private final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }
}
