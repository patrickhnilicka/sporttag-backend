package org.sporttag.backend.dto;

import java.sql.Date;

public record StudentDto(Long id, String vorname, String nachname, String geschlecht, String klasse,
                         Date geburtstag, Long sportklasseId, Long riegeId) { }
