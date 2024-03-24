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

    public void save(Riege riege){
        db.save(riege);
    }
}
