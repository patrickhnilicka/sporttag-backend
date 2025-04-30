package org.sporttag.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sporttag.backend.dto.*;
import org.sporttag.backend.entities.Sporttag;
import org.sporttag.backend.entities.Student;
import org.sporttag.backend.services.*;
import org.sporttag.backend.viewmodels.RiegenzuteilungViewModel;
import org.sporttag.backend.viewmodels.SportklasseViewModel;
import org.sporttag.backend.viewmodels.SportlehrerViewModel;
import org.sporttag.backend.viewmodels.StudentViewModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);


    private StudentService studentService;
    private RestTemplateBuilder restTemplateBuilder;
    private DocumentService documentService;
    private SportklasseService sportklasseService;
    private SporttagService sporttagService;
    private ExcelStudentService excelStudentService;
    private RiegenzuteilungService riegenzuteilungService;
    private RiegeService riegeService;
    private SportlehrerService sportlehrerService;

    public MessageController(StudentService studentService, RestTemplateBuilder restTemplateBuilder, DocumentService documentService, SportklasseService sportklasseService, SporttagService sporttagService, ExcelStudentService excelStudentService, RiegenzuteilungService riegenzuteilungService, RiegeService riegeService, SportlehrerService sportlehrerService) {
        this.studentService = studentService;
        this.restTemplateBuilder = restTemplateBuilder;
        this.documentService = documentService;
        this.sportklasseService = sportklasseService;
        this.sporttagService = sporttagService;
        this.excelStudentService = excelStudentService;
        this.riegenzuteilungService = riegenzuteilungService;
        this.riegeService = riegeService;
        this.sportlehrerService = sportlehrerService;
    }

    @GetMapping("/students/{sporttagId}")
    List<StudentViewModel> getStudents(@PathVariable Long sporttagId) {
        return studentService.getStudentViewmodels(sporttagId);
    }

    @GetMapping("/student/{id}")
    public StudentViewModel getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/student")
    public void saveStudent(@RequestBody StudentViewModel student) {
        StudentDto studentDto = new StudentDto(student.getId(), student.getVorname(),
                student.getNachname(), student.getGeschlecht(), student.getKlasse(),
                student.getGeburtsdatum(), student.getSportklassenId(), null);
        studentService.saveStudent(studentDto);
    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(Student student) {
        studentService.deleteStudent(student);
    }

    @PostMapping(value = "/studentsFromExcel")
    public String uploadStudentsFromExcel(@RequestParam("file") MultipartFile file, @RequestParam("sporttagid") String sporttagId) throws Exception {
        byte[] fileContent = file.getBytes();
        String filename = file.getOriginalFilename();

        List<ExcelStudentDataDto> excelStudentDataDtos = documentService.getStudentsFromExcel(fileContent, filename);
        excelStudentDataDtos.forEach(s -> excelStudentService.createStudent(s, Long.parseLong(sporttagId)));
        return "Imported " + excelStudentDataDtos.size() + " records";
    }

    @GetMapping("/sportklasse")
    public SportklasseViewModel getSportklasse(@RequestParam Map<String, String> params) {
        String name = params.get("name");
        Long sporttagId = Long.parseLong(params.get("sporttagid"));
        return sportklasseService.getSportklasseViewModel(name, sporttagId);
    }

    @GetMapping("/sporttag")
    public List<Sporttag> getSporttage() {
        return sporttagService.getAllSporttage();
    }

    @GetMapping("/riege/{sporttagId}")
    List<RiegeDto> getRiegen(@PathVariable Long sporttagId) {
        return riegeService.getRieges(sporttagId);
    }

    @GetMapping("/currentsporttag")
    public Sporttag getCurrentSporttag() {
        return sporttagService.getAllSporttage().getLast();
    }

    @GetMapping("/sportklassen/{sporttagId}")
    public List<SportklasseViewModel> getSportklassen(@PathVariable Long sporttagId) {
        return sportklasseService.findAllBySporttag(sporttagId);
    }

    @GetMapping(value = "/riegenExcel/{sporttagId}", produces = "application/zip")
    public @ResponseBody byte[] getRiegenExcel(@PathVariable Long sporttagId) throws Exception {
        List<SportklasseStudentDto> sportklasseStudentDtos = sportklasseService.findAllWithStudentsBySporttag(sporttagId);
        return documentService.getZipWithRiegenExcels(sportklasseStudentDtos);
    }

    @GetMapping("/riegenzuteilung/{sporttagId}")
    public List<RiegenzuteilungViewModel> getRiegenzuteilung(@PathVariable Long sporttagId) {
        return riegenzuteilungService.getRiegenzuteilungen(sporttagId);
    }

    @PostMapping(value="/riegenzuteilung")
    public void saveRiegenzuteilung(@RequestBody RiegenzuteilungDto riegenzuteilung){
        riegenzuteilungService.saveRiegenzuteilung(riegenzuteilung);
    }
    @PostMapping(value = "/riegenZuteilungFromExcel")
    public String uploadRiegenzuteilungFromExcel(@RequestParam("file") MultipartFile file, @RequestParam("sporttagid") String sporttagIdString) throws Exception {
        byte[] fileContent = file.getBytes();
        String filename = file.getOriginalFilename();
        Long sporttagId = Long.parseLong(sporttagIdString);

        List<RiegenzuteilungDto> riegenzuteilungDtos = documentService.getRiegenzuteilungFromExcel(fileContent, filename)
                .stream()
                .map(z -> new RiegenzuteilungDto(z.studentId(), z.riegeId(), sporttagId))
                .toList();
        riegenzuteilungDtos.forEach(s -> riegenzuteilungService.saveRiegenzuteilung(s));
        return "Imported " + riegenzuteilungDtos.size() + " records";
    }

    @GetMapping("/hellodocumentservice")
    public String helloDocumentService() {
        return documentService.getHello();
    }
    @GetMapping("/sportlehrer")
    List<SportlehrerViewModel> getSportlehrers() {
        return sportlehrerService.getAll();
    }
    @PostMapping("/sportlehrer")
    public void saveSportlehrer(@RequestBody SportlehrerViewModel sportlehrer){
        sportlehrerService.saveSportlehrer(sportlehrer);
    }
}