package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    Patient jane;
    Laegemiddel laegemiddel;
    LocalDate start;
    LocalDate slut;
    DagligFast ordination;
    int morgenAntal;
    int middagAntal;
    int aftenAntal;
    int natAntal;

    @BeforeEach
    void setup() throws Exception {
        jane = new Patient("121256-0512", "Jane Jensen", 63.4);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        start = LocalDate.of(2026, 3, 1);
        slut = LocalDate.of(2026, 4, 1);
        ordination = new DagligFast(start, slut);
        ordination.setLaegemiddel(laegemiddel);
        morgenAntal = 0;
        middagAntal = 0;
        aftenAntal = 0;
        natAntal = 0;
    }

    @Test
    void TC1_Alle0() {
        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(morgenAntal,middagAntal,aftenAntal,natAntal);
        });
    }

    @Test
    void TC2_AlleNegative() {
        morgenAntal = -1;
        middagAntal = -1;
        aftenAntal = -1;
        natAntal = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(morgenAntal,middagAntal,aftenAntal,natAntal);
        });
    }

    @Test
    void TC3_antalMorgenNegativ() {
        morgenAntal = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(morgenAntal,middagAntal,aftenAntal,natAntal);
        });
    }

    @Test
    void TC4_antalMiddagNegativ() {
        middagAntal = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(morgenAntal,middagAntal,aftenAntal,natAntal);
        });
    }

    @Test
    void TC5_antalAftenNegativ() {
        aftenAntal = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(morgenAntal,middagAntal,aftenAntal,natAntal);
        });
    }

    @Test
    void TC6_antalNatNegativ() {
        natAntal = -1;

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(morgenAntal,middagAntal,aftenAntal,natAntal);
        });
    }

    @Test
    void TC7_AllePositive() {
        morgenAntal = 1;
        middagAntal = 2;
        aftenAntal = 3;
        natAntal = 4;

        ordination.opretDosis(morgenAntal,middagAntal,aftenAntal,natAntal);

        Dosis[] doser = ordination.getDoser();

        assertEquals(morgenAntal,doser[0].getAntal());
        assertEquals(middagAntal,doser[1].getAntal());
        assertEquals(aftenAntal,doser[2].getAntal());
        assertEquals(natAntal,doser[3].getAntal());
    }
}