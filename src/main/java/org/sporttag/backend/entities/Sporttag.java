package org.sporttag.backend.entities;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="sporttag")
public class Sporttag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date datum;

    public Sporttag(){

    }
    public Sporttag(Long id, Date datum, String bezeichnung) {
        this.id = id;
        this.datum = datum;
        this.bezeichnung = bezeichnung;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    private String bezeichnung;
}
