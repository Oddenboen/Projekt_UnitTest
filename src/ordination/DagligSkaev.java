package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private final ArrayList<Dosis> doses = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }

    public ArrayList<Dosis> getDoser() {
        return new ArrayList<>(doses);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis d = new Dosis(tid, antal);
        doses.add(d);
    }

    @Override
    public double samletDosis() {
        double sum = 0;

        for (Dosis dose : doses) {
            sum+=dose.getAntal();
        }
        return sum;
    }

    @Override
    public double doegnDosis() {
        return samletDosis()/antalDage();
    }

    @Override
    public String getType() {
        return "Daglig skaev";
    }
}
