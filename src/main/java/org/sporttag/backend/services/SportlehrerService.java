package org.sporttag.backend.services;

import org.sporttag.backend.entities.Sportlehrer;
import org.sporttag.backend.repositories.SportlehrerRepository;
import org.springframework.stereotype.Service;

@Service
public class SportlehrerService {
    private SportlehrerRepository db;

    public SportlehrerService(SportlehrerRepository db) {
        this.db = db;
    }

    public Long getOrCreateSportlehrer(String kuerzel) {
        Sportlehrer sportlehrer = db.findSportlehrerByKuerzel(kuerzel);
        if(sportlehrer != null){
            return sportlehrer.getId();
        }
        sportlehrer = new Sportlehrer("", "", kuerzel);
        db.save(sportlehrer);
        return sportlehrer.getId();
    }
}
