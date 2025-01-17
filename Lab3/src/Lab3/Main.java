package Lab3;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Main
{
    public static void main(String[] args)
    {
        List<Parabola> parabole = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/in.txt")))
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coeficienti = line.split(" ");
                int a = Integer.parseInt(coeficienti[0]);
                int b = Integer.parseInt(coeficienti[1]);
                int c = Integer.parseInt(coeficienti[2]);
                parabole.add(new Parabola(a, b, c));
            }
        } catch (IOException e)
        {
            System.err.println("Eroare la citirea fisierului: " + e.getMessage());
            return;
        }
        for (Parabola p : parabole)
        {
            System.out.println(p);
            double[] varf = p.calculeazaVarf();
            System.out.println("Varful: (" + varf[0] + ", " + varf[1] + ")");
        }
        for (int i = 0; i < parabole.size() - 1; i++)
        {
            Parabola p1 = parabole.get(i);
            Parabola p2 = parabole.get(i + 1);
            double[] mijloc = Parabola.calculeazaMijloc(p1, p2);
            System.out.println("Mijlocul segmentului intre " + p1 + " si " + p2 + ": (" + mijloc[0] + ", " + mijloc[1] + ")");
            double lungime = Parabola.calculeazaLungimeIntreVarfuri(p1, p2);
            System.out.println("Lungimea segmentului intre varfuri: " + lungime);
        }
    }
}
