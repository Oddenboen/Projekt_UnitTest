import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class Tests {


    Patient jane;
    Laegemiddel laegemiddel;
    LocalDate start;
    LocalDate slut;

    @BeforeEach
    void setup() throws Exception {
        jane = new Patient("121256-0512", "Jane Jensen", 63.4);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        start = LocalDate.of(2026,3,1);
        slut = LocalDate.of(2026,4,1);
    }


    @Test
    void test_givDosis() {
        //Arrange


        //Act

        //Assert

    }
}
