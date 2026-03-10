package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination {

    private double antalEnheder = 0;
    private int antalGangeGivet = 0;
    private LocalDate foersteGivning;
    private List<LocalDate> dageGivet = new ArrayList<>();

    public PN(LocalDate startDate, LocalDate slutDate, double antal) {
        super(startDate, slutDate);
        antalEnheder = antal;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     *
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (!givesDen.isBefore(getStartDen()) && !givesDen.isAfter(getSlutDen())) {
            antalGangeGivet++;
            if (foersteGivning == null || foersteGivning.isAfter(givesDen)) {
                foersteGivning = givesDen;
            }
            dageGivet.add(givesDen);
            return true;
        }
        throw new IllegalArgumentException("Dato er ude for Ordinationen");
    }

    public double doegnDosis() {
        if (dageGivet.isEmpty()) {
            return (getAntalGangeGivet() * getAntalEnheder()) / (ChronoUnit.DAYS.between(LocalDate.of(2026, 05, 01), LocalDate.of(2026, 05, 07)));
        }

        LocalDate senesteGivning = dageGivet.getLast();
        for (LocalDate datoGivet : dageGivet) {
            if (senesteGivning.isBefore(datoGivet)) {
                senesteGivning = datoGivet;
            }
        }
        if (senesteGivning == foersteGivning) {
            return getAntalGangeGivet() * getAntalEnheder();
        }
        return (getAntalGangeGivet() * getAntalEnheder()) / (ChronoUnit.DAYS.between(foersteGivning, senesteGivning));
    }


    public double samletDosis() {
        if (getAntalEnheder() < 0) {
            throw new IllegalArgumentException();
        }
        return getAntalGangeGivet() * getAntalEnheder();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     *
     * @return
     */
    public int getAntalGangeGivet() {
        return antalGangeGivet;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    @Override
    public String getType() {
        return "PN";
    }
}
