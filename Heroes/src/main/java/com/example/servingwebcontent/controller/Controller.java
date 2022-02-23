package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.model.Hero;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repository.HeroRepository;
import com.example.servingwebcontent.repository.UserRepository;
import com.example.servingwebcontent.service.HeroService;
import com.example.servingwebcontent.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;


@org.springframework.stereotype.Controller
public class Controller {

    private final UserService userService;
    private final UserRepository userRepository;
    private final HeroRepository heroRepository;
    private final HeroService heroService;
    private Long tempUserId = null;
    private String tempUserUsername = "Guest";


    public Controller(UserService userService, UserRepository userRepository, HeroRepository heroRepository, HeroService heroService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.heroRepository = heroRepository;
        this.heroService = heroService;
    }

    @GetMapping("/index")
    public String index() {
        tempUserUsername = "Guest";
        tempUserId = 0L;
        return "/index";
    }

    @GetMapping("/home")
    public ModelAndView home(@ModelAttribute User user) {
        if (tempUserId != null) {
            ModelAndView modelAndView = new ModelAndView("/home");
            modelAndView.addObject("username", tempUserUsername);
            return modelAndView;
        }
        else {
            return new ModelAndView("/index");
        }
    }

    @GetMapping("/create-hero")
    public ModelAndView createHero(@ModelAttribute User user) {
        if (tempUserId != null) {
            ModelAndView modelAndView = new ModelAndView("/create-hero");
            Hero hero = new Hero();
            modelAndView.addObject("hero", hero);
            return modelAndView;
        }
        else {
            return new ModelAndView("/index");
        }

    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("/login");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView showForm() {
        ModelAndView modelAndView = new ModelAndView("/register");
        User user = new User();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/saveUser")
    public ModelAndView saveUser(@ModelAttribute User user) {
        userService.createUser(user);
        tempUserId = user.getId();
        tempUserUsername = user.getUsername();
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("username", tempUserUsername);
        return modelAndView;
    }

    @GetMapping("/checkUser")
    public ModelAndView checkUser(@ModelAttribute User user) {
        ModelAndView modelAndView = new ModelAndView("/home");
        if (userRepository.findByUsername(user.getUsername()).getPassword().equals(user.getPassword())) {
            tempUserId = userRepository.findByUsername(user.getUsername()).getId();
            tempUserUsername = user.getUsername();
            modelAndView.addObject("username", tempUserUsername);
            return modelAndView;
        }
        else {
            return new ModelAndView("/login");
        }
    }

    @PostMapping("/saveHero")
    public ModelAndView saveHero(@ModelAttribute Hero hero) {
        hero.setUserId(tempUserId);
        heroService.createHero(hero);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("username", tempUserUsername);
        return modelAndView;
    }

    @GetMapping("/myHeroes")
    public ModelAndView myHeroes() {
        List<Hero> heroes = new ArrayList<Hero>(heroRepository.findByUserId(tempUserId));
        if (heroes.isEmpty()) {
            return new ModelAndView("no-heroes");
        }
        ModelAndView modelAndView = new ModelAndView("/my-heroes");
        modelAndView.addObject("heroes", heroes);
        return modelAndView;
    }

    @GetMapping("/warriorDetails")
    public String warriorDetails() {
        return "warrior-details";
    }

    @GetMapping("/archerDetails")
    public String archerDetails() {
        return "archer-details";
    }

    @GetMapping("/mageDetails")
    public String mageDetails() {
        return "mage-details";
    }

    @GetMapping("/delete-hero")
    public ModelAndView deleteHero() {
        if (tempUserId != null) {
            Hero hero = new Hero();
            ModelAndView modelAndView = new ModelAndView("delete-hero");
            modelAndView.addObject(hero);
            return modelAndView;
        }
        else {
            return new ModelAndView("/index");
        }

    }

    @GetMapping("removeHero")
    public ModelAndView removeHero(@ModelAttribute Hero hero) {
        Hero toDeleteHero = heroService.findHero(hero);
        if (toDeleteHero != null) {
            heroService.deleteHero(toDeleteHero);
        }
        return myHeroes();
    }

}
