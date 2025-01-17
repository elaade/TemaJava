/*1. Se cere să se scrie un program Java care să calculeze şi să afişeze perimetru şi aria unui
        dreptunghi. Valorile pentru lungime şi lățime se citesc de la tastatura. Sa se adauge un break
        point pe prima linie care citește valoarea unei laturi si din acel punct să se ruleze programul
        linie cu linie urmărind valorile variabilelor în memorie.*/
package exercitiul1;
import java.util.Scanner;
public class MainApp {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceti lungimea dreptunghiului: ");
        double lungime = scanner.nextDouble();
        System.out.print("Introduceti latimea dreptunghiului: ");
        double latime = scanner.nextDouble();
        double perimetru = 2 * (lungime + latime);
        double aria = lungime * latime;
        System.out.println("Perimetrul dreptunghiului este: " + perimetru);
        System.out.println("Aria dreptunghiului este: " + aria);
    }
}
