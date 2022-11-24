package com.codeup.haskellspringblog.controllers;

import com.codeup.haskellspringblog.models.Ad;
import com.codeup.haskellspringblog.repositories.AdRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final AdRepository adsDao;

    public HomeController(AdRepository adsDao) {
        this.adsDao = adsDao;
    }
    @GetMapping("/home")
    public String welcome() {
        return "home";
    }


    @GetMapping("/ads")
    public String allAds(Model model) {
        List<Ad> ads = adsDao.findAll();
        for (Ad ad : ads) {
            System.out.println("This ad title: " + ad.getTitle());
            System.out.println("This ad description: " + ad.getDescription());
            System.out.println("Owner: " + ad.getOwner().getFirstName() + " " + ad.getOwner().getLastName());
        }
        model.addAttribute("ads", ads);

        return "ads";
    }
}
