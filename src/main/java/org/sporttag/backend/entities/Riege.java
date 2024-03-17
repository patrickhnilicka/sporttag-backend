package org.sporttag.backend.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "riege")
public class Riege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nummer;

    private boolean isdefault;

    @ManyToOne
    private Sportklasse sportklassen;

    private Riege(Long id, int nummer, boolean isdefault, Sportklasse sportklassen) {
        this.id = id;
        this.nummer = nummer;
        this.isdefault = isdefault;
        this.sportklassen = sportklassen;
    }

    public Riege(int nummer, boolean isdefault, Sportklasse sportklassen) {
        this.nummer = nummer;
        this.isdefault = isdefault;
        this.sportklassen = sportklassen;
    }

    public Riege(){}

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
