package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/posts/save")
  public String postSave() {
    return "posts-save";
  }
}
