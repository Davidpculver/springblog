package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Park;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

//    @GetMapping("/posts")
//    @ResponseBody
//    public String postIndexPage(){
//        return "<h1>This is the posts index page</h1>";
//    }
//
//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String viewIndividualPosts(@PathVariable long id){
//        return "<h1>This shows an individual post with id of </h1>" + id;
//    }
//
////    When you visit you will see form to create post
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String createPostForm(){
//        return "<h1>This is the create a new post form</h1>";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createNewPost(){
//        return "<h1>This is the create new post page</h1>";
//    }


//    List<Post> posts = new ArrayList<>();
//
//
//    @GetMapping("/index")
//    public String showPosts(Model model) {
//        Post post1 = new Post("Post 1", "This is the first post.");
//        Post post2 = new Post("Post 2", "This is the second post.");
//
//
//        posts.add(post1);
//        posts.add(post2);
//
//        model.addAttribute("posts", posts);
//
//        return "posts/index";
//    }
//
//
//    //        For individual post
//    @GetMapping("/posts/{id}")
//    public String viewIndividualPosts(@PathVariable long id, Model model) {
//        Post post = new Post("Mailman bites dog", "Dog is suing the city for treats.");
//        model.addAttribute("post", post);
//        return "posts/show";
//    }

    private final PostRepository postRepo;

    public PostController(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postRepo.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{n}")
    public String findById(@PathVariable long n, Model model) {
        model.addAttribute("post", postRepo.findById(n));
        return "posts/show";
    }

    @PostMapping("/posts/delete/{id}")
    public String deleteById(@PathVariable long id) {
        postRepo.deleteById(id);
//        Instructor:
//        postRepo.delete(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/edit/{id}")
    public String postToEdit(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/edit/update/{id}")
    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
        Post updatedPost = postRepo.getById(id);
        updatedPost.setTitle(title);
        updatedPost.setBody(body);
        postRepo.save(updatedPost);
        return "redirect:/posts";
    }




}
