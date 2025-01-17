package org.example;
/**
 * Clasa care reprezintă o imprimantă.
 */
public class Imprimanta extends Echipamente
{
    private int ppm;
    private String dpi;
    private int pCar;
    private ModTiparire modTiparire;
    /**
     * Constructor pentru inițializarea unei imprimante.
     */
    public Imprimanta(String denumire, int nrInv, double pret, String zonaMag, Stare stare,
                      int ppm, String dpi, int pCar, ModTiparire modTiparire)
    {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.ppm = ppm;
        this.dpi = dpi;
        this.pCar = pCar;
        this.modTiparire = modTiparire;
    }
    /**
     * Metodă pentru afișarea detaliilor imprimantei.
     */
    public void afisareDetalii()
    {
        System.out.println(super.toString() + ", Imprimanta{" +
                "ppm=" + ppm +
                ", dpi='" + dpi + '\'' +
                ", pCar=" + pCar +
                ", modTiparire=" + modTiparire +
                '}');
    }
    /**
     * Metodă pentru setarea modului de tipărire.
     */
    public void seteazaModTiparire(ModTiparire mod)
    {
        this.modTiparire = mod;
    }
}
