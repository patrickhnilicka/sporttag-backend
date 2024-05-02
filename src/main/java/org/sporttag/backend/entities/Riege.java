package org.sporttag.backend.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "riege")
public class Riege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nummer;
    @Column(name = "sporttag_id")
    private Long sporttagId;

    private Riege(Long id, int nummer, Long sporttagId) {
        this.id = id;
        this.nummer = nummer;
        this.sporttagId = sporttagId;
    }

    public Riege(int nummer, Long sporttagId) {
        this.nummer = nummer;
        this.sporttagId = sporttagId;
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

    public Long getSporttagId() {
        return sporttagId;
    }

    public void setSporttagId(Long sporttagId) {
        this.sporttagId = sporttagId;
    }
}
