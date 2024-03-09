package org.sporttag.backend.viewmodels;

import java.sql.Date;

public class StudentViewModel {
    private String vorname;
    private String nachname;
    private String geschlecht;
    private Date geburtsdatum;
    private String klasse;
    private String sportklasse;
    private Long sportklassenId;
    private String sportlehrerKuerzel;

    public StudentViewModel() {
    }

    public StudentViewModel(String vorname, String nachname, String geschlecht, Date geburtsdatum, String klasse, String sportklasse, Long sportklassenId, String sportlehrerKuerzel) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.geburtsdatum = geburtsdatum;
        this.klasse = klasse;
        this.sportklasse = sportklasse;
        this.sportklassenId = sportklassenId;
        this.sportlehrerKuerzel = sportlehrerKuerzel;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public Date getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(Date geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getKlasse() {
        return klasse;
    }

    public void setKlasse(String klasse) {
        this.klasse = klasse;
    }

    public String getSportklasse() {
        return sportklasse;
    }

    public void setSportklasse(String sportklasse) {
        this.sportklasse = sportklasse;
    }

    public Long getSportklassenId() {
        return sportklassenId;
    }

    public void setSportklassenId(Long sportklassenId) {
        this.sportklassenId = sportklassenId;
    }

    public String getSportlehrerKuerzel() {
        return sportlehrerKuerzel;
    }

    public void setSportlehrerKuerzel(String sportlehrerKuerzel) {
        this.sportlehrerKuerzel = sportlehrerKuerzel;
    }
}
