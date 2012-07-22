/**
 * Given a function which generates a random integer in the range 1 to 7,
 * write a function which generates a random integer in the range 1 to 10 uniformly.
 * <p/>
 *    1  2  3  4  5  6  7
 * 1  1  2  3  4  5  6  7
 * 2  8  9 10  1  2  3  4
 * 3  5  6  7  8  9 10  1
 * 4  2  3  4  5  6  7  8
 * 5  9 10  1  2  3  4  5
 * 6  6  7  8  9 10  *  *
 * 7  *  *  *  *  *  *  *
 * <p/>
 * <p/>
 * http://en.wikipedia.org/wiki/Rejection_sampling
 * <p/>
 * The main idea is when you generate a number in the desired range,
 * output that number immediately. If the number is out of the desired range,
 * reject it and re-sample again. As each number in the desired range has
 * the same probability of being chosen, a uniform distribution is produced.
 */
public class Rand {
    public static int rand7() {
        // the higher of factor of random, the higher of precision of random 7
        return (int) (Math.random() * 100) % 7;
    }

    public static int rand10() {
        while (true) {
            int row = rand7() * 7;
            int col = rand7();
            int index = col + row;
            if (index < 40) {
                return index % 10;
            }
        }
    }

    public static void main(String[] args) {
        /* Test: call rand10 many times and inspect the results. */
        int[] arr = new int[10];
        int test_size = 1000000;
        for (int k = 0; k < test_size; k++) {
            arr[rand10()]++;
        }

        for (int i = 0; i < 10; i++) {
            double percent = 100.0 * arr[i] / test_size;
            System.out.println(i + " appeared " + percent + "% of the time.");
        }
    }
}
