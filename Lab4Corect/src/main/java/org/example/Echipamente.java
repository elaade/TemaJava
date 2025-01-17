package org.example;
import java.io.Serializable;

/**
 * Clasa de bază pentru toate echipamentele.
 */
public abstract class Echipamente implements Serializable
{
    protected String denumire;
    protected int nrInv;
    protected double pret;
    protected String zonaMag;
    protected Stare stare;
    /**
     * Constructor pentru inițializarea unui echipament.
     */
    public Echipamente(String denumire, int nrInv, double pret, String zonaMag, Stare stare)
    {
        this.denumire = denumire;
        this.nrInv = nrInv;
        this.pret = pret;
        this.zonaMag = zonaMag;
        this.stare = stare;
    }
    /**
     * Metodă abstractă care afișează detalii despre echipament.
     */
    public abstract void afisareDetalii();
    @Override
    public String toString()
    {
        return "Echipament{" +
                "denumire='" + denumire + '\'' +
                ", nrInv=" + nrInv +
                ", pret=" + pret +
                ", zonaMag='" + zonaMag + '\'' +
                ", stare=" + stare +
                '}';
    }

    /**
     * Metodă pentru modificarea stării echipamentului.
     */
    public void modificaStare(Stare stareNoua)
    {
        this.stare = stareNoua;
    }
}
