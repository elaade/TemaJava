package com.example.lab11;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EvenimentService {
    private final EvenimentRepository repository;

    public EvenimentService(EvenimentRepository repository) {
        this.repository = repository;
    }

    public List<Eveniment> getAllEvenimente() {
        return repository.findAll();
    }

    public List<Eveniment> getEvenimenteByLocatie(String locatie) {
        return repository.findByLocatie(locatie);
    }

    public List<Eveniment> getEvenimenteByData(LocalDate data) {
        return repository.findByData(data);
    }

    public Eveniment addEveniment(Eveniment eveniment) {
        return repository.save(eveniment);
    }

    public Eveniment updateEveniment(Long id, Eveniment updatedEveniment) {
        return repository.findById(id).map(eveniment -> {
            eveniment.setDenumire(updatedEveniment.getDenumire());
            eveniment.setLocatie(updatedEveniment.getLocatie());
            eveniment.setData(updatedEveniment.getData());
            eveniment.setTimp(updatedEveniment.getTimp());
            eveniment.setPretBilet(updatedEveniment.getPretBilet());
            return repository.save(eveniment);
        }).orElseThrow(() -> new RuntimeException("Evenimentul nu a fost găsit!"));
    }

    public void deleteEveniment(Long id) {
        repository.deleteById(id);
    }
}

