package Ex2Tema;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class MainApp {
    public static void main(String[] args)
    {
        List<PerecheNumere> perecheNumere=new ArrayList<>();
        perecheNumere.add(new PerecheNumere(12,21));
        perecheNumere.add(new PerecheNumere(13,14));
        perecheNumere.add(new PerecheNumere(5,6));
        perecheNumere.add(new PerecheNumere(1,2));
        scriere(perecheNumere);
        //citire();
        boolean ruleaza=true;
        Scanner scanner=new Scanner(System.in);
       while(ruleaza)
       {
           System.out.println("1.Afisare numere");
           System.out.println("2.Fibbonaci");
           System.out.println("3.Cel mai mic multiplu comun");
           System.out.println("4.Suma cifrelor egala");
           System.out.println("5.Acelasi numar de cifre pare");
           System.out.println("6.Iesire");
           System.out.println("Introduceti o optiune:");
           int opt= scanner.nextInt();
           scanner.nextLine();
           switch(opt)
           {
               case 1:
                   citire();
                   break;
               case 2:
                   for(PerecheNumere pereche:perecheNumere)
                   {
                       System.out.println(pereche+"-Sunt consecutive in fibbonaci? "+pereche.Fibbonaci());
                   }
                   break;
               case 3:
                   for(PerecheNumere per:perecheNumere)
                   {
                       System.out.println("Cel mai mic multiplu comun al nr: "+per.getA()+" si "+per.getB()+" este "+per.CelMaiMicMultipluComun(per.getA(), per.getB()));
                   }
                   break;
               case 4:
                   for(PerecheNumere pe:perecheNumere)
                   {
                       System.out.println("Numerele: "+pe.getA()+" si "+pe.getB()+" au suma cifrelor egala? "+pe.SumaCifrelorUnuiNumar(pe.getA(),pe.getB()));
                   }
                   break;
               case 5:
                   for(PerecheNumere pere:perecheNumere)
                   {
                       System.out.println("Numerele:"+pere.getA()+" si "+pere.getB()+" au acelasi numar de cifre pare? "+pere.AcelasiNumarDeCifrePare(pere.getA(),pere.getB()));
                   }
                   break;
               case 6:
                   ruleaza=false;
                   System.out.println("Ceau");
                   break;
               default:
                   System.out.println("Optiune invalida! Introduceti o optiune valida!");
           }
       }


    }
    static void scriere(List<PerecheNumere>lista)
    {
        try
        {
            ObjectMapper mapper=new ObjectMapper();
            File file=new File("src/main/resources/perechenumere.json");
            mapper.writeValue(file,lista);
            System.out.println("Scriere cu succes!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    static List<PerecheNumere> citire()
    {
        try
        {
            File file=new File("src/main/resources/perechenumere.json");
            ObjectMapper mapper=new ObjectMapper();
            List<PerecheNumere> perecheNumere=mapper.readValue(file, new TypeReference<List<PerecheNumere>>() {
            });
            for(PerecheNumere p:perecheNumere)
            {
                System.out.println(p);
            }
            System.out.println("Citire cu succes!");
            return perecheNumere;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
