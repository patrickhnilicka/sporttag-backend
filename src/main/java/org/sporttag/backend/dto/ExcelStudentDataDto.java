package org.sporttag.backend.dto;

import java.sql.Date;
import java.util.List;

public record ExcelStudentDataDto(String vorname, String nachname, String geschlecht, String klasse,
                                  Date geburtstag, String sportklasse, String sportlehrerkuerzel) {
}
