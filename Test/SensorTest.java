import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTest {

    Sensor sensor_test = new Sensor();


    @Test
    void updateStatus0() {
            sensor_test.updateStatus(0);
            assertEquals(0, sensor_test.startRestartPin);
        }

    @Test
    void updateStatus1() {
        sensor_test.updateStatus(1);
        assertEquals(1, sensor_test.startRestartPin);
    }
    @Test
    void updateStatus2() {
        sensor_test.updateStatus(2);
        assertEquals(2, sensor_test.startRestartPin);
    }
    @Test
    void updateStatus3() {
        sensor_test.updateStatus(3);
        assertEquals(3, sensor_test.startRestartPin);
    }


}