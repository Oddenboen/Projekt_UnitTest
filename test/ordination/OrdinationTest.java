package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {

    LocalDate start;
    LocalDate slut;

    @BeforeEach
    void setup() {
        start = LocalDate.of(2026, 3, 1);
        slut = LocalDate.of(2026, 3, 1);
    }

    @Test
    void TC1_pnDato() {
        int antal = 1;
        PN ordination = new PN(start, slut, antal);

        int expected = 1;
        int actual = ordination.antalDage();

        assertEquals(expected, actual);
    }

    @Test
    void TC2_daligSkaevDato() {
        DagligSkaev ordination = new DagligSkaev(start,slut);

        int expected = 1;
        int actual = ordination.antalDage();

        assertEquals(expected, actual);
    }

    @Test
    void TC3_daligFastDato() {
        DagligFast ordination = new DagligFast(start,slut);

        int expected = 1;
        int actual = ordination.antalDage();

        assertEquals(expected, actual);
    }

    @Test
    void TC4_daligNegativ() {
        LocalDate forkertStartDato = LocalDate.of(2026,3,2);

        assertThrows(IllegalArgumentException.class, () -> {
            DagligFast ordination = new DagligFast(forkertStartDato,slut);
        });

    }
}