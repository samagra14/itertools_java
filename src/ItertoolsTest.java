import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItertoolsTest {

    public static void main(String[] args) {

        new ItertoolsTest().chainTest();
        new ItertoolsTest().cycleTest();
        new ItertoolsTest().compressTest();
        new ItertoolsTest().ifilterTest();
        new ItertoolsTest().takewhileTest();
        new ItertoolsTest().dropwhileTest();
    }

    public void cycleTest() {
        int i = 4;
        for (String s : Itertools.cycle(Arrays.asList("Shanu", "Sammy", "Samagra", "Bhanu"))) {
            System.out.println(s);
            i++;
            if (i > 100)
                break;
        }
    }

    public void chainTest() {
        String[] strings = { "Hello", "World", "Samagra", "Sammy", "P", "NP", "PSPACE" };
        int i = 0;
        for (String s : Itertools.chain(Arrays.asList(strings[0], strings[1], strings[2]), Arrays.asList(),
                Arrays.asList(strings[3], strings[4], strings[5], strings[6]))) {
            System.out.println(s);
            i++;
        }
    }

    public void compressTest() {
        String[] data = { "Hello", "World", "Samagra", "Sammy", "P", "NP", "PSPACE" };
        Boolean[] selector = { true, true, true, false, false, false, true };
        int i = 0;
        for (String s : Itertools.compress(Arrays.asList(data), Arrays.asList(selector))) {
            System.out.println(s);
            i++;
        }
    }

    public void dropwhileTest() {
        int i = 0;
        for (int a : Itertools.dropWhile(integer -> integer < 5, new ArrayList<>(Arrays.asList(1, 5, 6, 4, 1)))) {
            System.out.println(a);
            i++;
        }
    }

    public void ifilterTest() {
        int i = 0;
        for (int a : Itertools.ifilter(integer -> (integer % 2) == 0, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))) {
            System.out.println(a);
            i++;
        }
    }

    public void takewhileTest() {
        int i = 0;
        for (int a : Itertools.takeWhile(integer -> integer < 4, new ArrayList<>(Arrays.asList(1, 4, 6, 4, 1)))) {
            System.out.println(a);
            i++;
        }
    }
}
