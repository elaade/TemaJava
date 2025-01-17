/*3. Să se insereze într-o anumită poziție a unui șir de caractere, un alt șir. Datele vor fi
preluate de la tastatură sau din fișier. Să se șteargă o porțiune a unui șir de caractere care
începe dintr-o anumită poziție și are un anumit număr de caractere. Se recomandă utilizarea
clasei StringBuilder. */
package ex2Lab2;
import java.util.Scanner;
public class MainApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti sirul de caractere principal: ");
        String textInitial = scanner.nextLine();
        StringBuilder sb = new StringBuilder(textInitial);
        System.out.print("Introduceti sirul de caractere de inserat: ");
        String textDeInserat = scanner.nextLine();
        System.out.print("Introduceti pozitia la care sa se insereze: ");
        int pozitieInserare = scanner.nextInt();
        scanner.nextLine();
        if (pozitieInserare >= 0 && pozitieInserare <= sb.length())
        {
            sb.insert(pozitieInserare, textDeInserat);
            System.out.println("Sirul dupa inserare: " + sb.toString());
        }
        else
        {
            System.out.println("Pozitia de inserare este invalida.");
        }
        System.out.print("Introduceti pozitia de la care sa inceapa stergerea: ");
        int pozitieStergere = scanner.nextInt();
        System.out.print("Introduceti numarul de caractere de sters: ");
        int numarCaractereDeSters = scanner.nextInt();
        int pozitieFinalaStergere = pozitieStergere + numarCaractereDeSters;
        if (pozitieStergere >= 0 && pozitieFinalaStergere <= sb.length())
        {
            sb.delete(pozitieStergere, pozitieFinalaStergere);
            System.out.println("Sirul dupa stergere: " + sb.toString());
        }
        else
        {
            System.out.println("Intervalul pentru stergere este invalid.");
        }
        scanner.close();
    }
}
