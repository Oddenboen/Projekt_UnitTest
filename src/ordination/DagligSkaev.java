package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DagligSkaev extends Ordination {

    private List<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDate, LocalDate slutDate) {
        super(startDate,slutDate);
    }

    public Dosis opretDosis(LocalTime tid, double antal) {
        if (antal < 0) {
            throw new IllegalArgumentException("Antal kan ikke være negativt");
        }
        if (doser.size() != 0) {
            for (Dosis dose : doser) {
                if (tid != dose.getTid()) {
                    Dosis nyDose = new Dosis(tid, antal);
                    if (!doser.contains(nyDose)) {
                        doser.add(nyDose);
                        return nyDose;
                    }
                }
            }
        }
        Dosis dosis = new Dosis(tid,antal);
        doser.add(dosis);
        return dosis;
//        throw new IllegalArgumentException("Der kan ikke oprettets to doser på samme tidspunkt");
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
