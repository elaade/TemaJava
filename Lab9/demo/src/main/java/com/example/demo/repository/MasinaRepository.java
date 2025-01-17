package com.example.demo.repository;

import com.example.demo.entity.Masina;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MasinaRepository extends CrudRepository<Masina, Long> {


    Masina save(Masina masina);

    void deleteByNumarInmatriculare(String numarInmatriculare);

    Masina findByNumarInmatriculare(String numarInmatriculare);

    List<Masina> findAll();

    @Query("SELECT COUNT(*) FROM masini WHERE marca = :marca")
    int countByMarca(String marca);

    @Query("SELECT COUNT(*) FROM masini WHERE km < 100000")
    int countByKmUnder100k();

    @Query("SELECT * FROM masini WHERE an_fabricatie > YEAR(CURDATE()) - 5")
    List<Masina> findMasiniMaiNoiDe5Ani();
}

