import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class IHDTest {

    Sensor sensor = new Sensor();
    IHD ihd_test = new IHD();

    @Test
    void inputBoundariesMinusOne() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = -1;
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead1());
    }

    @Test
    void inputBoundariesZero() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = 0;
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead1());
    }

    @Test
    void inputBoundariesOne() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = 1;
        ihd_test.execute();
        assertEquals(1, ihd_test.getRead1());
    }

    @Test
    void inputBoundariesOneHundredNinetyNine() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = 199;
        ihd_test.execute();
        assertEquals(199, ihd_test.getRead1());
    }

    @Test
    void inputBoundariesTwoHundred() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = 200;
        ihd_test.execute();
        assertEquals(200, ihd_test.getRead1());

    }

    @Test
    void inputBoundariesTwoHundredOne() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = 201;
        ihd_test.execute();
        assertEquals(200, ihd_test.getRead1());

    }

    @Test
    void inputLowerLimitMinusOneHundredFifty() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = -150;
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead1());

    }

    @Test
    void inputUpperLimitFourHundred() {
        ihd_test.initialize(sensor);
        sensor.originalOutput = 400;
        ihd_test.execute();
        assertEquals(200, ihd_test.getRead1());

    }

    @Test
    void inputMissingOriginalInput() {
        ihd_test.initialize(sensor);
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead1());

    }

    @Test
    void inputMissingSafetyInput() {
        ihd_test.initialize(sensor);
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead2());

    }

    @Test
    void inputBoundariesMinusOne2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = -1;
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead2());
    }

    @Test
    void inputBoundariesZero2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = 0;
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead2());
    }

    @Test
    void inputBoundariesOne2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = 1;
        ihd_test.execute();
        assertEquals(1, ihd_test.getRead2());
    }

    @Test
    void inputBoundariesOneHundredNinetyNine2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = 199;
        ihd_test.execute();
        assertEquals(199, ihd_test.getRead2());
    }

    @Test
    void inputBoundariesTwoHundred2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = 200;
        ihd_test.execute();
        assertEquals(200, ihd_test.getRead2());

    }

    @Test
    void inputBoundariesTwoHundredOne2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = 201;
        ihd_test.execute();
        assertEquals(200, ihd_test.getRead2());

    }

    @Test
    void inputLowerLimitMinusOneHundredFifty2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = -150;
        ihd_test.execute();
        assertEquals(0, ihd_test.getRead2());

    }

    @Test
    void inputUpperLimitFourHundred2() {
        ihd_test.initialize(sensor);
        sensor.safetyOutput = 400;
        ihd_test.execute();
        assertEquals(200, ihd_test.getRead2());

    }

    @Test
    void integerAverageOddResult () {
        ihd_test.initialize(sensor);
//        sensor.originalOutput = 149;
//        sensor.safetyOutput= 58;
        ihd_test.execute();
        assertEquals(103, ihd_test.mean(149,58));

    }

    @Test
    void integerAverageEvenResult () {
        ihd_test.initialize(sensor);
//        sensor.originalOutput = 149;
//        sensor.safetyOutput= 59;
        ihd_test.execute();
        assertEquals(104, ihd_test.mean(149,59));

    }
    @Test
    void integerAverageRandomResult () {
        ihd_test.initialize(sensor);
//        sensor.originalOutput = 149;
//        sensor.safetyOutput= 56;
        ihd_test.execute();
        assertEquals(102, ihd_test.mean(149,56));

    }
    @Test
    void integerAverageBoundariesResult () {
        ihd_test.initialize(sensor);
        ihd_test.execute();
        assertEquals(0, ihd_test.mean(0,1));

    }
    @Test
    void integerAverageBoundariesResult2 () {
        ihd_test.initialize(sensor);
        ihd_test.execute();
        assertEquals(199, ihd_test.mean(199,200));

    }
   @Test
    void sendOutput () {
        ihd_test.initialize(sensor);
        ihd_test.mean(38,59);
        ihd_test.execute();
        assertEquals(48, ihd_test.getOutput());

    }

    @Test
    void startSensor() {
        int[] expected = {0, 0, 0, 3};
        int[] actual = new int[4];
        ihd_test.initialize(sensor);
        actual[0] = ihd_test.getRead1();
        actual[1] = ihd_test.getRead2();
        actual[2] = ihd_test.getOutput();
        actual[3] = ihd_test.outputToSensor;
        assertArrayEquals(expected, actual);
    }

    @Test void requestInput() {
        int[] expected = {49, 35, 0};
        int[] actual = new int[3];
        sensor.originalOutput = 49;
        sensor.safetyOutput = 35;
        ihd_test.initialize(sensor);
        ihd_test.execute();
        actual[0] = ihd_test.getRead1();
        actual[1] = ihd_test.getRead2();
        actual[2] = ihd_test.outputToSensor;
        assertArrayEquals(expected, actual);

    }

    @Test
    void requestSwitchOff() {
        ihd_test.initialize(sensor);
        ihd_test.turnOfSensor();
        assertEquals(2,ihd_test.outputToSensor);
    }

    @Test
    void requestStartSensor() {
        ihd_test.startSensor();
        assertEquals(3,ihd_test.outputToSensor);
    }
}
