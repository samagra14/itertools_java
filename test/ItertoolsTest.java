import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ItertoolsTest {
    @Test
    public void countTest(){
        int i = -5;
        for (int a :
                Itertools.count(-5)) {
            Assertions.assertEquals(i,a);
            i++;
            if(i>100) break;
        }
        i = -5;
        for (int a :
                Itertools.count(-5,5)) {
            Assertions.assertEquals(i,a);
            i+=5;
            if(i>100) break;
        }
    }

    @Test
    public void cycleTest(){
        int i = 4;
        for (String s :
                Arrays.asList("Shanu","Sammy","Samagra","Bhanu")) {
            if (i%4==0)
                Assertions.assertEquals("Shanu",s);
            if (i%4==1)
                Assertions.assertEquals("Sammy",s);
            if (i%4==2)
                Assertions.assertEquals("Samagra",s);
            if (i%4==3)
                Assertions.assertEquals("Bhanu",s);
            i++;
            if(i>100)
                break;
        }
    }

    @Test
    public void repeatTest(){
        int i = 0;
        String s = "Hello World";
        for (String str :
                Itertools.repeat("Hello World")) {
            Assertions.assertEquals(s,str);
            i++;
            if(i>20)
                break;
        }
        i = 0;
        for (String str :
                Itertools.repeat("Hello World",10)) {
            Assertions.assertEquals(s,str);
            Assertions.assertTrue(i<10);
            i++;
        }
    }

    @Test
    public void chainTest(){
        String[] strings = {"Hello","World","Samagra","Sammy","P","NP","PSPACE"};
        int i =0;
        for (String s :
                Itertools.chain(Arrays.asList(strings[0],strings[1],
                        strings[2]),Arrays.asList(),Arrays.asList(
                                strings[3],strings[4],strings[5],strings[6]
                ))) {
            Assertions.assertEquals(strings[i],s);
            i++;
        }
    }

    @Test
    public void compressTest(){
        String[] data = {"Hello","World","Samagra","Sammy","P","NP","PSPACE"};
        Boolean[] selector = {false,true,true,false,false,false,true};
        int i =0;
        for (String s :
                Itertools.compress(Arrays.asList(data), Arrays.asList(selector))) {
            if(i==0)
            Assertions.assertEquals("World",s);
            if(i==1)
                Assertions.assertEquals("Samagra",s);
            if(i==2 )
                Assertions.assertEquals("PSPACE",s);
            i++;
        }
    }

    @Test
    public void dropwhileTest(){
        int i = 0;
        for (int a :
                Itertools.dropWhile(integer -> integer<5,new ArrayList<>(Arrays.asList(1,4,6,4,1)))){
            if(i==0)
                Assertions.assertEquals(6,a);
            if(i==1)
                Assertions.assertEquals(4,a);
            if(i==2)
                Assertions.assertEquals(1,a);
            i++;
        }
    }
}