package com.example.demo.web.service;

import javax.transaction.Transactional;

import com.example.demo.web.domain.Posts;
import com.example.demo.web.domain.PostsRepository;
import com.example.demo.web.dto.PostsResponseDto;
import com.example.demo.web.dto.PostsSaveRequestDto;
import com.example.demo.web.dto.PostsUpdateRequestDto;

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

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) throws IllegalAccessException {
    Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalAccessException("해당 게시글이 없습니다. id=" + id));

    posts.update(requestDto.getTitle(), requestDto.getContent());
    // 영속성 컨텍스트로 인해서 실제로 query문 날리게 하지 않음
    // Transaction이 끝나면 해당 테이블 변경시킴 -> 더티 체킹(별도로 Update 쿼리문 날리지 않아도 됨)
    return id;
  }

  public PostsResponseDto findById(Long id) throws IllegalAccessException {
    Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalAccessException("해당 게시글이 없습니다. id=" + id));
    return new PostsResponseDto(entity);
  }
}
