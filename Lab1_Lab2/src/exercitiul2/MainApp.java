/*2. Să se scrie un program care citește un set de numerele din fișierul de intrare in.txt, care
        conține câte un număr pe un rând, având valorile din figura 18. Programul va determină suma
        lor, media aritmetică, valoarea minimă, valoarea maximă, va afișa aceste valori pe ecran și le
        va scrie în fișierul de ieșire out.txt. Media aritmetică va fi afișată ca un număr real.*/
package exercitiul2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class MainApp
{
        public static void main(String[] args)
        {
            int suma = 0;
            double media = 0.0;
            int minim = Integer.MAX_VALUE;
            int maxim = Integer.MIN_VALUE;
            int count = 0;

            try (Scanner scanner = new Scanner(new File("in.txt")))
            {
                while (scanner.hasNextInt())
                {
                    int numar = scanner.nextInt();
                    suma += numar;
                    count++;
                    if (numar < minim) {
                        minim = numar;
                    }
                    if (numar > maxim) {
                        maxim = numar;
                    }
                }
                if (count > 0)
                {
                    media = (double) suma / count;
                }

            }
            catch (FileNotFoundException e)
            {
                System.out.println("Fisierul in.txt nu a fost gasit.");
                return;
            }
            System.out.println("Suma: " + suma);
            System.out.println("Media aritmetica: " + media);
            System.out.println("Valoarea minima: " + minim);
            System.out.println("Valoarea maxima: " + maxim);

            try (FileWriter writer = new FileWriter("out.txt"))
            {
                writer.write("Suma: " + suma + "\n");
                writer.write("Media aritmetica: " + media + "\n");
                writer.write("Valoarea minima: " + minim + "\n");
                writer.write("Valoarea maxima: " + maxim + "\n");
            }
            catch (IOException e)
            {
                System.out.println("Eroare la scrierea in fisierul out.txt.");
            }
        }
}
