package org.sporttag.backend.services;

import org.sporttag.backend.entities.Sporttag;
import org.sporttag.backend.repositories.SporttagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SporttagService {
    private SporttagRepository db;

    public SporttagService(SporttagRepository db) {
        this.db = db;
    }

    public List<Sporttag> getAllSporttage(){
        return db.findAll();
    }
}
