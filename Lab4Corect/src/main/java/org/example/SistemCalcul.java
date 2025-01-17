package org.example;
/**
 * Clasa care reprezintă un sistem de calcul.
 */
public class SistemCalcul extends Echipamente {
    private String tipMon;      // Tipul monitorului
    private double vitProc;     // Viteza procesorului
    private int cHdd;           // Capacitatea HDD-ului
    private SistemOperare sistemOperare;

    /**
     * Constructor pentru inițializarea unui sistem de calcul.
     */
    public SistemCalcul(String denumire, int nrInv, double pret, String zonaMag, Stare stare,
                        String tipMon, double vitProc, int cHdd, SistemOperare sistemOperare) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.tipMon = tipMon;
        this.vitProc = vitProc;
        this.cHdd = cHdd;
        this.sistemOperare = sistemOperare;
    }

    /**
     * Metodă pentru afișarea detaliilor sistemului de calcul.
     */
    public void afisareDetalii() {
        System.out.println(super.toString() + ", SistemCalcul{" +
                "tipMon='" + tipMon + '\'' +
                ", vitProc=" + vitProc +
                ", cHdd=" + cHdd +
                ", sistemOperare=" + sistemOperare +
                '}');
    }

    /**
     * Metodă pentru instalarea unui sistem de operare.
     */
    public void instaleazaSistemOperare(SistemOperare sistem) {
        this.sistemOperare = sistem;
    }
}

/*class SistemCalcul extends Echipament {
    private String tipMonitor;
    private double vitezaProcesor;
    private int capacitateHDD;
    private SistemOperare sistemOperare;

    public SistemCalcul(String denumire, int nrInv, double pret, String zonaMag, Stare stare, String tipMonitor, double vitezaProcesor, int capacitateHDD, SistemOperare sistemOperare) {
        super(denumire, nrInv, pret, zonaMag, stare);
        this.tipMonitor = tipMonitor;
        this.vitezaProcesor = vitezaProcesor;
        this.capacitateHDD = capacitateHDD;
        this.sistemOperare = sistemOperare;
    }

    public void setSistemOperare(SistemOperare sistemOperare) {
        this.sistemOperare = sistemOperare;
    }

    @Override
    public void afisare() {
        System.out.println("Sistem Calcul: " + denumire + ", Nr Inv: " + nrInv + ", Pret: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare + ", Tip Monitor: " + tipMonitor + ", Viteza Procesor: " + vitezaProcesor + "GHz, HDD: " + capacitateHDD + "GB, Sistem Operare: " + sistemOperare);
    }
}*/