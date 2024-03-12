package org.sporttag.backend.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.sporttag.backend.dto.ExcelStudentDataDto;
import org.sporttag.backend.dto.SportklasseDto;
import org.sporttag.backend.dto.StudentDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Service
public class DocumentService {

    public static final String URL = "http://localhost:8081/api/v1/get-students";
    private RestTemplateBuilder restTemplateBuilder;

    public DocumentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public ExcelStudentDataDto getStudentsFromExcel(byte[] file, String filename) {
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(filename)
                .build();

        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(file, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = restTemplateBuilder.build();
        ExcelStudent[] excelStudents = restTemplate.postForObject(URL, requestEntity, ExcelStudent[].class);
        return toExcelDataDto(Arrays.stream(excelStudents).toList());
    }

    private ExcelStudentDataDto toExcelDataDto(List<ExcelStudent> excelStudents) {
        List<StudentDto> studentDtos = excelStudents.stream().map(s -> toStudentDto(s)).toList();
        List<SportklasseDto> sportklasseDtos = excelStudents.stream().map(s -> toSportklasseDto(s)).toList();

        return new ExcelStudentDataDto(studentDtos, sportklasseDtos);
    }

    private StudentDto toStudentDto(ExcelStudent excelStudent) {
        return new StudentDto(excelStudent.vorname(), excelStudent.nachname(), excelStudent.gender(),
                excelStudent.klasseZahl() + excelStudent.klasseBuchstabe(), excelStudent.geburtstag(), excelStudent.sportklasse());
    }

    private SportklasseDto toSportklasseDto(ExcelStudent excelStudent) {
        return new SportklasseDto(excelStudent.sportklasse(), excelStudent.lehrpersonKuerzel());
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
record ExcelStudent(String gender, String vorname, String nachname, int klasseZahl, String klasseBuchstabe,
                    Date geburtstag, String sportklasse, String lehrpersonKuerzel) {
}
