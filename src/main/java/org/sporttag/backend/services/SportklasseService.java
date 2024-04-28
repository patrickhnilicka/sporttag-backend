package org.sporttag.backend.services;

import org.sporttag.backend.dto.SportklasseStudentDto;
import org.sporttag.backend.dto.StudentDto;
import org.sporttag.backend.entities.Sportklasse;
import org.sporttag.backend.entities.Sportlehrer;
import org.sporttag.backend.repositories.SportklasseRepository;
import org.sporttag.backend.viewmodels.SportklasseViewModel;
import org.sporttag.backend.viewmodels.SportlehrerViewModel;
import org.springframework.stereotype.Service;

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

    public Long getSportklasse(String name, Long sporttagId){
        return db.findSportklasseByName(name, sporttagId).getId();
    }

    public void saveSportklasse(Sportklasse sportklasse){
        db.save(sportklasse);
    }

    public Long saveNewSportklasseForNameAndSportlehrerId(String name, Long sportlehrerId, Long sporttagId){
        Sportklasse sportklasse = new Sportklasse(name, sportlehrerId,  sporttagId);
        db.save(sportklasse);
        return sportklasse.getId();
    }

    public Long findSportklasseByName(String name, Long sporttagId){
        Sportklasse sportklasse = db.findSportklasseByName(name, sporttagId);
        if(sportklasse == null){
            return null;
        }
        return sportklasse.getId();
    }
    public List<SportklasseViewModel> findAllBySporttag(Long sporttagId){ return db.findAllBySporttag(sporttagId).stream().map(s -> toSportklasseViewModel(s)).toList();}

    public List<SportklasseStudentDto> findAllWithStudentsBySporttag(Long sporttagId){
        return db.findAllBySporttag(sporttagId).stream().map(s -> new SportklasseStudentDto(s.getKlassenname(),
                s.getSportlehrer().getKuerzel(), s.getStudents().stream().map(st ->
                new StudentDto(st.getId(), st.getVorname(), st.getNachname(), st.getGeschlecht(), st.getKlasse(),
                        st.getGeburtsdatum(), st.getSportklasseId())).toList())).toList();
    }
    private SportklasseViewModel toSportklasseViewModel(Sportklasse sportklasse){
        return new SportklasseViewModel(sportklasse.getId(), sportklasse.getKlassenname(), toSportlehrerViewModel(sportklasse.getSportlehrer()));
    }

    private SportlehrerViewModel toSportlehrerViewModel(Sportlehrer sportlehrer){
        return new SportlehrerViewModel(sportlehrer.getVorname(), sportlehrer.getNachname(), sportlehrer.getKuerzel());
    }

}
