package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerAnbefaletDosisPrDoegnTest {

    Controller controller;
    Patient patient;
    Laegemiddel laegemiddel;
    double vægt;

    @BeforeEach
    void setup() {
        patient = new Patient("121256-0512", "Jane Jensen", vægt);
        laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
        controller = Controller.getController();
    }

    @Test
    void TC1_vaegtUnder25() {
        patient.setVaegt(24.9);
        double expected = laegemiddel.getEnhedPrKgPrDoegnLet();
        double actual = controller.anbefaletDosisPrDoegn(patient, laegemiddel);

        assertEquals(expected, actual);
    }

    @Test
    void TC2_vaegtEr25() {
        patient.setVaegt(25);
        double expected = laegemiddel.getEnhedPrKgPrDoegnNormal();
        double actual = controller.anbefaletDosisPrDoegn(patient, laegemiddel);

        assertEquals(expected, actual);
    }

    @Test
    void TC3_vaegtEr120() {
        patient.setVaegt(120);
        double expected = laegemiddel.getEnhedPrKgPrDoegnNormal();
        double actual = controller.anbefaletDosisPrDoegn(patient, laegemiddel);

        assertEquals(expected, actual);
    }

    @Test
    void TC4_vaegtOver120() {
        patient.setVaegt(120.1);
        double expected = laegemiddel.getEnhedPrKgPrDoegnTung();
        double actual = controller.anbefaletDosisPrDoegn(patient, laegemiddel);

        assertEquals(expected, actual);
    }
}