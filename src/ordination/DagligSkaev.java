package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligSkaev extends Ordination {

    List<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDate, LocalDate slutDate) {
        super(startDate,slutDate);
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dose = new Dosis(tid, antal);
        if (!doser.contains(dose)) {
            doser.add(dose);
        }
    }

    public List<Dosis> getDoser() {
        return new ArrayList<>(doser);
    }

    @Override
    public double samletDosis() {
        double dagsDosis = doegnDosis();
        return dagsDosis * antalDage();
    }

    @Override
    public double doegnDosis() {
        double doegnDosis = 0;
        for (Dosis dose : doser) {
            if (dose != null) {
                doegnDosis += dose.getAntal();
            }
        }
        return doegnDosis;
    }

    @Override
    public String getType() {
        return "Daglig Skæv";
    }
}
