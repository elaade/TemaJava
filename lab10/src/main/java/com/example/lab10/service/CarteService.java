package com.example.lab10.service;

import com.example.lab10.model.Carte;
import com.example.lab10.repository.CarteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteService {
    private final CarteRepository carteRepository;

    public CarteService(CarteRepository carteRepository) {
        this.carteRepository = carteRepository;
    }

    public List<Carte> findAll() {
        return carteRepository.findAll();
    }

    public Carte findByIsbn(String isbn) {
        return carteRepository.findById(isbn).orElse(null);
    }

    public List<Carte> findByAutor(String autor) {
        return carteRepository.findByAutor(autor);
    }

    public Carte save(Carte carte){
                return carteRepository.save(carte);
}

public void deleteByIsbn(String isbn) {
    carteRepository.deleteById(isbn);
}
}


