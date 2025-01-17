package org.example;
import java.io.*;
import java.util.*;

/**
 * Enumerare care definește stările posibile ale unui echipament.
 */
 enum Stare {
    ACHIZITIONAT,
    EXPUS,
    VANDUT
}
/**
 * Enumerare care definește modurile posibile de tipărire pentru imprimante.
 */
enum ModTiparire {
    COLOR,
    ALB_NEGRU
}
/**
 * Enumerare care definește formatele de copiere disponibile pentru copiatoare.
 */
 enum FormatCopiere {
    A3,
    A4
}
/**
 * Enumerare care definește sistemele de operare posibile pentru sistemele de calcul.
 */
 enum SistemOperare {
    WINDOWS,
    LINUX
}
/**
 * Clasa principală care gestionează meniul și interacțiunea utilizatorului.
 */
public class Main
{
    static List<Echipamente> listaEchipamente = new ArrayList<>();

    public static void main(String[] args)
    {
        citesteFisier();
        Scanner scanner = new Scanner(System.in);
        int optiune;
        do {
            afiseazaMeniu();
            optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    afiseazaToateEchipamentele();
                    break;
                case 2:
                    afiseazaImprimantele();
                    break;
                case 3:
                    afiseazaCopiatoarele();
                    break;
                case 4:
                    afiseazaSistemeCalcul();
                    break;
                case 5:
                    modificaStareEchipament(scanner);
                    break;
                case 6:
                    seteazaModTiparire(scanner);
                    break;
                case 7:
                    seteazaFormatCopiere(scanner);
                    break;
                case 8:
                    instaleazaSistemOperare(scanner);
                    break;
                case 9:
                    afiseazaVandute();
                    break;
                case 10:
                    serializeazaEchipamente();
                    break;
                case 11:
                    deserializeazaEchipamente();
                    break;
                case 0:
                    System.out.println("Iesire din program...");
                    break;
                default:
                    System.out.println("Opțiune invalidă. Încercați din nou.");
                    break;
            }
        } while (optiune != 0);
    }
    private static void afiseazaMeniu()
    {
        System.out.println("\nMeniu:");
        System.out.println("1. Afișează toate echipamentele");
        System.out.println("2. Afișează imprimantele");
        System.out.println("3. Afișează copiatoarele");
        System.out.println("4. Afișează sistemele de calcul");
        System.out.println("5. Modifică starea unui echipament");
        System.out.println("6. Setează modul de tiparire pentru o imprimantă");
        System.out.println("7. Setează formatul de copiere pentru un copiator");
        System.out.println("8. Instalează un sistem de operare pe un sistem de calcul");
        System.out.println("9. Afișează echipamentele vândute");
        System.out.println("10. Serializează echipamentele");
        System.out.println("11. Deserializați echipamentele");
        System.out.println("0. Ieși");
        System.out.print("Alegeți o opțiune: ");
    }
    private static void afiseazaToateEchipamentele()
    {
        for (Echipamente echipament : listaEchipamente)
        {
            echipament.afisareDetalii();
        }
    }
    private static void afiseazaImprimantele()
    {
        for (Echipamente echipament : listaEchipamente)
        {
            if (echipament instanceof Imprimanta)
            {
                echipament.afisareDetalii();
            }
        }
    }
    private static void afiseazaCopiatoarele()
    {
        for (Echipamente echipament : listaEchipamente)
        {
            if (echipament instanceof Copiator)
            {
                echipament.afisareDetalii();
            }
        }
    }
    private static void afiseazaSistemeCalcul()
    {
        for (Echipamente echipament : listaEchipamente)
        {
            if (echipament instanceof SistemCalcul)
            {
                echipament.afisareDetalii();
            }
        }
    }
    private static void modificaStareEchipament(Scanner scanner)
    {
        System.out.print("Introduceți numărul de inventar al echipamentului: ");
        int nrInv = scanner.nextInt();
        scanner.nextLine();
        Echipamente echipament = cautaEchipamentDupaNrInv(nrInv);
        if (echipament != null)
        {
            System.out.print("Introduceți noua stare (ACHIZITIONAT, EXPUS, VANDUT): ");
            String stareNoua = scanner.nextLine();
            echipament.modificaStare(Stare.valueOf(stareNoua.toUpperCase()));
            System.out.println("Stare modificată.");
        }
        else
        {
            System.out.println("Echipament nu găsit.");
        }
    }
    private static void seteazaModTiparire(Scanner scanner)
    {
        System.out.print("Introduceți numărul de inventar al imprimantei: ");
        int nrInv = scanner.nextInt();
        scanner.nextLine();
        Echipamente echipament = cautaEchipamentDupaNrInv(nrInv);
        if (echipament instanceof Imprimanta)
        {
            System.out.print("Introduceți modul de tipărire (COLOR sau ALB_NEGRU): ");
            String mod = scanner.nextLine();
            ((Imprimanta) echipament).seteazaModTiparire(ModTiparire.valueOf(mod.toUpperCase()));
            System.out.println("Modul de tipărire setat.");
        }
        else
        {
            System.out.println("Echipament nu este o imprimantă.");
        }
    }
    private static void seteazaFormatCopiere(Scanner scanner)
    {
        System.out.print("Introduceți numărul de inventar al copiatorului: ");
        int nrInv = scanner.nextInt();
        scanner.nextLine();
        Echipamente echipament = cautaEchipamentDupaNrInv(nrInv);
        if (echipament instanceof Copiator)
        {
            System.out.print("Introduceți formatul de copiere (A3 sau A4): ");
            String format = scanner.nextLine();
            ((Copiator) echipament).seteazaFormatCopiere(FormatCopiere.valueOf(format.toUpperCase()));
            System.out.println("Formatul de copiere setat.");
        }
        else
        {
            System.out.println("Echipament nu este un copiator.");
        }
    }
    private static void instaleazaSistemOperare(Scanner scanner)
    {
        System.out.print("Introduceți numărul de inventar al sistemului de calcul: ");
        int nrInv = scanner.nextInt();
        scanner.nextLine();
        Echipamente echipament = cautaEchipamentDupaNrInv(nrInv);
        if (echipament instanceof SistemCalcul)
        {
            System.out.print("Introduceți sistemul de operare (WINDOWS sau LINUX): ");
            String sistemOp = scanner.nextLine();
            ((SistemCalcul) echipament).instaleazaSistemOperare(SistemOperare.valueOf(sistemOp.toUpperCase()));
            System.out.println("Sistem de operare instalat.");
        }
        else
        {
            System.out.println("Echipament nu este un sistem de calcul.");
        }
    }
    private static void afiseazaVandute()
    {
        for (Echipamente echipament : listaEchipamente)
        {
            if (echipament.stare == Stare.VANDUT)
            {
                echipament.afisareDetalii();
            }
        }
    }
    private static Echipamente cautaEchipamentDupaNrInv(int nrInv)
    {
        for (Echipamente echipament : listaEchipamente)
        {
            if (echipament.nrInv == nrInv)
            {
                return echipament;
            }
        }
        return null;
    }
    private static void serializeazaEchipamente()
    {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("echip.bin")))
        {
            oos.writeObject(listaEchipamente);
            System.out.println("Colecția de echipamente a fost serializată.");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private static void deserializeazaEchipamente()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("echip.bin")))
        {
            listaEchipamente = (List<Echipamente>) ois.readObject();
            System.out.println("Colecția de echipamente a fost deserializată.");
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    private static void citesteFisier()
    {
        try (BufferedReader br = new BufferedReader(new FileReader("src/echipamente1.txt")))
        {
            String linie;
            while ((linie = br.readLine()) != null)
            {
                String[] date = linie.split(";");

                String denumire = date[0];
                int nrInv = Integer.parseInt(date[1]);
                double pret = Double.parseDouble(date[2]);
                String zonaMag = date[3];
                Stare stare = Stare.valueOf(date[4].toUpperCase());
                String tipEchipament = date[5].toLowerCase();

                switch (tipEchipament)
                {
                    case "imprimanta":
                        int ppm = Integer.parseInt(date[6]);
                        String dpi = date[7];
                        int pCar = Integer.parseInt(date[8]);
                        ModTiparire modTiparire = ModTiparire.valueOf(date[9].toUpperCase());
                        listaEchipamente.add(new Imprimanta(denumire, nrInv, pret, zonaMag, stare, ppm, dpi, pCar, modTiparire));
                        break;

                    case "copiator":
                        int pTon = Integer.parseInt(date[6]);
                        FormatCopiere formatCopiere = FormatCopiere.valueOf(date[7].toUpperCase());
                        listaEchipamente.add(new Copiator(denumire, nrInv, pret, zonaMag, stare, pTon, formatCopiere));
                        break;

                    case "sistem de calcul":
                        String tipMon = date[6];
                        double vitProc = Double.parseDouble(date[7]);
                        int cHdd = Integer.parseInt(date[8]);
                        SistemOperare sistemOperare = SistemOperare.valueOf(date[9].toUpperCase());
                        listaEchipamente.add(new SistemCalcul(denumire, nrInv, pret, zonaMag, stare, tipMon, vitProc, cHdd, sistemOperare));
                        break;

                    default:
                        System.out.println("Tip necunoscut: " + tipEchipament);
                        break;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}