package org.sporttag.backend.services;

import org.sporttag.backend.dto.RiegeDto;
import org.sporttag.backend.entities.Riege;
import org.sporttag.backend.repositories.RiegeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RiegeService {
    private RiegeRepository db;

    public RiegeService(RiegeRepository db) {
        this.db = db;
    }

    public void save(Riege riege){
        db.save(riege);
    }

    public List<RiegeDto> getRieges(Long sporttagId){
        List<Riege> rieges = db.getAllBySporttagId(sporttagId);
        return rieges.stream().map(r -> new RiegeDto(r.getId(), r.getNummer())).toList();
    }

    public Long insertRiegeIfNotExists(int riegenNr, Long sporttagId){
        Riege riege = db.getBySporttagIdAndNr(riegenNr, sporttagId).getFirst();
        if(riege == null){
            riege = new Riege(riegenNr, sporttagId);
            db.save(riege);
        }
        return riege.getId();
    }
}
