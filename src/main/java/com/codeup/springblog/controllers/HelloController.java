package com.codeup.springblog.controllers;

import com.codeup.springblog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// controller notifies spring (similar to servlet)
@Controller
public class HelloController {

    private final EmailService emailSvc;

//    injecting the email service so we can use it in our helloController
    public HelloController(EmailService emailService){
        this.emailSvc = emailService;
    }

    @GetMapping("/hello")
//    responsebody - out "doGet" method
    @ResponseBody
    public String hello(){
        return "<h1>Hello from spring</h1>";
    }

//    instead of getparameter can pass in variable
//    @GetMapping("/hello/{name}")
//    @ResponseBody
////    this is what we are looking for in path variable (name)
//    public String sayHello(@PathVariable String name){
//        return "Hello " + name + "!";
//    }

//    Connected to the home.html file using spring
    @GetMapping("/hello/{name}")
//    Model is object from Spring (not model from mvc)
    public String sayHello(@PathVariable String name, Model model){
//        setting the name variable to the name of "name"
        model.addAttribute("name", name);
        return "hello";
    }

//    @GetMapping("/join")
//    public String showJoinForm(){
//        return "join";
//    }
//
////    Connected to join.html. Used pizza for names to clarify what is happening
//    @PostMapping("/join")
////    RequestParam references the name from the html form (join.html)..similar to request.getParameter()
//    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model){
//        model.addAttribute("pizza", "Welcome to " + cohort + "!");
//        return "join";
//    }

//    REFACTOR to use emailService
    @GetMapping("/join")
    public String showJoinForm(){
        return "join";
    }


    @PostMapping("/join")
    public String joinCohort(@RequestParam(name = "cohort") String cohort, Model model){
        model.addAttribute("pizza", "Welcome to " + cohort + "!");
//        using prepareAndSend method from EmailService class
        emailSvc.prepareAndSend("crdorito@hotmail.com", "Hello! This is the first email from " + cohort, "Thank you for attending the class.");
        return "join";
    }

    @GetMapping("/number/{num}")
    @ResponseBody
//    @pathvariable
    public int displayNumber(@PathVariable int num){
        return num;
    }

//    More verbose way
    @RequestMapping(path = "/hello/in/{color}", method = RequestMethod.GET)
    @ResponseBody
    public String helloInColor(@PathVariable String color){
        return "<h1 style=\"color: " + color + "\">Hello in " + color + "</h1>";
    }
}
