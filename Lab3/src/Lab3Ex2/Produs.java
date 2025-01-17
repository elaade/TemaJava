package Lab3Ex2;

import java.time.LocalDate;

public class Produs
{
    private String denumire;
    private double pret;
    private int cantitate;
    private LocalDate dataExpirarii;
    private static double incasari = 0.0;

    public Produs(String denumire, double pret, int cantitate, LocalDate dataExpirarii)
    {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.dataExpirarii = dataExpirarii;
    }

    public String getDenumire()
    {
        return denumire;
    }

    public double getPret()
    {
        return pret;
    }

    public int getCantitate()
    {
        return cantitate;
    }

    public void setCantitate(int cantitate)
    {
        this.cantitate = cantitate;
    }

    public LocalDate getDataExpirarii()
    {
        return dataExpirarii;
    }

    public static double getIncasari()
    {
        return incasari;
    }

    public static void adaugaIncasari(double suma)
    {
        incasari += suma;
    }
    @Override
    public String toString() {
        return "Produs{" +
                "denumire='" + denumire + '\'' +
                ", pret=" + pret +
                ", cantitate=" + cantitate +
                ", dataExpirarii=" + dataExpirarii +
                '}';
    }
}

