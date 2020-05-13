package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ClosestIntSameWeight {
  @EpiTest(testDataFile = "closest_int_same_weight.tsv")
  public static long closestIntSameBitCount(long x) {

    //1. check the first bit
    //2. IF 0:
    //      find LSB and its pos
    //      pos2 = findMax(pos -1, 0)
    //      swap
    // 3. IF 1:
    //      find the position of next 0
    //      pos2 = findMax(0, pos - 1)
    //      swap
    Integer test = 5;
    int testBit = 1 & (int) ~(x & 1); //opposite of the bit0
    for (int i = 1; i < 64; i++) {
      if ((x >>> i & 1) == testBit) { //found the test-bit
        //swap and return
        return swap(x, i, Math.max(i-1, 0));
      }
    }
    // TODO - you fill in here.
    //[1001 0101] => [1001 0110]
    return 0;
  }

  private static long swap(long x, int i, int j) {
    //make sure they are different
    if (i > 63 || j > 63 || i < 0 || j < 0 || i == j) {
      throw new IllegalArgumentException(String.format("unable to swap: i %d, j: %d", i, j));
    }
    if ((x >>> i & 1) == (x >>> j & 1)) {
      //should not happen either
      throw new IllegalArgumentException(
              String.format("should not try to swap when equal: i %d, j: %d, val: %d", i, j, (x >>> i) & 1));
//      return x; //should not happen either
    }

    long swapMask = (1 << i) | (1 << j);

    return x ^ swapMask;

  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "ClosestIntSameWeight.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
