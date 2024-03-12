package org.sporttag.backend;

import org.sporttag.backend.dataclasses.Sportklasse;
import org.sporttag.backend.dataclasses.Student;
import org.sporttag.backend.dto.ExcelStudentDataDto;
import org.sporttag.backend.services.DocumentService;
import org.sporttag.backend.services.SportklasseService;
import org.sporttag.backend.services.StudentService;
import org.sporttag.backend.viewmodels.StudentViewModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MessageController {

    private StudentService studentService;
    private RestTemplateBuilder restTemplateBuilder;
    private DocumentService documentService;
    private SportklasseService sportklasseService;

    public MessageController(StudentService studentService, RestTemplateBuilder restTemplateBuilder, DocumentService documentService, SportklasseService sportklasseService) {
        this.studentService = studentService;
        this.restTemplateBuilder = restTemplateBuilder;
        this.documentService = documentService;
        this.sportklasseService = sportklasseService;
    }

    @GetMapping("student")
    List<StudentViewModel> getStudents(){
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

    @PostMapping(value = "/students")
    public String uploadStudents(@RequestParam("file") MultipartFile file) throws Exception {
        byte[] fileContent = file.getBytes();
        String filename = file.getOriginalFilename();

        ExcelStudentDataDto excelStudentDataDtos = documentService.getStudentsFromExcel(fileContent, filename);
        return "";
    }

    @GetMapping("/sportklasse/{name}")
    public Sportklasse getSportklasse(@PathVariable String name) {
        return sportklasseService.getSportklasse(name);
    }
}