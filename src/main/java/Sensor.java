import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class Sensor {
  private int lower;
  private int higher;
  private int[] buffer;

  private int length;
  private int[] filtered;
  private ArrayList<Integer> arrayList;

  Sensor(int lower, int higher, int[] buffer) {
      Logger log = Logger.getLogger(Sensor.class.getName());

     // construct an array list
     arrayList = new ArrayList<Integer>();

     // filter out elements that are out of bounds
     for (int val : buffer) {
       if (val >= lower && val <= higher) {
         arrayList.add(val);
       }
     }

    // Sort the arrayList
    Collections.sort(arrayList);
    log.info("arrayList is: " + arrayList.toString());

    this.length = arrayList.size();
    this.filtered = new int[this.length];
    this.lower = lower;
    this.higher = higher;
    this.buffer = buffer;
  }

  public double findMean() {
    double mean = 0;

    // no streams
    // double sum = 0;
    // for (int i : arrayList) {
    //   sum += i;
    // }

    // streams
    double sum = arrayList.stream().reduce(0, (a, b) -> a + b);
    mean = sum / length;
    return mean;
  }

  public double findMedian() {
    if (length % 2 == 0) { // avg of center-most 2 if even-lengthed
      int rightIdx = length / 2;
      int leftIdx = rightIdx - 1;

      int leftVal = arrayList.get(leftIdx);
      int rightVal = arrayList.get(rightIdx);
      double median = (leftVal + rightVal) / 2.0;
      return median;
    } else { // middle value if odd-lengthed
      int midIdx = length / 2;
      double median = arrayList.get(midIdx);
      return median;
    }
  }

  // the number that occurs the most often
  public int findMode() {
    HashMap<Integer, Integer> modeMap = new HashMap<Integer, Integer>();

    int occurance = 1;
    for (int key : arrayList) {
      if (modeMap.containsKey(key)) {
        occurance++;
      }
      modeMap.put(key, occurance);
    }

    // get the maxValue in the HashMap
    int maxValue = Collections.max(modeMap.values());

    int maxKey = arrayList.get(0);
    // iterate through the map to find the key associated w/ the maxValue
    for (Entry<Integer, Integer> entry : modeMap.entrySet()) {
      if (entry.getValue() == maxValue) {
        maxKey = entry.getKey();
      }
    }

    return maxKey;
  }

  public int getLower() {
    return lower;
  }

  public int getHigher() {
    return higher;
  }

  public int[] getBuffer() {
    return buffer;
  }

  public int[] getOutput() {
    return filtered;
  }

  public String toString() {
    return arrayList.stream().map(Object::toString).collect(Collectors.joining(","));
  }
}
