package org.example;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public record Carte(String titlul, String autorul, int anul) {}

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, Carte> colectieCarti = new HashMap<>();
        try {
            colectieCarti = objectMapper.readValue(
                            new File("src/main/resources/carti.json"),
                            new TypeReference<Map<String, Carte>>() {}
                    ).entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> Integer.parseInt(entry.getKey()),
                            Map.Entry::getValue
                    ));
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        boolean ruleaza = true;

        while (ruleaza) {
            System.out.println("\n*** Meniu Gestionare Cărți ***");
            System.out.println("1. Afișează colecția de cărți");
            System.out.println("2. Șterge o carte");
            System.out.println("3. Adaugă o carte");
            System.out.println("4. Salvează modificările în fișier");
            System.out.println("5. Cărțile autorului Yuval Noah Harari");
            System.out.println("6. Ordonează cărțile după titlu");
            System.out.println("7. Afișează cea mai veche carte");
            System.out.println("0. Ieși");
            System.out.print("Alegeți o opțiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    System.out.println("\nColecția de cărți:");
                    colectieCarti.forEach((id, carte) -> System.out.println("ID: " + id + " -> " + carte));
                    break;

                case 2:
                    System.out.print("Introduceți ID-ul cărții de șters: ");
                    int idDeSters = scanner.nextInt();
                    colectieCarti.remove(idDeSters);
                    System.out.println("Cartea cu ID-ul " + idDeSters + " a fost ștearsă.");
                    break;

                case 3:
                    System.out.print("Introduceți titlul cărții: ");
                    String titlu = scanner.nextLine();
                    System.out.print("Introduceți autorul cărții: ");
                    String autor = scanner.nextLine();
                    System.out.print("Introduceți anul apariției: ");
                    int anAparitie = scanner.nextInt();

                    Carte carteNoua = new Carte(titlu, autor, anAparitie);
                    System.out.print("Introduceți ID-ul pentru cartea nouă: ");
                    int idNou = scanner.nextInt();
                    colectieCarti.putIfAbsent(idNou, carteNoua);
                    System.out.println("Cartea a fost adăugată cu ID-ul " + idNou);
                    break;

                case 4:
                    try {
                        objectMapper.writeValue(new File("carti.json"), colectieCarti);
                        System.out.println("Modificările au fost salvate în fișier.");
                    } catch (IOException e) {
                        System.err.println("Eroare la salvarea fișierului: " + e.getMessage());
                    }
                    break;

                case 5:
                    Set<Carte> cartiAutor = colectieCarti.values().stream()
                            .filter(carte -> "Yuval Noah Harari".equals(carte.autorul()))
                            .collect(Collectors.toSet());

                    System.out.println("\nCărțile autorului Yuval Noah Harari:");
                    cartiAutor.forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("\nCărțile ordonate după titlu:");
                    colectieCarti.values().stream()
                            .sorted(Comparator.comparing(Carte::titlul))
                            .forEach(System.out::println);
                    break;

                case 7:
                    Optional<Carte> ceaMaiVecheCarte = colectieCarti.values().stream()
                            .min(Comparator.comparingInt(Carte::anul));

                    ceaMaiVecheCarte.ifPresent(carte ->
                            System.out.println("\nCea mai veche carte: " + carte));
                    break;

                case 0:
                    System.out.println("Ieșire din aplicație...");
                    ruleaza = false;
                    break;

                default:
                    System.out.println("Opțiune invalidă! Vă rugăm să alegeți o opțiune validă.");
            }
        }

        scanner.close();
    }
}