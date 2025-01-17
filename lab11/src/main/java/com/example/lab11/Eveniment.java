package com.example.lab11;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Eveniment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String denumire;
    private String locatie;
    private LocalDate data;
    private LocalTime timp;
    private Double pretBilet;
    public Eveniment() {
    }
    public Eveniment(String denumire, String locatie, LocalDate data, LocalTime timp, Double pretBilet) {
        this.denumire = denumire;
        this.locatie = locatie;
        this.data = data;
        this.timp = timp;
        this.pretBilet = pretBilet;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getTimp() {
        return timp;
    }

    public void setTimp(LocalTime timp) {
        this.timp = timp;
    }

    public Double getPretBilet() {
        return pretBilet;
    }

    public void setPretBilet(Double pretBilet) {
        this.pretBilet = pretBilet;
    }
}

