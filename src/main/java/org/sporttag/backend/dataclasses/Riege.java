package org.sporttag.backend.dataclasses;


import jakarta.persistence.*;

@Entity

@Table(name = "riege")

public class Riege {
    @Id
    private Long id;
    private int nummer;

    @ManyToOne
    private Sportklasse sportklassen;

    public Riege(Long id, int nummer, Sportklasse sportklassen) {
        this.id = id;
        this.nummer = nummer;
        this.sportklassen = sportklassen;
    }

    public Riege() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public Sportklasse getSportklassen() {
        return sportklassen;
    }

    public void setSportklassen(Sportklasse sportklasse) {
        this.sportklassen = sportklasse;
    }
}
