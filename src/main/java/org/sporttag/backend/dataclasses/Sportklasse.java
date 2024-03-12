package org.sporttag.backend.dataclasses;

import jakarta.persistence.*;

@Entity
@Table(name = "sportklasse")
public class Sportklasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String klassenname;
    @ManyToOne
    private Sportlehrer sportlehrer;

    private Sportklasse(Long id, String klassenname, Sportlehrer sportlehrer) {
        this.id = id;
        this.klassenname = klassenname;
        this.sportlehrer = sportlehrer;
    }

    public Sportklasse(String klassenname, Sportlehrer sportlehrer) {
        this.klassenname = klassenname;
        this.sportlehrer = sportlehrer;
    }

    public Sportklasse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKlassenname() {
        return klassenname;
    }

    public Sportlehrer getSportlehrer() {
        return sportlehrer;
    }

    public void setSportlehrer(Sportlehrer sportlehrer) {
        this.sportlehrer = sportlehrer;
    }

    public void setKlassenname(String klassenname) {
        this.klassenname = klassenname;
    }
}
