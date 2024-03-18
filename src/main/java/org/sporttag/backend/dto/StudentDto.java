package org.sporttag.backend.dto;

import java.sql.Date;

public record StudentDto(String vorname, String nachname, String geschlecht, String klasse,
                         Date geburtstag, String sportklasse) { }
