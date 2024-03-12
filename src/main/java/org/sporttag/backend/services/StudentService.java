package org.sporttag.backend.services;

import org.sporttag.backend.dataclasses.Riege;
import org.sporttag.backend.dataclasses.Student;
import org.sporttag.backend.dto.StudentDto;
import org.sporttag.backend.repositories.StudentRepository;
import org.sporttag.backend.viewmodels.StudentViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository db;

    private RiegeService riegeService;

    private RiegeSportklassenService riegeSportklassenService;
    public StudentService(StudentRepository db, RiegeService riegeService, RiegeSportklassenService riegeSportklassenService) {
        this.db = db;
        this.riegeService = riegeService;
        this.riegeSportklassenService = riegeSportklassenService;
    }

    public StudentViewModel getStudentById(Long id) {
        Student student = db.findById(id).orElse(new Student()); // was, falls
        return studentToStudentViewModel(student);

    }

    public Optional<Student> getStudent(Long id) {
        return db.findById(id);
    }

    public List<StudentViewModel> getStudents() {
        List<Student> students = db.findAll();
        return students.stream().map(s -> studentToStudentViewModel(s)).toList();
    }

    public Long saveStudent(Student student) {
        return db.save(student).getId();
    }

    public void deleteStudent(Student student) {
        db.delete(student);
    }

    public Student createStudent(StudentDto studentDto){
        Riege riege = riegeSportklassenService.getOrCreateDefaultRiegeForSportklasse(studentDto.sportklasse());
        Student student = new Student(studentDto.vorname(), studentDto.nachname(), studentDto.geschlecht(), studentDto.geburtstag(), studentDto.klasse(), riege);
        db.save(student);
        return student;
    }

    private StudentViewModel studentToStudentViewModel(Student student){
        String sportlehrerKuerzel = student.getRiege() == null? "": student.getRiege().getSportklassen().getSportlehrer().getKuerzel(); // falls Student nicht in db
        String sportklassenName = student.getRiege() == null? "": student.getRiege().getSportklassen().getKlassenname();
        Long sportklassenId = student.getRiege() == null? 0L : student.getRiege().getSportklassen().getId();
        return new StudentViewModel(student.getId(), student.getVorname(), student.getNachname(), student.getGeschlecht(),
                student.getGeburtsdatum(), student.getKlasse(), sportklassenName,
                sportklassenId, sportlehrerKuerzel);
    }
}
