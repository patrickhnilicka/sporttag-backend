package org.sporttag.backend.services;

import org.sporttag.backend.dto.StudentDto;
import org.sporttag.backend.entities.Student;
import org.sporttag.backend.repositories.StudentRepository;
import org.sporttag.backend.viewmodels.StudentViewModel;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {
    private StudentRepository db;

    public StudentService(StudentRepository db) {
        this.db = db;
    }

    public StudentViewModel getStudentById(Long id) {
        Student student = db.findById(id).orElse(new Student()); // was, falls
        return studentToStudentViewModel(student);

    }

    public Optional<Student> getStudent(Long id) {
        return db.findById(id);
    }

    public List<StudentViewModel> getStudents(Long sporttagId) {
        List<Student> students = db.getAllBySporttagId(sporttagId);
        return students.stream().map(s -> studentToStudentViewModel(s)).toList();
    }

    public Long saveStudent(Student student) {
        return db.save(student).getId();
    }

    public Long saveStudent(StudentDto studentDto) {
        Student student = db.findById(studentDto.id()).get();
        student.setVorname(studentDto.vorname());
        student.setNachname(studentDto.nachname());
        student.setGeschlecht(studentDto.geschlecht());
        student.setGeburtsdatum(studentDto.geburtstag());
        student.setKlasse(studentDto.klasse());
        student.setSportklasseId(studentDto.sportklasseId());
        db.save(student);
        return student.getId();
    }

    public void deleteStudent(Student student) {
        db.delete(student);
    }

    public Student createStudent(StudentDto studentDto, Long sporttagId) {
        Student student = new Student(studentDto.vorname(), studentDto.nachname(), studentDto.geschlecht(), studentDto.geburtstag(), studentDto.klasse(), studentDto.sportklasseId());
        db.save(student);
        return student;
    }

    private StudentViewModel studentToStudentViewModel(Student student) {
        String sportlehrerKuerzel = student.getSportklasse().getSportlehrer().getKuerzel(); // falls Student nicht in db
        String sportklassenName = student.getSportklasse().getKlassenname();
        Long sportklassenId = student.getSportklasse().getId();
        return new StudentViewModel(student.getId(), student.getVorname(), student.getNachname(), student.getGeschlecht(),
                student.getGeburtsdatum(), student.getKlasse(), sportklassenName,
                sportklassenId, sportlehrerKuerzel);
    }
}
