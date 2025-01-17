package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Angajat> angajati = new ArrayList<>();
        angajati.add(new Angajat("Ion Popescu", "Programator", LocalDate.of(2020, 5, 10), 7000.5f));
        angajati.add(new Angajat("Maria Ionescu", "Director", LocalDate.of(2024, 4, 15), 2000.0f));
        angajati.add(new Angajat("Andrei Dumitrescu", "Manager", LocalDate.of(2018, 7, 20), 1500.0f));
        angajati.add(new Angajat("Elena Vasilescu", "Tester", LocalDate.of(2022, 2, 5), 3500.0f));
        angajati.add(new Angajat("Cristian Georgescu", "Analist", LocalDate.of(2019, 11, 11), 5000.0f));
        File jsonFile = new File("src/main/resources/angajati.json");
        try {
            writeToJson(angajati, jsonFile);
            angajati = readFromJson(jsonFile);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        boolean ruleaza=true;
        Scanner scanner=new Scanner(System.in);
        while (ruleaza) {
            System.out.println("\nMeniu Interactiv:");
            System.out.println("1. Afișarea listei de angajați");
            System.out.println("2. Afișarea angajaților care au salariul peste 2500 RON");
            System.out.println("3. Afișarea angajaților din luna aprilie a anului trecut, cu funcții de conducere");
            System.out.println("4. Afișarea angajaților fără funcție de conducere, ordonați descrescător după salariu");
            System.out.println("5. Afișarea numelor angajaților scrise cu majuscule");
            System.out.println("6. Afișarea salariilor mai mici de 3000 RON");
            System.out.println("7. Afișarea datelor primului angajat al firmei");
            System.out.println("8. Afișarea statisticilor referitoare la salariu");
            System.out.println("9. Verificare dacă există un angajat cu numele 'Ion'");
            System.out.println("10. Numărul de angajați care s-au angajat vara anului trecut");
            System.out.println("0. Ieșire");
            System.out.print("Alegeți o opțiune: ");
            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    System.out.println("\n1. Lista de angajați:");
                    angajati.forEach(System.out::println);
                    break;

                case 2:
                    System.out.println("\n2. Angajații cu salariul peste 2500 RON:");
                    angajati.stream()
                            .filter(a -> a.getSalariul() > 2500)
                            .forEach(System.out::println);
                    break;

                case 3:
                    System.out.println("\n3. Angajații din luna aprilie, anul trecut, cu funcții de conducere:");
                    var anulTrecut = LocalDate.now().getYear() - 1;
                    var conducere = angajati.stream()
                            .filter(a -> a.getDataAngajarii().getYear() == anulTrecut &&
                                    a.getDataAngajarii().getMonth() == Month.APRIL &&
                                    (a.getPostul().toLowerCase().contains("sef") ||
                                            a.getPostul().toLowerCase().contains("director")))
                            .collect(Collectors.toList());
                    conducere.forEach(System.out::println);
                    break;

                case 4:
                    System.out.println("\n4. Angajații fără funcție de conducere (ordonați descrescător după salariu):");
                    angajati.stream()
                            .filter(a -> !a.getPostul().toLowerCase().contains("sef") &&
                                    !a.getPostul().toLowerCase().contains("director"))
                            .sorted(Comparator.comparingDouble(Angajat::getSalariul).reversed())
                            .forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("\n5. Numele angajaților scrise cu majuscule:");
                    angajati.stream()
                            .map(a -> a.getNumele().toUpperCase())
                            .forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("\n6. Salariile mai mici de 3000 RON:");
                    angajati.stream()
                            .map(Angajat::getSalariul)
                            .filter(salariu -> salariu < 3000)
                            .forEach(System.out::println);
                    break;

                case 7:
                    System.out.println("\n7. Datele primului angajat al firmei:");
                    angajati.stream()
                            .min(Comparator.comparing(Angajat::getDataAngajarii))
                            .ifPresentOrElse(
                                    System.out::println,
                                    () -> System.out.println("Nu există angajați."));
                    break;

                case 8:
                    System.out.println("\n8. Statistici despre salarii:");
                    var statistici = angajati.stream()
                            .collect(Collectors.summarizingDouble(Angajat::getSalariul));
                    System.out.println("Salariul mediu: " + statistici.getAverage());
                    System.out.println("Salariul minim: " + statistici.getMin());
                    System.out.println("Salariul maxim: " + statistici.getMax());
                    break;

                case 9:
                    System.out.println("\n9. Există cel puțin un angajat cu numele Ion:");
                    angajati.stream()
                            .map(Angajat::getNumele)
                            .filter(nume -> nume.contains("Ion"))
                            .findAny()
                            .ifPresentOrElse(
                                    nume -> System.out.println("Firma are cel puțin un Ion angajat."),
                                    () -> System.out.println("Firma nu are nici un Ion angajat."));
                    break;

                case 10:
                    System.out.println("\n10. Numărul angajaților care s-au angajat vara anului trecut:");
                    var anulTrecut2 = LocalDate.now().getYear() - 1;
                    var vara = angajati.stream()
                            .filter(a -> a.getDataAngajarii().getYear() == anulTrecut2 &&
                                    (a.getDataAngajarii().getMonth() == Month.JUNE ||
                                            a.getDataAngajarii().getMonth() == Month.JULY ||
                                            a.getDataAngajarii().getMonth() == Month.AUGUST))
                            .count();
                    System.out.println("Număr angajați: " + vara);
                    break;

                case 0:
                    ruleaza = false;
                    System.out.println("Ieșire din program. La revedere!");
                    break;

                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
            }
        }
    }

    public static void writeToJson(List<Angajat> angajati, File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writerWithDefaultPrettyPrinter().writeValue(file, angajati);
    }

    public static List<Angajat> readFromJson(File file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Angajat.class));
    }
}