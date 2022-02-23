package com.example.servingwebcontent.repository;

import com.example.servingwebcontent.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeroRepository extends JpaRepository<Hero, Long> {
    List<Hero> findByUserId(Long id);
    Optional<Hero> findByNameAndTypeAndLevel(String name, String type, Long level);
}
