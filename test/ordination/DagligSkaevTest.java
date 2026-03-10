package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    Patient jane;
    Laegemiddel laegemiddel;
    LocalDate start;
    LocalDate slut;
    DagligSkaev ordination;
    int antal;
    LocalTime tid;

    @BeforeEach
    void setup() throws Exception {
        jane = new Patient("121256-0512", "Jane Jensen", 63.4);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        start = LocalDate.of(2026,3,1);
        slut = LocalDate.of(2026,4,1);
        ordination = new DagligSkaev(start,slut);
        ordination.setLaegemiddel(laegemiddel);
        tid = LocalTime.of(8,00);
    }

    @Test
    void TC1_Antal1() {
        antal = 1;
        Dosis dose = ordination.opretDosis(tid,antal);

        List<Dosis> doser = ordination.getDoser();

        assertEquals(1,doser.size());
        assertEquals(dose,doser.getFirst());
        assertEquals(tid,dose.getTid());
        assertEquals(antal,dose.getAntal());
    }

    @Test
    void TC2_Antal0() {
        antal = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(tid,antal);
        });
    }

    @Test
    void TC3_antal1Gentages() {
        antal = 1;
        Dosis dose1 = ordination.opretDosis(tid,antal);
        Dosis dose2 = ordination.opretDosis(tid,antal);

        List<Dosis> doser = ordination.getDoser();

        assertEquals(2,doser.size());
        assertEquals(dose1,doser.get(0));
        assertEquals(dose2,doser.get(1));
        assertEquals(tid,dose1.getTid());
        assertEquals(antal,dose1.getAntal());
        assertEquals(tid,dose2.getTid());
        assertEquals(antal,dose2.getAntal());
    }

    @Test
    void TC4_antal0Gentages() {
        antal = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(tid,antal);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            ordination.opretDosis(tid,antal);
        });
    }

}