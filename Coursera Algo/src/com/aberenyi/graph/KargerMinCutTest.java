package com.aberenyi.graph;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.bioseek.StopWatch;

public class KargerMinCutTest {
    
    
    @Test
    public void testTimeOneCut() {
        Graph g = new Graph();

        StopWatch st = new StopWatch();
        for (int i = 0; i < 1000; i++) {
            g.reset();
            GraphManager
                    .loadSimpleGraphFromAdjacencyList(
                            "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2.txt",
                            g);
            st.start();
            KargerMinCut.oneCut(g);
            st.stop();
        }
        System.out.println(" average Karger time millis: " + st.getTime() / 1000);

    }

    @Test
    public void testAdjacencyList() throws GraphException {
        final String fileName = "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/testAdjacencyList.txt";
        int mc = KargerMinCut.calculate(fileName);
        System.out.println("Karger test = " + mc);
        Assert.assertEquals(2, mc);
    }

    @Test
    public void testMinCut() throws GraphException {
        final String fileName = "/Users/aberenyi/git/algo/Coursera Algo/input/algo1/assign3/kargerMinCut2.txt";
        int mc = KargerMinCut.calculate(fileName);
        System.out.println("Karger = " + mc);
        Assert.assertEquals(17, mc);
    }

    @Test
    public void testRandom() {
        Random RANDOM = new Random();
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;

        final int ITERATIONS = 1000000;
        int it = ITERATIONS;
        while (it-- > 0) {
            int r = RANDOM.nextInt(5);
            switch (r) {
            case 0:
                r0++;
                break;
            case 1:
                r1++;
                break;
            case 2:
                r2++;
                break;
            case 3:
                r3++;
                break;
            case 4:
                r4++;
                break;

            default:
                break;
            }
        }
        System.out.println("0: " + ((double) r0)/ITERATIONS*100);
        System.out.println("1: " + ((double) r1)/ITERATIONS*100);
        System.out.println("2: " + ((double) r2)/ITERATIONS*100);
        System.out.println("3: " + ((double) r3)/ITERATIONS*100);
        System.out.println("4: " + ((double) r4)/ITERATIONS*100);

    }
}
