package controller;

import ordination.DagligSkaev;
import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ControllerOpretDaligSkaevTest {

    Controller controller;
    Patient patient;
    Laegemiddel laegemiddel;
    LocalDate start;
    LocalDate slut;
    LocalTime[] klokkeslaet;
    double[] antalEnheder;


    @BeforeEach
    void setup() {
        patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        controller = Controller.getController();
        start = LocalDate.of(2026, 1, 1);
        slut = LocalDate.of(2026, 1, 11);
        klokkeslaet = new LocalTime[3];
        antalEnheder = new double[3];
    }

    @Test
    void TC1_giverEtResultat() {
        klokkeslaet[0] = LocalTime.of(10, 30);
        klokkeslaet[1] = LocalTime.of(10, 30);
        klokkeslaet[2] = LocalTime.of(10, 35);

        antalEnheder[0] = 5;
        antalEnheder[1] = 5;
        antalEnheder[2] = 1;

        DagligSkaev dagligSkaev = controller.opretDagligSkaevOrdination(start, slut, patient, laegemiddel, klokkeslaet, antalEnheder);
        double expected = 11;
        double actual = dagligSkaev.doegnDosis();

        assertEquals(expected, actual);
        assertEquals(121, dagligSkaev.samletDosis());
    }

    @Test
    void TC2_SlutFoerStart() {
        start = LocalDate.of(2026, 1, 2);
        slut = LocalDate.of(2026, 1, 1);

        assertThrows(IllegalArgumentException.class, () -> {
            controller.opretDagligSkaevOrdination(start, slut, patient, laegemiddel, klokkeslaet, antalEnheder);
        });

    }


}