package Ex2lab7;

class SetTobe extends InstrumentMuzical {
    private TipTobe tip_tobe;
    private int nr_tobe;
    private int nr_cinele;

    public SetTobe(String producator, double pret, TipTobe tip_tobe, int nr_tobe, int nr_cinele) {
        super(producator, pret);
        this.tip_tobe = tip_tobe;
        this.nr_tobe = nr_tobe;
        this.nr_cinele = nr_cinele;
    }

    public TipTobe getTipTobe() {
        return tip_tobe;
    }

    public int getNrTobe() {
        return nr_tobe;
    }

    public int getNrCinele() {
        return nr_cinele;
    }

    @Override
    public String toString() {
        return "SetTobe{" +
                "tip_tobe=" + tip_tobe +
                ", nr_tobe=" + nr_tobe +
                ", nr_cinele=" + nr_cinele +
                ", producator='" + producator + '\'' +
                ", pret=" + pret +
                '}';
    }
}
