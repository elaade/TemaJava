package Lab3Ex2;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    public static void main(String[] args) throws IOException
    {
        List<Produs> produse = new ArrayList<>();
        Path path = Paths.get("src/produse.csv");
        try (BufferedReader br = Files.newBufferedReader(path))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] parts = line.split(",");
                String denumire = parts[0];
                double pret = Double.parseDouble(parts[1]);
                int cantitate = Integer.parseInt(parts[2]);
                LocalDate dataExpirarii = LocalDate.parse(parts[3]);
                produse.add(new Produs(denumire, pret, cantitate, dataExpirarii));
            }
        } catch (IOException e)
        {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        Scanner scanner = new Scanner(System.in);
        int optiune;
        do {
            System.out.println("\nMeniu:");
            System.out.println("1. Afisarea tuturor produselor");
            System.out.println("2. Afisarea produselor expirate");
            System.out.println("3. Vanzarea unui produs");
            System.out.println("4. Afisarea produselor cu pretul minim");
            System.out.println("5. Salvarea produselor cu cantitate mai mica decat o valoare data");
            System.out.println("6. Afisarea incasarilor totale");
            System.out.println("0. Iesire");
            System.out.print("Alegeti o optiune: ");
            optiune = scanner.nextInt();
            scanner.nextLine();
            switch (optiune)
            {
                case 1:
                    produse.forEach(System.out::println);
                    break;
                case 2:
                    produse.stream()
                            .filter(p -> p.getDataExpirarii().isBefore(LocalDate.now()))
                            .forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Introduceti denumirea produsului: ");
                    String denumire = scanner.nextLine();
                    System.out.print("Introduceti cantitatea de vandut: ");
                    int cantitateVanduta = scanner.nextInt();
                    Optional<Produs> produsOpt = produse.stream()
                            .filter(p -> p.getDenumire().equalsIgnoreCase(denumire))
                            .findFirst();
                    if (produsOpt.isPresent())
                    {
                        Produs produs = produsOpt.get();
                        if (produs.getCantitate() >= cantitateVanduta)
                        {
                            produs.setCantitate(produs.getCantitate() - cantitateVanduta);
                            Produs.adaugaIncasari(produs.getPret() * cantitateVanduta);
                            System.out.println("Produs vandut cu succes!");
                            if (produs.getCantitate() == 0)
                            {
                                produse.remove(produs);
                                System.out.println("Produsul a fost eliminat din stoc.");
                            }
                        } else
                        {
                            System.out.println("Cantitate insuficienta pe stoc.");
                        }
                    } else
                    {
                        System.out.println("Produsul nu exista.");
                    }
                    break;
                case 4:
                    double pretMinim = produse.stream()
                            .mapToDouble(Produs::getPret)
                            .min()
                            .orElse(Double.MAX_VALUE);
                    produse.stream()
                            .filter(p -> p.getPret() == pretMinim)
                            .forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Introduceti cantitatea maxima: ");
                    int cantitateMax = scanner.nextInt();
                    List<Produs> produseFiltrate = produse.stream()
                            .filter(p -> p.getCantitate() < cantitateMax)
                            .collect(Collectors.toList());
                    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("produse_filtrate.csv")))
                    {
                        for (Produs p : produseFiltrate)
                        {
                            writer.write(p.getDenumire() + "," + p.getPret() + "," + p.getCantitate() + "," + p.getDataExpirarii());
                            writer.newLine();
                        }
                        System.out.println("Produsele au fost salvate in 'produse_filtrate.csv'.");
                    } catch (IOException e)
                    {
                        System.out.println("Eroare la scrierea fisierului: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Incasarile totale: " + Produs.getIncasari());
                    break;
                case 0:
                    System.out.println("Iesire din program...");
                    break;
                default:
                    System.out.println("Optiune invalida.");
            }
        } while (optiune != 0);
        scanner.close();
    }
}

