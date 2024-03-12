package org.sporttag.backend.services;

import org.sporttag.backend.dataclasses.Riege;
import org.sporttag.backend.dataclasses.Sportklasse;
import org.sporttag.backend.dataclasses.Sportlehrer;
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

    public Sportklasse getOrCreateSportklasse(SportklasseDto sportklasseDto){
        Sportklasse sportklasse = sportklasseService.findSportklasseByName(sportklasseDto.name());
        if(sportklasse != null){
            return sportklasse;
        }
        Sportlehrer sportlehrer = sportlehrerService.getOrCreateSportlehrer(sportklasseDto.sportlehrerkuerzel());
        sportklasse = new Sportklasse(sportklasseDto.name(), sportlehrer);
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

    public Riege getOrCreateDefaultRiegeForSportklasse(String sportklasseName){
        Sportklasse sportklasse = sportklasseService.getSportklasse(sportklasseName);
        return getOrCreateDefaultRiegeForSportklasse(sportklasse);
    }
}
