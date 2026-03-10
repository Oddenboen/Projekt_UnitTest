package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PNGivDosisTest {


    Patient jane;
    Laegemiddel laegemiddel;
    LocalDate start;
    LocalDate slut;
    PN ordination;

    @BeforeEach
    void setup() throws Exception {
        jane = new Patient("121256-0512", "Jane Jensen", 63.4);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        start = LocalDate.of(2026,3,1);
        slut = LocalDate.of(2026,4,1);
        ordination = new PN(start,slut,5);
        ordination.setLaegemiddel(laegemiddel);
    }


    @Test
    void TC1_foerStartDato() {
        LocalDate førStartDato = LocalDate.of(2026,2,1);

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.givDosis(førStartDato);
        });
    }

    @Test
    void TC2_startDag() {
        boolean expected = ordination.givDosis(start);
        assertTrue(expected);
    }

    @Test
    void TC3_slutDag() {
        boolean expected = ordination.givDosis(slut);
        assertTrue(expected);
    }

    @Test
    void TC4_efterSlutDag() {
        assertThrows(IllegalArgumentException.class, () -> {
            ordination.givDosis(LocalDate.of(2026,4,2));
        });
    }

    @Test
    void TC5_2GangeSammeDag() {
        boolean expected = ordination.givDosis(LocalDate.of(2026,3,15));
        expected = ordination.givDosis(LocalDate.of(2026,3,15));
        assertTrue(expected);
    }

}
