package Ex2lab7;

class Chitara extends InstrumentMuzical {
    private TipChitara tip_chitara;
    private int nr_corzi;

    public Chitara(String producator, double pret, TipChitara tip_chitara, int nr_corzi) {
        super(producator, pret);
        this.tip_chitara = tip_chitara;
        this.nr_corzi = nr_corzi;
    }

    public TipChitara getTipChitara() {
        return tip_chitara;
    }

    public int getNrCorzi() {
        return nr_corzi;
    }

    @Override
    public String toString() {
        return "Chitara{" +
                "tip_chitara=" + tip_chitara +
                ", nr_corzi=" + nr_corzi +
                ", producator='" + producator + '\'' +
                ", pret=" + pret +
                '}';
    }
}
