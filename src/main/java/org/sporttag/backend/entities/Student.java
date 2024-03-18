package org.sporttag.backend.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vorname;
    private String nachname;
    private String geschlecht;
    private Date geburtsdatum;
    private String klasse;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Riege riege;

    @Column(name="riege_id")
    private Long riegeId;

    public Student() {
    }

    public Student(String vorname, String nachname, String geschlecht, Date geburtsdatum, String klasse, Long riegeId) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
        this.geburtsdatum = geburtsdatum;
        this.klasse = klasse;
        this.riegeId = riegeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Riege getRiege() {
        return riege;
    }

    public void setRiege(Riege riege) {
        this.riege = riege;
    }
}