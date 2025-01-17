package com.example.lab10.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carti")
public class Carte {
    @Id
    private String isbn;
    private String titlu;
    private String autor;

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitlu() { return titlu; }
    public void setTitlu(String titlu) { this.titlu = titlu; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
}

