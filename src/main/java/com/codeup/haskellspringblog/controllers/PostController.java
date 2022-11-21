package com.codeup.haskellspringblog.controllers;

import com.codeup.haskellspringblog.models.Post;
import com.codeup.haskellspringblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String posts(Model model){
        List<Post> allPosts = postDao.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model){
        Post singlePost = new Post(id, "Another One", "It goes on and on. Cant understand how I code so long.");
        model.addAttribute("post", singlePost);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreate(){
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPosts(@RequestParam(name = "title")String title, @RequestParam(name = "body")String body){
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/posts";
    }
}
