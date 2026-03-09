package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {
    private Dosis[] dosis = new Dosis[4];


    public DagligFast(LocalDate startDen, LocalDate slutDen) {
        super(startDen, slutDen);
    }


    public Dosis createDosis(LocalTime tid, double antal) {
        for (int i = 0; i < dosis.length; i++) {
            if (dosis[i] == null) {
                Dosis d = new Dosis(tid, antal);
                dosis[i] = d;
                return d;
            }
        }
        return null;
    }

    @Override
    public double samletDosis() {
        double sum = 0;

        for (Dosis d : dosis) {
            if (d != null) {
                sum += d.getAntal();
            }
        }

        return sum;
    }

    @Override
    public double doegnDosis() {
        return samletDosis()/antalDage();
    }

    public Dosis[] getDoser() {
        return dosis;
    }

    @Override
    public String getType() {
        return "Dagligfast";
    }

}
