package ex3Lab2;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MainApp
{
    public static void main(String[] args)
    {
        String terminatieSpecifica = "re";
        ArrayList<Vers> versuri = new ArrayList<>();
        Random random = new Random();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/cantec_in.txt")))
        {
            String linie;
            while ((linie = reader.readLine()) != null)
            {
                versuri.add(new Vers(linie));
            }
        } catch (IOException e)
        {
            System.out.println("Eroare la citirea fisierului: " + e.getMessage());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/cantec_out.txt")))
        {
            for (Vers vers : versuri)
            {
                int numarCuvinte = vers.numaraCuvinte();
                int numarVocale = vers.numaraVocale();
                boolean seTerminaCuSpecific = vers.seTerminaCu(terminatieSpecifica);
                boolean scriereMajuscule = random.nextDouble() < 0.1;
                String linie = (scriereMajuscule ? vers.toUpperCase() : vers.getVers()) +
                        " - Cuvinte: " + numarCuvinte +
                        ", Vocale: " + numarVocale;
                if (seTerminaCuSpecific)
                {
                    linie += " *";
                }
                writer.write(linie);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Eroare la scrierea in fisier: " + e.getMessage());
        }
    }
}
