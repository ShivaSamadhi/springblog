package com.codeup.haskellspringblog.controllers;

import com.codeup.haskellspringblog.models.Post;
import com.codeup.haskellspringblog.models.User;
import com.codeup.haskellspringblog.repositories.PostRepository;
import com.codeup.haskellspringblog.repositories.UserReposiitory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PostController {
    private PostRepository postDao;
    private UserReposiitory userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserReposiitory userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String getPosts(Model vModel) {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User loggedInUser = userDao.findById(principal.getId()).get();
            vModel.addAttribute("user", loggedInUser);
        }
        List<Post> posts = postDao.findAll();
        vModel.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable long id, Model vModel) {
        Post post = postDao.findById(id).get();
        vModel.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String savePost(@Valid Post post, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", post);
            return "posts/create";
        }
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(principal);
        postDao.save(post);
        emailService.prepareAndSend(post, "You created a new post!");
        return "redirect:/posts?created";
    }

    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Post post = postDao.findById(id).get();
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String submitEditForm(@Valid Post postUpdates, Errors validation, Model model) {
        if (validation.hasErrors()) {
            model.addAttribute("errors", validation);
            model.addAttribute("post", postUpdates);
            return "posts/edit";
        }
        Optional<Post> postToUpdate = postDao.findById(postUpdates.getId());
        Post post = postToUpdate.get();
        post.setTitle(postUpdates.getTitle());
        post.setBody(postUpdates.getBody());
        postDao.save(post);
        return "redirect:/posts?edited";
    }

//    @PostMapping("/posts/create")
//    public String createPosts(@RequestParam(name = "title")String title, @RequestParam(name = "body")String body){
//        Post post = new Post();
//        post.setTitle(title);
//        post.setBody(body);
//        postDao.save(post);
//        return "redirect:/posts";
//    }

    @PostMapping("/posts/create"){
        public String create(@ModelAttribute Post post){
            User user = userDao.getById(1L);
            post.setUser(user);
            postDao.save(post);
            return "redirect:/posts";
        }
    }
}
