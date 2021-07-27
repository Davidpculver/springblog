package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.AdRepository;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {
    //    Can call this anything; this takes the place of checking if dao is null (singleton pattern)
    private final AdRepository adDao;
    private final UserRepository userDao;

    public AdController(AdRepository adDao, UserRepository userDao) {
        this.adDao = adDao;
        this.userDao = userDao;
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

    @GetMapping("/ads/create")
    public String createAdForm(Model model) {
//        Passing new Ad to the form, automatically creating the object descriptions(body, title, etc)
        model.addAttribute("ad", new Ad());
        return "ads/create";
    }

    @PostMapping("/ads/create")
//    this will take in the modelattribute; which is the Ad created from the form
    public String createAd(@ModelAttribute Ad ad) {
//        setUser calls back the Ad model class
        ad.setUser(userDao.getById(1L));
        adDao.save(ad);
        return "redirect:/ads";
    }

}
