package ordination;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PN extends Ordination{

    private double antalEnheder = 0;
    private int antalGangeGivet = 0;
    private LocalDate foersteGivning;
    private List<LocalDate> dageGivet = new ArrayList<>();

    public PN(LocalDate startDate, LocalDate slutDate, double antal) {
        super(startDate,slutDate);
        antalEnheder = antal;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (givesDen.isAfter(getStartDen()) && givesDen.isBefore(getSlutDen())) {
            antalGangeGivet++;
            if (foersteGivning == null) {
                foersteGivning = LocalDate.now();
            }
            dageGivet.add(LocalDate.now());
            return true;
        }
        throw new IllegalArgumentException("Dato er ude for Ordinationen");
    }

    public double doegnDosis() {
        LocalDate senesteGivning = dageGivet.getLast();
        return (getAntalGangeGivet() * getAntalEnheder()) / (ChronoUnit.DAYS.between(foersteGivning,senesteGivning));
    }


    public double samletDosis() {
        return getAntalGangeGivet() * getAntalEnheder();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
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
