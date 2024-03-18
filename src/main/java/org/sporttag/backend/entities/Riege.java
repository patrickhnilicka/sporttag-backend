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
    @JoinColumn(insertable = false, updatable = false)
    private Sportklasse sportklassen;

    @Column(name = "sportklassen_id")
    private Long sportklassenId;

    private Riege(Long id, int nummer, boolean isdefault, Sportklasse sportklassen) {
        this.id = id;
        this.nummer = nummer;
        this.isdefault = isdefault;
        this.sportklassen = sportklassen;
    }

    public Riege(int nummer, boolean isdefault, Long sportklassenId) {
        this.nummer = nummer;
        this.isdefault = isdefault;
        this.sportklassenId = sportklassenId;
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
