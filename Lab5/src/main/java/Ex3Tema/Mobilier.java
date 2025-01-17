package Ex3Tema;

import java.util.List;

public class Mobilier {
    private String nume;
    private List<Placa> placi;

    public String getNume() {
        return nume;
    }

    public List<Placa> getPlaci() {
        return placi;
    }

    @Override
    public String toString() {
        return "Mobilier{" +
                "nume='" + nume + '\'' +
                ", placi=" + placi +
                '}';
    }
}
