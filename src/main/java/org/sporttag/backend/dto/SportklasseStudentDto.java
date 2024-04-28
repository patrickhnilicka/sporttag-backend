package org.sporttag.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SportklasseStudentDto(String klassenname, String sportlehrerkuerzel, List<StudentDto> students) {
}
