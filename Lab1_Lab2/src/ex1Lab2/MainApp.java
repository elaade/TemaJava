/*1. Fișierul judete_in.txt, conține lista neordonată a județelor din țară. Să se încarce
datele din fișier într-un tablou de String-uri și să se ordoneze acest tablou cu ajutorul metodei
sort() din clasa Arrays. Să se returneze pe ce poziție se află în vectorul ordonat un județ
introdus de la tastatură. Se va utiliza metoda de căutare binară din clasa Arrays.*/
/*1. Fișierul judete_in.txt, conține lista neordonată a județelor din țară. Să se încarce
datele din fișier într-un tablou de String-uri și să se ordoneze acest tablou cu ajutorul metodei
sort() din clasa Arrays. Să se returneze pe ce poziție se află în vectorul ordonat un județ
introdus de la tastatură. Se va utiliza metoda de căutare binară din clasa Arrays. */
package ex1Lab2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class MainApp
{
    public static void main(String[] args)
    {
        String filePath = "src/judete_in.txt";
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String[] judete = new String[41];
            int i = 0;
            while ((line = reader.readLine()) != null)
            {
                judete[i++] = line.trim();
            }
            reader.close();
            Arrays.sort(judete);
            System.out.println("Județele ordonate:");
            for (String judet : judete)
            {
                System.out.println(judet);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduceți numele județului de căutat: ");
            String judetCautat = scanner.nextLine().trim();
            int index = Arrays.binarySearch(judete, judetCautat);
            if (index >= 0)
            {
                System.out.println("Județul " + judetCautat + " se află pe poziția " + index + " în vectorul ordonat.");
            } else {
                System.out.println("Județul " + judetCautat + " nu a fost găsit în listă.");
            }
        } catch (IOException e)
        {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }
}
