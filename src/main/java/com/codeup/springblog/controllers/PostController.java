package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {



    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailSvc;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailSvc) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailSvc = emailSvc;
    }

    @GetMapping("/posts")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

//    @GetMapping("/posts/{id}")
//    public String findById(@PathVariable long id, Model model) {
//        model.addAttribute("post", postDao.findById(id));
//        return "posts/show";
//    }

//    REFACTOR to use authentication; if the post user is logged in, then the delete/edit buttons should show. Otherwise, they see the post only
    @GetMapping("/posts/{id}")
    public String singlePost(@PathVariable long id, Model model) {
        Post post = postDao.getById(id);
        boolean isPostOwner = false;
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            isPostOwner = currentUser.getId() == post.getUser().getId();
        }
        model.addAttribute("post", post);
        model.addAttribute("isPostOwner", isPostOwner);
        return "posts/show";
    }

    @PostMapping("/posts/delete/{id}")
    public String deleteById(@PathVariable long id) {
        postDao.deleteById(id);
        return "redirect:/posts";
    }

//    @GetMapping("/posts/edit/{id}")
//    public String postToEdit(@PathVariable long id, Model model) {
//        model.addAttribute("post", postDao.findById(id));
//        return "posts/edit";
//    }
//
//    @PostMapping("/posts/edit/update/{id}")
//    public String editPost(@PathVariable long id, @RequestParam String title, @RequestParam String body) {
//        Post updatedPost = postDao.getById(id);
//        updatedPost.setTitle(title);
//        updatedPost.setBody(body);
//        postDao.save(updatedPost);
//        return "redirect:/posts";
//    }

//    @GetMapping("/posts/create")
//    public String showCreateForm() {
//        return "posts/create";
//    }

    //    REFACTORING FOR FORM MODEL BINDING
    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }




//    REFACTOR for using authentication and grabbing the user thats signed in
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
//        Line below gets the user that is logged in using the prebuilt SecurityContextHolder class; sets it as a User object so we can use it.
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
//        emailSvc.prepareAndSend(userDao.getById(1L).getEmail(), "New post", "Thank you for creating a new post!");
        postDao.save(post);
        return "redirect:/posts";
    }

//    REFACTORING FOR FORM MODEL BINDING

//    @GetMapping("/posts/{id}/edit")
//    public String postToEdit(@PathVariable long id, Model model) {
//        Post post = postDao.getById(id);
//        System.out.println("user id: " + post.getUser().getId());
//        model.addAttribute("post", post);
//        model.addAttribute("id", id);
//        return "posts/edit";
//    }

    //    INSTRUCTOR EXAMPLE
    @GetMapping("/posts/{id}/edit")
    public String postToEdit(@PathVariable long id, Model model) {
//        checks to see if the user is logged in and has authentication
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = postDao.getById(id);
        if(currentUser.getId() == post.getUser().getId()){
            model.addAttribute("post", post);
            model.addAttribute("id", id);
            return "posts/edit";
        }
        else {
            return "redirect:/posts/" + id;
        }
    }


    @PostMapping("/posts/{id}/edit")
    public String postToEdit(@PathVariable long id, @ModelAttribute Post post) {
//        checks to see if the user is logged in and has authentication
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post postFromDB = postDao.getById(id);
        if(user.getId() == postFromDB.getUser().getId()){
            post.setUser(user);
            postDao.save(post);
        }
            return "redirect:/posts/" + id;
        }

//    Can't get edit update to work. deleting the user
//    @PostMapping("/posts/edit/update/{id}")
//    public String editPost(@PathVariable long id, @ModelAttribute Post post) {
////        System.out.println("user id: " + post.getUser().getUsername());
//        postDao.save(post);
//        return "redirect:/posts";
//    }

//    since we are using .save (or PUT to the db), have to set the user. unable to only update the title/body
//    after commented line below, is the solution to grabbing the id of the user that posted
    @PostMapping("/posts/edit/update/{id}")
    public String editPost(@PathVariable long id, @ModelAttribute Post post) {
//        post.setUser(userDao.getById(1L));
        User postUser = postDao.getById(post.getId()).getUser();
        post.setUser(postUser);
        postDao.save(post);
        return "redirect:/posts";
    }

}
