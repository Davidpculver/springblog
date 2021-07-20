package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @GetMapping("/posts")
    @ResponseBody
    public String postIndexPage(){
        return "<h1>This is the posts index page</h1>";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String viewIndividualPosts(@PathVariable long id){
        return "<h1>This shows an individual post with id of </h1>" + id;
    }

//    When you visit you will see form to create post
    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm(){
        return "<h1>This is the create a new post form</h1>";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createNewPost(){
        return "<h1>This is the create new post page</h1>";
    }

}
