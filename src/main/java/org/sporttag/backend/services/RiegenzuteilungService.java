package org.sporttag.backend.services;

import org.sporttag.backend.dto.RiegeDto;
import org.sporttag.backend.dto.RiegenzuteilungDto;
import org.sporttag.backend.dto.StudentDto;
import org.sporttag.backend.viewmodels.RiegenzuteilungViewModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RiegenzuteilungService {
    private RiegeService riegeService;
    private StudentService studentService;
    private SportklasseService sportklasseService;

    public RiegenzuteilungService(RiegeService riegeService, StudentService studentService, SportklasseService sportklasseService) {
        this.riegeService = riegeService;
        this.studentService = studentService;
        this.sportklasseService = sportklasseService;
    }

    public List<RiegenzuteilungViewModel> getRiegenzuteilungen(Long sporttagId) {
        List<StudentDto> students = studentService.getStudents(sporttagId);
        Set<Long> sportklassenIds = students.stream().map(s -> s.sportklasseId()).collect(Collectors.toSet());
        Map<Long, String> sportklassenByIds = sportklasseService.findAllById(sportklassenIds);

        Map<Long, Integer> riegesById = riegeService.getRieges(sporttagId).stream().collect(Collectors.toMap(RiegeDto::id, RiegeDto::name));

        List<RiegenzuteilungViewModel> riegenzuteilungViewModels = students.stream().map(
                s -> new RiegenzuteilungViewModel(s.id(), s.riegeId(), s.vorname() + " " + s.nachname(),
                        sportklassenByIds.get(s.sportklasseId()), riegesById.get(s.riegeId()))).toList();
        return riegenzuteilungViewModels;
    }

    public void saveRiegenzuteilung(RiegenzuteilungDto riegenzuteilung){
        studentService.saveRiegeOnStudent(riegenzuteilung.studentId(), riegenzuteilung.riegeId());
    }
}
