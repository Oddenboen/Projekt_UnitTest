package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination {

    private double antalEnheder;
    private ArrayList<LocalDate> givDatoer = new ArrayList<>();

    public PN(LocalDate startDen, LocalDate slutDen, double antalEnheder) {
        super(startDen, slutDen);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if (!givesDen.isBefore(getStartDen()) && !givesDen.isAfter(getSlutDen())){
            givDatoer.add(givesDen);
            return true;
        }
        return false;   
    }

    public double doegnDosis() {
        return samletDosis() / antalDage();
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return antalEnheder * getAntalGangeGivet();
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return givDatoer.size();
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
