/**
 * @author yuzhen_lin
 * @version Id: UnitTest, v 0.1 2021-05-30 10:09 PM yuzhen_lin Exp $
 */

import com.test.Cal;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {


    @Test
    public void example1(){
        Cal cal = new Cal();
        cal.readInput("5 2");
        Assert.assertTrue(cal.getPrintData().equals("stack: 5 2"));

    }

    @Test
    public void example2(){

        Cal cal = new Cal();
        cal.readInput("2 sqrt");
        Assert.assertTrue(cal.getPrintData().equals("stack: 1.4142135623"));
        cal.readInput("clear 9 sqrt");
        Assert.assertTrue(cal.getPrintData().equals("stack: 3"));


    }

    @Test
    public void example3(){
        Cal cal = new Cal();
        cal.readInput("5 2 -");
        Assert.assertTrue(cal.getPrintData().equals("stack: 3"));

        cal.readInput("3 -");
        Assert.assertTrue(cal.getPrintData().equals("stack: 0"));

        cal.readInput("clear");
        Assert.assertTrue(cal.getPrintData().equals("stack:"));

    }

    @Test
    public void example4(){
        Cal cal = new Cal();
        cal.readInput("5 4 3 2");
        Assert.assertTrue(cal.getPrintData().equals("stack: 5 4 3 2"));


        cal.readInput("undo undo *");
        Assert.assertTrue(cal.getPrintData().equals("stack: 20"));
        cal.readInput("5 *");
        Assert.assertTrue(cal.getPrintData().equals("stack: 100"));
        cal.readInput("undo");
        Assert.assertTrue(cal.getPrintData().equals("stack: 20 5"));

    }


    @Test
    public void example5(){
        Cal cal = new Cal();
        cal.readInput("7 12 2 /");
        Assert.assertTrue(cal.getPrintData().equals("stack: 7 6"));
        cal.readInput("*");
        Assert.assertTrue(cal.getPrintData().equals("stack: 42"));
        cal.readInput("4 /");
        Assert.assertTrue(cal.getPrintData().equals("stack: 10.5"));

    }


    @Test
    public void example6(){
        Cal cal = new Cal();
        cal.readInput("1 2 3 4 5");
        Assert.assertTrue(cal.getPrintData().equals("stack: 1 2 3 4 5"));
        cal.readInput("*");
        Assert.assertTrue(cal.getPrintData().equals("stack: 1 2 3 20"));
        cal.readInput("clear 3 4 -");
        Assert.assertTrue(cal.getPrintData().equals("stack: - 1"));

    }


    @Test
    public void example7(){
        Cal cal = new Cal();
        cal.readInput("1 2 3 4 5");
        Assert.assertTrue(cal.getPrintData().equals("stack: 1 2 3 4 5"));
        cal.readInput("* * * *");
        Assert.assertTrue(cal.getPrintData().equals("stack: 120"));

    }


    @Test
    public void example8(){
        Cal cal = new Cal();
        String out =cal.readInput("1 2 3 * 5 + * * 6 5");
        Assert.assertTrue(out.equals("operator * (position: 15): insucient parameters"));
        Assert.assertTrue(cal.getPrintData().equals("stack: 11"));



    }

}
