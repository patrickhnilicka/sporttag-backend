package org.sporttag.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sportklasse")
public class Sportklasse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String klassenname;
    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Sportlehrer sportlehrer;

    @Column(name="sportlehrer_id")
    private Long sportlehrerId;

    public Sportklasse(String klassenname, Long sportlehrerId, Long sporttagId) {
        this.klassenname = klassenname;
        this.sportlehrerId = sportlehrerId;
        this.sporttagId = sporttagId;
    }

    @Column(name = "sporttag_id")
    private Long sporttagId;

    private Sportklasse(Long id, String klassenname, Sportlehrer sportlehrer, Long sportlehrerId, Long sporttagId) {
        this.id = id;
        this.klassenname = klassenname;
        this.sportlehrer = sportlehrer;
        this.sportlehrerId = sportlehrerId;
        this.sporttagId = sporttagId;
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

    public Long getSporttagId() {
        return sporttagId;
    }

    public void setSporttagId(Long sporttagId) {
        this.sporttagId = sporttagId;
    }
}
