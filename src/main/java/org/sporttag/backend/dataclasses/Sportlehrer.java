package org.sporttag.backend.dataclasses;

import jakarta.persistence.*;

@Entity
@Table(name = "sportlehrer")
public class Sportlehrer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vorname;
    private String nachname;
    private String kuerzel;

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

    public String getKuerzel() {
        return kuerzel;
    }

    public void setKuerzel(String kuerzel) {
        this.kuerzel = kuerzel;
    }

    private Sportlehrer(Long id, String vorname, String nachname, String kuerzel) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.kuerzel = kuerzel;
    }

    public Sportlehrer(String vorname, String nachname, String kuerzel) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.kuerzel = kuerzel;
    }

    public Sportlehrer() {
    }
}
