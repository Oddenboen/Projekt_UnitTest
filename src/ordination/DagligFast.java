package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {

    private Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDate, LocalDate slutDate) {
        super(startDate,slutDate);
    }

    public void opretDosis(double antalMorgen, double antalMiddag, double antalAften, double antalNat) {
        if (antalMorgen < 0 || antalMiddag < 0 || antalAften < 0 || antalNat < 0) {
            throw new IllegalArgumentException("Værdier kan ikke være negative");
        }
        if (antalMorgen + antalMiddag + antalAften + antalNat == 0) {
            throw new IllegalArgumentException("Sammlede antal doser skal være større end 0");
        }
        Dosis doseMorgen = new Dosis(LocalTime.of(8,00),antalMorgen);
        Dosis doseMiddag = new Dosis(LocalTime.of(12,00),antalMiddag);
        Dosis doseAften = new Dosis(LocalTime.of(18,00),antalAften);
        Dosis doseNat = new Dosis(LocalTime.of(23,59),antalNat);

        doser[0] = doseMorgen;
        doser[1] = doseMiddag;
        doser[2] = doseAften;
        doser[3] = doseNat;
    }

    public Dosis[] getDoser() {
        return doser;
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
        return "Daglig fast";
    }
}
