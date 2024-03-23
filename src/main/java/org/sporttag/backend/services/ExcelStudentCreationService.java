package org.sporttag.backend.services;

import org.sporttag.backend.dto.ExcelStudentDataDto;
import org.sporttag.backend.dto.SportklasseDto;
import org.sporttag.backend.dto.StudentDto;
import org.springframework.stereotype.Service;

@Service
public class ExcelStudentCreationService {
    private StudentService studentService;
    private RiegeService riegeService;
    private SportklasseService sportklasseService;
    private SportlehrerService sportlehrerService;

    public ExcelStudentCreationService(StudentService studentService, RiegeService riegeService,
                                       SportklasseService sportklasseService, SportlehrerService sportlehrerService) {
        this.studentService = studentService;
        this.riegeService = riegeService;
        this.sportklasseService = sportklasseService;
        this.sportlehrerService = sportlehrerService;
    }

    // Creates Student with default Riege, Sportklasse and Sportlehrer if not existent
    public Long createStudentWithRiege(ExcelStudentDataDto excelStudentDataDto, Long sporttagId) {
        SportklasseDto sportklasseDto = new SportklasseDto(excelStudentDataDto.sportklasse(),excelStudentDataDto.sportlehrerkuerzel());
        Long sportklasseId = getOrCreateSportklasse(sportklasseDto, sporttagId);

        Long riegeId = riegeService.findOrCreateDefaulRiegeForSportklasse(sportklasseId);
        return studentService.createStudentForRiege(new StudentDto(null, excelStudentDataDto.vorname(),
                excelStudentDataDto.nachname(),
                excelStudentDataDto.geschlecht(),
                excelStudentDataDto.klasse(),
                excelStudentDataDto.geburtstag(),
                riegeId), riegeId, sporttagId).getId();
    }
    private Long getOrCreateSportklasse(SportklasseDto sportklasseDto, Long sporttagId){
        Long sportklasseId = sportklasseService.findSportklasseByName(sportklasseDto.name(), sporttagId);
        if(sportklasseId != null){
            return sportklasseId;
        }
        Long sportlehrerId = sportlehrerService.getOrCreateSportlehrer(sportklasseDto.sportlehrerkuerzel());
        return sportklasseService.saveNewSportklasseForNameAndSportlehrerId(sportklasseDto.name(), sportlehrerId, sporttagId);
    }
}
