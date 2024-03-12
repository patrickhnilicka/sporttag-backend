package org.sporttag.backend.services;

import org.sporttag.backend.dataclasses.Student;
import org.sporttag.backend.repositories.StudentRepository;
import org.sporttag.backend.viewmodels.StudentViewModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private StudentRepository db;

    public StudentService(StudentRepository db) {
        this.db = db;
    }

    public StudentViewModel getStudentViewModel(Long id) {
        Student student = db.findById(id).orElse(new Student());
        StudentViewModel studentViewModel = new StudentViewModel(student.getId(), student.getVorname(), student.getNachname(), student.getGeschlecht(),
                student.getGeburtsdatum(), student.getKlasse(), student.getRiege().getSportklassen().getKlassenname(),
                student.getRiege().getSportklassen().getId(), student.getRiege().getSportklassen().getSportlehrer().getKuerzel());
        return studentViewModel;

    }

    public Optional<Student> getStudent(Long id) {
        return db.findById(id);
    }

    public List<StudentViewModel> getStudents() {
        List<Student> students = db.findAll();
        List<StudentViewModel> studentViewModels = new ArrayList<>();
        students.forEach(student -> {
            studentViewModels.add(getStudentViewModel(student.getId()));
        });
        return studentViewModels;
    }

    public Long saveStudent(Student student) {
        return db.save(student).getId();
    }

    public void deleteStudent(Student student) {
        db.delete(student);
    }
}
