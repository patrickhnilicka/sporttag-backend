package org.sporttag.backend.services;

import org.sporttag.backend.entities.Sportklasse;
import org.sporttag.backend.entities.Sportlehrer;
import org.sporttag.backend.repositories.SportklasseRepository;
import org.sporttag.backend.viewmodels.SportklasseViewModel;
import org.sporttag.backend.viewmodels.SportlehrerViewModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SportklasseService {
    private SportklasseRepository db;

    public SportklasseService(SportklasseRepository db) {
        this.db = db;
    }

    public SportklasseViewModel getSportklasseViewModel(String name, Long sportagId){
        return toSportklasseViewModel(db.findSportklasseByName(name, sportagId));
    }

    public Sportklasse getSportklasse(String name, Long sporttagId){
        return db.findSportklasseByName(name, sporttagId);
    }

    public void saveSportklasse(Sportklasse sportklasse){
        db.save(sportklasse);
    }

    public Sportklasse findSportklasseByName(String name, Long sporttagId){
        return db.findSportklasseByName(name, sporttagId);
    }
    public List<SportklasseViewModel> findAllBySporttag(Long sporttagId){ return db.findAllBySporttag(sporttagId).stream().map(s -> toSportklasseViewModel(s)).toList();}

    private SportklasseViewModel toSportklasseViewModel(Sportklasse sportklasse){
        return new SportklasseViewModel(sportklasse.getId(), sportklasse.getKlassenname(), toSportlehrerViewModel(sportklasse.getSportlehrer()));
    }

    private SportlehrerViewModel toSportlehrerViewModel(Sportlehrer sportlehrer){
        return new SportlehrerViewModel(sportlehrer.getVorname(), sportlehrer.getNachname(), sportlehrer.getKuerzel());
    }

}
