package org.sporttag.backend;

import org.sporttag.backend.dataclasses.Student;
import org.sporttag.backend.repositories.StudentRepository;
import org.sporttag.backend.services.StudentService;
import org.sporttag.backend.viewmodels.StudentViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MessageController {

    private StudentService studentService;

    public MessageController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("student")
    List<Student> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/student/{id}")
    public StudentViewModel getStudent(@PathVariable Long id) {
        return studentService.getStudentViewModel(id);
    }

    @PostMapping("/student")
    public void saveStudent(@RequestBody Student student){
        studentService.saveStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(Student student){
        studentService.deleteStudent(student);
    }
}