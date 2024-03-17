package org.sporttag.backend.services;

import org.sporttag.backend.entities.Riege;
import org.sporttag.backend.entities.Sportklasse;
import org.sporttag.backend.entities.Sportlehrer;
import org.sporttag.backend.dto.SportklasseDto;
import org.springframework.stereotype.Service;

@Service
public class RiegeSportklassenService {
    private RiegeService riegeService;
    private SportklasseService sportklasseService;

    private SportlehrerService sportlehrerService;

    public RiegeSportklassenService(RiegeService riegeService, SportklasseService sportklasseService, SportlehrerService sportlehrerService) {
        this.riegeService = riegeService;
        this.sportklasseService = sportklasseService;
        this.sportlehrerService = sportlehrerService;
    }

    public Sportklasse getOrCreateSportklasse(SportklasseDto sportklasseDto, Long sporttagId){
        Sportklasse sportklasse = sportklasseService.findSportklasseByName(sportklasseDto.name(), sporttagId);
        if(sportklasse != null){
            return sportklasse;
        }
        Sportlehrer sportlehrer = sportlehrerService.getOrCreateSportlehrer(sportklasseDto.sportlehrerkuerzel());
        sportklasse = new Sportklasse(sportklasseDto.name(), sportlehrer, sporttagId);
        sportklasseService.saveSportklasse(sportklasse);
        getOrCreateDefaultRiegeForSportklasse(sportklasse);
        return sportklasse;
    }


    public Riege getOrCreateDefaultRiegeForSportklasse(Sportklasse sportklasse){
        Riege riege = riegeService.getDefaultRiegeFromSportklasse(sportklasse.getKlassenname());
        if(riege == null){
            riege = new Riege(0, true, sportklasse);
            riegeService.save(riege);
            return riege;
        }
        return riege;
    }

    public Riege getOrCreateDefaultRiegeForSportklasse(String sportklasseName, Long sporttagId){
        Sportklasse sportklasse = sportklasseService.getSportklasse(sportklasseName, sporttagId);
        return getOrCreateDefaultRiegeForSportklasse(sportklasse);
    }
}
