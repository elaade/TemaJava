/*4. Să se determine cmmdc a două numere naturale, a căror valoare maximă este 30. Numerele
        vor fi generate aleatoriu cu ajutorul unui obiect de tip Random și metodei nextInt();*/
package exercitiul4;
import java.util.Random;
public class MainApp
{
    public static void main(String[] args)
    {
        Random random = new Random();
        int numar1 = random.nextInt(31);
        int numar2 = random.nextInt(31);
        System.out.println("Numerele generate sunt: " + numar1 + " si " + numar2);
        int cmmdc = calculeazaCmmdc(numar1, numar2);
        System.out.println("CMMDC-ul celor două numere este: " + cmmdc);
    }
    public static int calculeazaCmmdc(int a, int b)
    {
        while (b != 0)
        {
            int rest = a % b;
            a = b;
            b = rest;
        }
        return a;
    }
}

