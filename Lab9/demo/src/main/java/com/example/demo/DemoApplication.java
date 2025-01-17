package com.example.demo;

import com.example.demo.entity.Masina;
import com.example.demo.service.MasinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private MasinaService masinaService;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		masinaService.afiseazaMasini();
		masinaService.numarMasiniMarca("BMW");
		masinaService.numarMasiniSub100k();
		masinaService.masiniMaiNoiDe5Ani();

		Masina masinaNoua = new Masina();
		masinaNoua.setNumarInmatriculare("B678MNO");
		masinaNoua.setMarca("Audi");
		masinaNoua.setAnFabricatie(2022);
		masinaNoua.setCuloare("Negru");
		masinaNoua.setKm(30000);
		masinaService.adaugaMasina(masinaNoua);

		masinaService.stergeMasina("B345DEF");

		masinaService.cautaMasina("B234ABC");
	}
}

