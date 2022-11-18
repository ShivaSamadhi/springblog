package com.codeup.haskellspringblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    @GetMapping("/posts")
    public String posts(Model model){
        List<Post> allPosts = new ArrayList<>();
        Post newPost = new Post(1, "Swag", "Hopped up out the bed. Turned my swag on. Took a look in the mirror, said wassup.");
        allPosts.add(newPost);
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
    @ResponseBody
    public String viewCreate(){
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPosts(){
        return "create a new post";
    }
}
