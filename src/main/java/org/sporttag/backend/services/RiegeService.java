package org.sporttag.backend.services;

import org.sporttag.backend.entities.Riege;
import org.sporttag.backend.repositories.RiegeRepository;
import org.springframework.stereotype.Service;

@Service
public class RiegeService {
    private RiegeRepository db;

    public RiegeService(RiegeRepository db) {
        this.db = db;
    }

    public Long findOrCreateDefaulRiegeForSportklasse(Long sportklasseId){
        Riege riege = db.getDefaultRiegeFromSportklasseId(sportklasseId);
        if(riege == null){
            riege = new Riege(0, true, sportklasseId);
            return db.save(riege).getId();
        }
        return riege.getId();
    }

    public void save(Riege riege){
        db.save(riege);
    }
}
