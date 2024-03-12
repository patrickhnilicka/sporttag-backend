package org.sporttag.backend.dto;

import java.util.List;

public record ExcelStudentDataDto(List<StudentDto> studentDtos, List<SportklasseDto> sportklasseDtos) {
}
