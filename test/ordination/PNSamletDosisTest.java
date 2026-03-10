package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PNSamletDosisTest {


    Patient jane;
    Laegemiddel laegemiddel;
    LocalDate start;
    LocalDate slut;
    PN ordination1;
    PN ordination2;

    @BeforeEach
    void setup() throws Exception {
        jane = new Patient("121256-0512", "Jane Jensen", 63.4);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        start = LocalDate.of(2026, 3, 1);
        slut = LocalDate.of(2026, 4, 1);
        ordination1 = new PN(start, slut, 0.01);
        ordination1.setLaegemiddel(laegemiddel);
        ordination2 = new PN(start, slut, -1);
        ordination2.setLaegemiddel(laegemiddel);
    }

    @Test
    void TC1_givet0EnhederPositiv() {
        //ordination1.givDosis(start);

        double actual = ordination1.samletDosis();

        assertEquals(0, actual);
    }

    @Test
    void TC2_givet1EnhedPositiv() {
        ordination1.givDosis(start);

        double actual = ordination1.samletDosis();

        assertEquals(0.01, actual);
    }

    @Test
    void TC3_givet0EnhederNegativ() {
        assertThrows(IllegalArgumentException.class, () -> {
            ordination2.samletDosis();
        });
    }


    @Test
    void TC4_givet1EnhedNegativ() {
        ordination2.givDosis(start);

        assertThrows(IllegalArgumentException.class, () -> {
            ordination2.samletDosis();
        });
    }

    @Test
    void TC5_givet5EnhederPositiv() {
        ordination1.givDosis(start);
        ordination1.givDosis(start);
        ordination1.givDosis(start);
        ordination1.givDosis(start);
        ordination1.givDosis(start);

        double expected = 0.05;
        double actual = ordination1.samletDosis();

        assertEquals(expected, actual);
    }


}
