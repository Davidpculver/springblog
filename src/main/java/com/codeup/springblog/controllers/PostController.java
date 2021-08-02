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
//        Instructor:
//        postRepo.delete(id);
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


//    @PostMapping("/posts/create")
//    public String createPost(@RequestParam String title, @RequestParam String body) {
//        User user1 = userDao.getById(1L);
//        Post post = new Post(title, body, user1);
//        postRepo.save(post);
//        return "redirect:/posts";
//    }

    //    REFACTORING FOR FORM MODEL BINDING
//    @PostMapping("/posts/create")
//    public String createPost(@ModelAttribute Post post) {
//        post.setUser(userDao.getById(1L));
//        emailSvc.prepareAndSend(userDao.getById(1L).getEmail(), "New post", "Thank you for creating a new post!");
//        postDao.save(post);
//        return "redirect:/posts";
//    }

//    REFACTOR for using authentication and grabbing the user thats signed in
    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post post) {
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
