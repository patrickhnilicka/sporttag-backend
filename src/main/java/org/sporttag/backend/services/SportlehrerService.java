package org.sporttag.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sporttag.backend.entities.Sportlehrer;
import org.sporttag.backend.repositories.SportlehrerRepository;
import org.sporttag.backend.viewmodels.SportlehrerViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportlehrerService {
    private SportlehrerRepository db;
    Logger logger = LoggerFactory.getLogger(SportlehrerService.class);

    public SportlehrerService(SportlehrerRepository db) {
        this.db = db;
    }

    public List<SportlehrerViewModel> getAll(){
        return db.findAll().stream()
                .map(s -> new SportlehrerViewModel(s.getId(),s.getVorname(),s.getNachname(),s.getKuerzel()))
                .toList();
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

    private Sportlehrer getSportlehrerById(Long id){
        Optional<Sportlehrer> sportlehrerOptional = db.findById(id);
        if (sportlehrerOptional.isPresent()){
            return sportlehrerOptional.get();
        }
        else {
            logger.error("Sportlehrer with id " + id + " not found in db.");
            return null;
        }
    }

    public void saveSportlehrer(SportlehrerViewModel sportlehrerViewModel){
        Sportlehrer sportlehrer = getSportlehrerById(sportlehrerViewModel.id());
        if(sportlehrer != null) {
            sportlehrer.setVorname(sportlehrerViewModel.vorname());
            sportlehrer.setNachname(sportlehrerViewModel.nachname());
            db.save(sportlehrer);
        }
    }
}
