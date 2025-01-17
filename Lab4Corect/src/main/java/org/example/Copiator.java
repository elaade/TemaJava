package org.example;
/**
 * Clasa care reprezintă un copiator.
 */
public class Copiator extends Echipamente
{
    private int pTon;
    private FormatCopiere formatCopiere;
    /**
     * Constructor pentru inițializarea unui copiator.
     */
    public Copiator(String denumire, int nrInv, double pret, String zonaMag, Stare stare,
                    int pTon, FormatCopiere formatCopiere)
    {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.pTon = pTon;
        this.formatCopiere = formatCopiere;
    }
    /**
     * Metodă pentru afișarea detaliilor copiatorului.
     */
    public void afisareDetalii()
    {
        System.out.println(super.toString() + ", Copiator{" +
                "pTon=" + pTon +
                ", formatCopiere=" + formatCopiere +
                '}');
    }
    /**
     * Metodă pentru setarea formatului de copiere.
     */
    public void seteazaFormatCopiere(FormatCopiere format)
    {
        this.formatCopiere = format;
    }
}
