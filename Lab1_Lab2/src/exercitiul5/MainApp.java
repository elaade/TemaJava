/*5. Să se scrie un program care generează aleatoriu un număr întreg cuprins între 0 și 20.
        Programul va determina dacă numărul aparține șirului lui Fobonacci.*/
package exercitiul5;
import java.util.Random;
public class MainApp
{
        public static void main(String[] args)
        {
            Random random = new Random();
            int numar = random.nextInt(21);
            System.out.println("Numarul generat este: " + numar);
            if (esteFibonacci(numar))
            {
                System.out.println(numar + " apartine sirului lui Fibonacci.");
            } else
            {
                System.out.println(numar + " nu apartine sirului lui Fibonacci.");
            }
        }
        public static boolean esteFibonacci(int n)
        {
            int a = 0;
            int b = 1;
            while (a <= n)
            {
                if (a == n)
                {
                    return true;
                }
                int urmator = a + b;
                a = b;
                b = urmator;
            }
            return false;
        }
}

