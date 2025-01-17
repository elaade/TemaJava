package Ex3Tema;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

enum Orientare {
    LUNGIME, LATIME, ORICARE
}

public class MainApp {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Mobilier> mobilierList = objectMapper.readValue(
                    new File("src/mobilier.json"),
                    new TypeReference<>() {}
            );

            System.out.println("Toate piesele de mobilier:");
            mobilierList.forEach(System.out::println);

            System.out.println("\nPiese de mobilier si placi:");
            for (Mobilier mobilier : mobilierList) {
                System.out.println("Piesa de mobilier: " + mobilier.getNume());
                for (Placa placa : mobilier.getPlaci()) {
                    System.out.println(placa);
                }
            }

            String mobilierCautat = "birou";
            System.out.println("\nCaracteristicile placilor pentru: " + mobilierCautat);
            mobilierList.stream()
                    .filter(mobilier -> mobilier.getNume().equalsIgnoreCase(mobilierCautat))
                    .findFirst()
                    .ifPresent(mobilier -> mobilier.getPlaci().forEach(System.out::println));

            double suprafataColaPal = 2800 * 2070;
            for (Mobilier mobilier : mobilierList) {
                double suprafataNecesara = mobilier.getPlaci().stream()
                        .mapToDouble(placa -> placa.getLungime() * placa.getLatime() * placa.getNrBucati())
                        .sum();
                int coliNecesar = (int) Math.ceil(suprafataNecesara / suprafataColaPal);
                System.out.println("\nPentru piesa de mobilier \"" + mobilier.getNume() + "\" sunt necesare: " + coliNecesar + " coli de PAL.");
            }

        } catch (IOException e) {
            System.err.println("Eroare la citirea fisierului JSON: " + e.getMessage());
        }
    }
}
