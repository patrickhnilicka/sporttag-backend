package org.sporttag.backend.services;

import org.sporttag.backend.dataclasses.Riege;
import org.sporttag.backend.dataclasses.Sportklasse;
import org.sporttag.backend.dataclasses.Sportlehrer;
import org.sporttag.backend.dto.SportklasseDto;
import org.sporttag.backend.repositories.SportklasseRepository;
import org.springframework.stereotype.Service;

@Service
public class SportklasseService {
    private SportklasseRepository db;

    public SportklasseService(SportklasseRepository db) {
        this.db = db;
    }

    public Sportklasse getSportklasse(String name){
        return db.findSportklasseByName(name);
    }

    public void saveSportklasse(Sportklasse sportklasse){
        db.save(sportklasse);
    }

    public Sportklasse findSportklasseByName(String name){
        return db.findSportklasseByName(name);
    }
}
