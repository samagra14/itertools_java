import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItertoolsTest {

    @Test
    public void isliceTest() {
        int i = 0;
        for (int a :
                Itertools.islice(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9), 5, 2)) {
            if (i == 0)
                assertEquals(1, a);
            if (i == 1)
                assertEquals(3, a);
            if (i == 2)
                assertEquals(5, a);
            assertTrue(i < 3);
            i++;
        }
    }

    @Test
    public void imapTest() {
        int i = 0;
        for (double a :
                Itertools.imap((t) -> Math.pow(t.get(0), t.get(1)), Arrays.asList(2, 3, 10), Arrays.asList(5, 2, 3))) {
            if (i == 0)
                assertEquals(32, (int) a);
            if (i == 1)
                assertEquals(9, (int) a);
            if (i == 2) {
                assertEquals(1000, (int) a);
            }
            i++;
        }
    }

    @Test
    public void countTest() {
        int i = -5;
        for (int a :
                Itertools.count(-5)) {
            assertEquals(i, a);
            i++;
            if (i > 100) break;
        }
        i = -5;
        for (int a :
                Itertools.count(-5, 5)) {
            assertEquals(i, a);
            i += 5;
            if (i > 100) break;
        }
    }

    @Test
    public void cycleTest() {
        int i = 4;
        for (String s :
                Itertools.cycle(Arrays.asList("Shanu", "Sammy", "Samagra", "Bhanu"))) {
            if (i % 4 == 0)
                assertEquals("Shanu", s);
            if (i % 4 == 1)
                assertEquals("Sammy", s);
            if (i % 4 == 2)
                assertEquals("Samagra", s);
            if (i % 4 == 3)
                assertEquals("Bhanu", s);
            i++;
            if (i > 100)
                break;
        }
    }

    @Test
    public void repeatTest() {
        int i = 0;
        String s = "Hello World";
        for (String str :
                Itertools.repeat("Hello World")) {
            assertEquals(s, str);
            i++;
            if (i > 20)
                break;
        }
        i = 0;
        for (String str :
                Itertools.repeat("Hello World", 10)) {
            assertEquals(s, str);
            assertTrue(i < 10);
            i++;
        }
    }

    @Test
    public void chainTest() {
        String[] strings = {"Hello", "World", "Samagra", "Sammy", "P", "NP", "PSPACE"};
        int i = 0;
        for (String s :
                Itertools.chain(Arrays.asList(strings[0], strings[1],
                        strings[2]), Arrays.asList(), Arrays.asList(
                        strings[3], strings[4], strings[5], strings[6]
                ))) {
            assertEquals(strings[i], s);
            i++;
        }
    }

    @Test
    public void compressTest() {
        String[] data = {"Hello", "World", "Samagra", "Sammy", "P", "NP", "PSPACE"};
        Boolean[] selector = {false, true, true, false, false, false, true};
        int i = 0;
        for (String s :
                Itertools.compress(Arrays.asList(data), Arrays.asList(selector))) {
            if (i == 0)
                assertEquals("World", s);
            if (i == 1)
                assertEquals("Samagra", s);
            if (i == 2)
                assertEquals("PSPACE", s);
            i++;
        }
    }

    @Test
    public void dropwhileTest() {
        int i = 0;
        for (int a :
                Itertools.dropWhile(integer -> integer < 5, new ArrayList<>(Arrays.asList(1, 4, 6, 4, 1)))) {
            if (i == 0)
                assertEquals(6, a);
            if (i == 1)
                assertEquals(4, a);
            if (i == 2)
                assertEquals(1, a);
            i++;
        }
    }

    @Test
    public void ifilterTest() {
        int i = 0;
        for (int a :
                Itertools.ifilter(integer -> (integer % 2) != 0, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))) {
            if (i == 0) {
                assertEquals(1, a);
            }
            if (i == 1) {
                assertEquals(3, a);
            }
            if (i == 2) {
                assertEquals(5, a);
            }
            if (i == 3) {
                assertEquals(7, a);
            }
            if (i == 4) {
                assertEquals(9, a);
            }
            i++;
        }
    }

    @Test
    public void ifilterfalseTest() {
        int i = 0;
        for (int a :
                Itertools.ifilterfalse(integer -> (integer % 2) != 0, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))) {
            if (i == 0) {
                assertEquals(2, a);
            }
            if (i == 1) {
                assertEquals(4, a);
            }
            if (i == 2) {
                assertEquals(6, a);
            }
            if (i == 3) {
                assertEquals(8, a);
            }
            i++;
        }
    }

    @Test
    public void takewhileTest() {
        int i = 0;
        for (int a :
                Itertools.takeWhile(integer -> integer < 5, new ArrayList<>(Arrays.asList(1, 4, 6, 4, 1)))) {
            if (i == 0)
                assertEquals(1, a);
            if (i == 1)
                assertEquals(4, a);
            i++;
        }
    }

    @Test
    public void izipTest() {
        String[] listOne = {"A", "B", "C", "D"};
        String[] listTwo = {"x", "y"};
        int i = 0;
        for (List<String> list :
                Itertools.izip(Arrays.asList(listOne), Arrays.asList(listTwo))) {
            assertTrue(i < 2);
            assertEquals(listOne[i], list.get(0));
            assertEquals(listTwo[i], list.get(1));
            i++;
        }
    }

    @Test
    public void izipLongestTest() {
        String[] listOne = {"A", "B", "C", "D"};
        String[] listTwo = {"x", "y"};
        int i = 0;
        for (List<String> list :
                Itertools.izipLongest("filler", Arrays.asList(listOne), Arrays.asList(listTwo))) {
            assertTrue(i < 4);
            if (i < 2) {
                assertEquals(listOne[i], list.get(0));
                assertEquals(listTwo[i], list.get(1));
            } else {
                assertEquals(listOne[i], list.get(0));
                assertEquals("filler", list.get(1));
            }
            i++;
        }
    }

    @Test
    public void testProduct() {
        String[] str1 = {"alpha", "Beta", "gamma"};
        String[] str2 = {"a", "b"};
        String[] str3 = {"Samagra", "Sammy", "Shanu"};
        for (List<String> products :
                Itertools.product(Arrays.asList(str1), Arrays.asList(str2), Arrays.asList(str3))) {
            for (String s :
                    products) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testCombinations() {
        String[] str1 = {"alpha", "Beta", "gamma", "a", "b", "Samagra", "Sammy", "Shanu"};
        int i = 0;
        for (List<String> products :
                Itertools.combinations(Arrays.asList(str1), 4)) {
            for (String s :
                    products) {
                System.out.print(s + " ");
            }
            i++;
            System.out.println(i);
        }
    }

    @Test
    public void testPermutations() {
        String[] str1 = {"alpha", "Beta", "gamma", "a", "b", "Samagra"};
        int i = 0;
        for (List<String> products :
                Itertools.permutations(Arrays.asList(str1), 5)) {
            for (String s :
                    products) {
                System.out.print(s + " ");
            }
            i++;
            System.out.println(i);
        }
    }

}