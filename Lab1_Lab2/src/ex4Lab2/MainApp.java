package ex4Lab2;
import java.util.Scanner;
public class MainApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti numarul de persoane: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        Persoana[] persoane = new Persoana[n];
        for (int i = 0; i < n; i++)
        {
            System.out.println("Introduceti datele pentru persoana " + (i + 1) + ":");
            System.out.print("Nume: ");
            String nume = scanner.nextLine();
            String cnp;
            while (true)
            {
                System.out.print("CNP: ");
                cnp = scanner.nextLine();

                if (validareCnp(cnp))
                {
                    break;
                }
                else
                {
                    System.out.println("CNP invalid. Reintroduceti.");
                }
            }
            persoane[i] = new Persoana(nume, cnp);
        }
        System.out.println("\nDate introduse:");
        for (Persoana persoana : persoane)
        {
            System.out.println(persoana);
        }
    }
    private static boolean validareCnp(String cnp)
    {
        if (cnp.length() != 13)
        {
            return false;
        }
        if (!cnp.matches("\\d+"))
        {
            return false;
        }
        char primaCifra = cnp.charAt(0);
        if (primaCifra != '1' && primaCifra != '2' && primaCifra != '5' && primaCifra != '6')
        {
            return false;
        }
        return true;
    }
}
