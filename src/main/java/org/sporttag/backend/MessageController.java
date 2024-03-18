package org.sporttag.backend;

import org.sporttag.backend.entities.Sporttag;
import org.sporttag.backend.entities.Student;
import org.sporttag.backend.dto.ExcelStudentDataDto;
import org.sporttag.backend.services.*;
import org.sporttag.backend.viewmodels.SportklasseViewModel;
import org.sporttag.backend.viewmodels.StudentViewModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MessageController {

    private StudentService studentService;
    private RestTemplateBuilder restTemplateBuilder;
    private DocumentService documentService;
    private SportklasseService sportklasseService;
    private SporttagService sporttagService;
    private ExcelStudentCreationService excelStudentService;

    public MessageController(StudentService studentService, RestTemplateBuilder restTemplateBuilder, DocumentService documentService, SportklasseService sportklasseService, SporttagService sporttagService, ExcelStudentCreationService excelStudentService) {
        this.studentService = studentService;
        this.restTemplateBuilder = restTemplateBuilder;
        this.documentService = documentService;
        this.sportklasseService = sportklasseService;
        this.sporttagService = sporttagService;
        this.excelStudentService = excelStudentService;
    }

    @GetMapping("/students/{sporttagId}")
    List<StudentViewModel> getStudents(@PathVariable Long sporttagId){
        return studentService.getStudents(sporttagId);
    }

    @GetMapping("/student/{id}")
    public StudentViewModel getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/student")
    public void saveStudent(@RequestBody StudentViewModel student){
        studentService.saveStudent(student);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(Student student){
        studentService.deleteStudent(student);
    }

    @PostMapping(value = "/studentsFromExcel")
    public String uploadStudentsFromExcel(@RequestParam("file") MultipartFile file, @RequestParam("sporttagid") String sporttagId) throws Exception {
        byte[] fileContent = file.getBytes();
        String filename = file.getOriginalFilename();

        List<ExcelStudentDataDto> excelStudentDataDtos = documentService.getStudentsFromExcel(fileContent, filename);
        excelStudentDataDtos.forEach(s -> excelStudentService.createStudentWithRiege(s, Long.parseLong(sporttagId)));
        return "Imported " + excelStudentDataDtos.size() + " records";
    }

    @GetMapping("/sportklasse")
    public SportklasseViewModel getSportklasse(@RequestParam Map<String, String> params) {
        String name = params.get("name");
        Long sporttagId = Long.parseLong(params.get("sporttagid"));
        return sportklasseService.getSportklasseViewModel(name, sporttagId);
    }

    @GetMapping("/sporttag")
    public List<Sporttag> getSporttage(){
        return sporttagService.getAllSporttage();
    }

    @GetMapping("/currentsporttag")
    public Sporttag getCurrentSporttag(){ return sporttagService.getAllSporttage().getFirst();}

    @GetMapping("/sportklassen/{sporttagId}")
    public List<SportklasseViewModel> getSportklassen(@PathVariable Long sporttagId){return sportklasseService.findAllBySporttag(sporttagId);}
}