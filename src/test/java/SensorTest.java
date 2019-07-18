import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SensorTest {

  private Sensor sensor;

  @Before
  public void createSensor() {

    sensor = new Sensor(1, 10, new int[]{-2, 8, 5, 3, 8, 8, 1, 15});

  }

  @Test
  public void TestInstance() {
    assertEquals(sensor.getLower(), 1, 0);
    assertEquals(sensor.getHigher(), 10, 0);
    assertEquals(sensor.getOutput().length, 6, 0);
    assertEquals(sensor.toString(), "1,3,5,8,8,8");

  }

  @Test
  public void TestMean() {

    double meanResult = sensor.findMean();

    assertEquals(5.5, meanResult, 0);

  }

  @Test
  public void TestMedian1() {

    double medianResult = sensor.findMedian();

    assertEquals(6.5, medianResult, 0);

  }

  @Test
  public void TestMedian2() {

    sensor = new Sensor(1, 10, new int[]{-2, 8, 5, 3, 8, 1, 15});

    double medianResult = sensor.findMedian();

    assertEquals(5, medianResult, 0);

  }

  @Test
  public void TestMode() {

    int modeResult = sensor.findMode();

    assertEquals(8, modeResult, 0);

  }

}
