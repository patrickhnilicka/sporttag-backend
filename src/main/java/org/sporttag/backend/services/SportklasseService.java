package org.sporttag.backend.services;

import org.sporttag.backend.dataclasses.Sportklasse;
import org.sporttag.backend.dataclasses.Sportlehrer;
import org.sporttag.backend.dto.SportklasseDto;
import org.sporttag.backend.repositories.SportklasseRepository;
import org.springframework.stereotype.Service;

@Service
public class SportklasseService {
    private SportklasseRepository db;
    private SportlehrerService sportlehrerService;

    public SportklasseService(SportklasseRepository db, SportlehrerService sportlehrerService) {
        this.db = db;
        this.sportlehrerService = sportlehrerService;
    }

    public Sportklasse getSportklasse(String name){
        return db.findSportklasseByName(name);
    }

    public Sportklasse getOrCreateSportklasse(SportklasseDto sportklasseDto){
        Sportklasse sportklasse = db.findSportklasseByName(sportklasseDto.name());
        if(sportklasse != null){
            return sportklasse;
        }
        Sportlehrer sportlehrer = sportlehrerService.getOrCreateSportlehrer(sportklasseDto.sportlehrerkuerzel());
        sportklasse = new Sportklasse(sportklasseDto.name(), sportlehrer);
        db.save(sportklasse);
        return sportklasse;
    }
}
