/*3. Să se scrie un program care citește un număr n natural de la tastatură și afișează toți
        divizorii acestuia pe ecran. Dacă numărul este prim se va afișa un mesaj corespunzător.*/
package exercitiul3;
import java.util.Scanner;
public class MainApp
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti un numar natural: ");
        int n = scanner.nextInt();
        boolean estePrim = true;
        System.out.println("Divizorii numarului " + n + " sunt:");
        for (int i = 1; i <= n; i++)
        {
            if (n % i == 0)
            {
                System.out.print(i + " ");
                if (i != 1 && i != n)
                {
                    estePrim = false;
                }
            }
        }
        System.out.println();
        if (estePrim && n > 1)
        {
            System.out.println(n + " este un numar prim.");
        }
        else if (n > 1)
        {
            System.out.println(n + " nu este un numar prim.");
        }
        else
        {
            System.out.println(n + " nu este un numar prim, deoarece 0 și 1 nu sunt considerate numere prime.");
        }
    }
}
