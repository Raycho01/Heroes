package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.Hero;

import java.util.Optional;

public interface HeroService {

    Hero createHero(Hero hero);

    void deleteHero(Hero hero);

    Hero findHero(Hero hero);

}
