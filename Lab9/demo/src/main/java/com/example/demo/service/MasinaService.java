package com.example.demo.service;

import com.example.demo.entity.Masina;
import com.example.demo.repository.MasinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class MasinaService {

    private static final Logger logger = LoggerFactory.getLogger(MasinaService.class);

    @Autowired
    private MasinaRepository masinaRepository;

    public void adaugaMasina(Masina masina) {
        masinaRepository.save(masina);
        logger.info("Masina adăugată: {}", masina);
    }

    public void stergeMasina(String numarInmatriculare) {
        masinaRepository.deleteByNumarInmatriculare(numarInmatriculare);
        logger.info("Masina cu numărul de înmatriculare {} a fost ștearsă", numarInmatriculare);
    }

    public void cautaMasina(String numarInmatriculare) {
        Masina masina = masinaRepository.findByNumarInmatriculare(numarInmatriculare);
        logger.info("Masina găsită: {}", masina);
    }

    public void afiseazaMasini() {
        List<Masina> masini = masinaRepository.findAll();
        logger.info("Toate mașinile: ");
        masini.forEach(masina -> logger.info(masina.toString()));
    }

    public void numarMasiniMarca(String marca) {
        int count = masinaRepository.countByMarca(marca);
        logger.info("Numărul de mașini cu marca {}: {}", marca, count);
    }

    public void numarMasiniSub100k() {
        int count = masinaRepository.countByKmUnder100k();
        logger.info("Numărul de mașini cu sub 100.000 km: {}", count);
    }

    public void masiniMaiNoiDe5Ani() {
        List<Masina> masini = masinaRepository.findMasiniMaiNoiDe5Ani();
        logger.info("Mașinile mai noi de 5 ani: ");
        masini.forEach(masina -> logger.info(masina.toString()));
    }
}

