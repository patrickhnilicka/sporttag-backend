package org.sporttag.backend.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.sporttag.backend.dto.ExcelStudentDataDto;
import org.sporttag.backend.dto.SportklasseDto;
import org.sporttag.backend.dto.SportklasseStudentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Value("${documentservice.url}")
    public String URL;
    private RestTemplateBuilder restTemplateBuilder;

    public DocumentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public List<ExcelStudentDataDto> getStudentsFromExcel(byte[] file, String filename) {
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
        ExcelStudent[] excelStudents = restTemplate.postForObject(URL + "/get-students", requestEntity, ExcelStudent[].class);
        return toExcelDataDto(Arrays.stream(excelStudents).toList());
    }

    public byte[] getZipWithRiegenExcels(List<SportklasseStudentDto> sportklassen) throws JsonProcessingException {
        Map<String, SportklasseStudentDto> sportklassenByName = sportklassen.stream().collect(Collectors.toMap(SportklasseStudentDto::klassenname, Function.identity()));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper mapper = new ObjectMapper();
        String json;
        json = mapper.writeValueAsString(sportklassenByName);
        HttpEntity<String> request =
                new HttpEntity<String>(json, headers);
        ResponseEntity<byte[]> responseEntity = restTemplate.
                postForEntity(URL + "/sportlehrerexcel", request, byte[].class);
        return responseEntity.getBody();
    }

    public String getHello(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String message = restTemplate.getForObject(URL + "/hello", String.class);
        return message;
    }

    private List<ExcelStudentDataDto> toExcelDataDto(List<ExcelStudent> excelStudents) {
        return excelStudents.stream().map(es -> new ExcelStudentDataDto(es.vorname(), es.nachname(), es.gender(),
                es.klasseBuchstabe() + es.klasseBuchstabe(),es.geburtstag(), es.sportklasse(), es.lehrpersonKuerzel())).toList();
    }

    private SportklasseDto toSportklasseDto(ExcelStudent excelStudent) {
        return new SportklasseDto(excelStudent.sportklasse(), excelStudent.lehrpersonKuerzel());
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
record ExcelStudent(String gender, String vorname, String nachname, int klasseZahl, String klasseBuchstabe,
                    Date geburtstag, String sportklasse, String lehrpersonKuerzel) {
}
