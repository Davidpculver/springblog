package com.codeup.springblog.controllers;

import com.codeup.springblog.models.AdRepository;
import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {
    //    Can call this anything; this takes the place of checking if dao is null (singleton pattern)
    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @GetMapping("/ads")
    public String index(Model model) {
//        findAll() comes from the adRepository; just "magic" from the jpa
        model.addAttribute("ads", adDao.findAll());
        return "ads/index";
    }

    @GetMapping("/ads/{n}")
    public String viewOne(@PathVariable long n, Model model) {
        model.addAttribute("ad", adDao.findById(n));
        return "ads/show";
    }

    @GetMapping("/ads/first/{title}")
    public String findByTitle(@PathVariable String title, Model model) {
        model.addAttribute("ad", adDao.findByTitle(title));
        return "ads/show";
    }

}
