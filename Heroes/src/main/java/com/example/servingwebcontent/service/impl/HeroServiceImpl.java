package com.example.servingwebcontent.service.impl;

import com.example.servingwebcontent.model.Hero;
import com.example.servingwebcontent.repository.HeroRepository;
import com.example.servingwebcontent.service.HeroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroRepository heroRepository;

    public HeroServiceImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    @Override
    public Hero createHero(Hero hero) {
        return heroRepository.save(hero);
    }

    @Override
    public void deleteHero(Hero hero) {
        Optional<Hero> optionalHero = heroRepository.findById(hero.getId());
        if (optionalHero.isPresent()) {
            heroRepository.delete(optionalHero.get());
        }
    }

    @Override
    public Hero findHero(Hero hero) {
        List<Hero> heroList = heroRepository.findAll();

        for (Hero heroInList : heroList) {
            if (heroInList.getName().equals(hero.getName())
                    && heroInList.getType().equals(hero.getType())
                    && heroInList.getLevel() == hero.getLevel()) {
                return heroInList;
            }
        }

        return null;

    }
}