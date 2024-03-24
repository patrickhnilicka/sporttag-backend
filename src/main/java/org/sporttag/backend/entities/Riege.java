package org.sporttag.backend.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "riege")
public class Riege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nummer;

    private Riege(Long id, int nummer) {
        this.id = id;
        this.nummer = nummer;
    }

    public Riege(int nummer) {
        this.nummer = nummer;
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
}
