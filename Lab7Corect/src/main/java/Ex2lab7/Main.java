package Ex2lab7;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.Optional;
enum TipChitara {
    ELECTRICA, ACUSTICA, CLASICA
}
enum TipTobe {
    ELECTRONICE, ACUSTICE
}

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Set<InstrumentMuzical> instrumente = new HashSet<>();

        instrumente.add(new Chitara("Fender", 2000, TipChitara.ELECTRICA, 6));
        instrumente.add(new Chitara("Yamaha", 1500, TipChitara.ACUSTICA, 6));
        instrumente.add(new Chitara("Gibson", 3000, TipChitara.CLASICA, 6));
        instrumente.add(new SetTobe("Pearl", 2500, TipTobe.ELECTRONICE, 5, 2));
        instrumente.add(new SetTobe("Tama", 2000, TipTobe.ACUSTICE, 4, 3));
        instrumente.add(new SetTobe("Roland", 3500, TipTobe.ELECTRONICE, 6, 3));

        while (true) {
            System.out.println("\nMeniu:");
            System.out.println("1. Afișează instrumentele din colecție");
            System.out.println("2. Salvează instrumentele în fișier JSON");
            System.out.println("3. Încarcă instrumentele din fișier JSON");
            System.out.println("4. Verifică duplicatele în colecție");
            System.out.println("5. Șterge instrumentele cu preț mai mare de 3000 RON");
            System.out.println("6. Afișează chitarele");
            System.out.println("7. Afișează tobele");
            System.out.println("8. Afișează chitara cu cele mai multe corzi");
            System.out.println("9. Afișează tobele acustice ordonate");
            System.out.println("10. Iesire");
            System.out.print("Alegeți o opțiune: ");

            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    System.out.println("\nInstrumentele din colecție:");
                    instrumente.forEach(System.out::println);
                    break;

                case 2:
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
                    mapper.writeValue(new File("instrumente.json"), instrumente);
                    System.out.println("Instrumentele au fost salvate în fișierul instrumente.json.");
                    break;

                case 3:
                    mapper = new ObjectMapper();
                    mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
                    instrumente = mapper.readValue(new File("instrumente.json"),
                            mapper.getTypeFactory().constructCollectionType(Set.class, InstrumentMuzical.class));
                    System.out.println("Instrumentele au fost încărcate din fișierul instrumente.json.");
                    break;

                case 4:
                    Chitara chitaraNoua = new Chitara("Fender", 2000, TipChitara.ELECTRICA, 6);
                    if (!instrumente.add(chitaraNoua)) {
                        System.out.println("Instrumentul cu aceleași caracteristici există deja în colecție.");
                    } else {
                        System.out.println("Instrumentul a fost adăugat în colecție.");
                    }
                    break;

                case 5:
                    instrumente.removeIf(instrument -> instrument.getPret() > 3000);
                    System.out.println("Instrumentele cu preț mai mare de 3000 RON au fost șterse.");
                    break;

                case 6:
                    System.out.println("\nChitarele din colecție:");
                    instrumente.stream()
                            .filter(instrument -> instrument instanceof Chitara)
                            .forEach(System.out::println);
                    break;

                case 7:
                    System.out.println("\nTobele din colecție:");
                    instrumente.stream()
                            .filter(instrument -> instrument.getClass() == SetTobe.class)
                            .forEach(System.out::println);
                    break;

                case 8:
                    Optional<Chitara> chitaraMaxCorzi = instrumente.stream()
                            .filter(instrument -> instrument instanceof Chitara)
                            .map(instrument -> (Chitara) instrument)
                            .max((ch1, ch2) -> Integer.compare(ch1.getNrCorzi(), ch2.getNrCorzi()));
                    chitaraMaxCorzi.ifPresent(System.out::println);
                    break;

                case 9:
                    System.out.println("\nTobele acustice ordonate:");
                    instrumente.stream()
                            .filter(instrument -> instrument instanceof SetTobe)
                            .map(instrument -> (SetTobe) instrument)
                            .filter(tobe -> tobe.getTipTobe() == TipTobe.ACUSTICE)
                            .sorted((t1, t2) -> Integer.compare(t1.getNrTobe(), t2.getNrTobe()))
                            .forEach(System.out::println);
                    break;

                case 10:
                    System.out.println("La revedere!");
                    return;

                default:
                    System.out.println("Opțiune invalidă. Vă rugăm să alegeți o opțiune validă.");
            }
        }
    }
}

