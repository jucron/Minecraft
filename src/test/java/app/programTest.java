package app;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class programTest {

    @Test
    void calculateWater() {
        //given
        int[] example1 = {3,2,1,3,1,1,2,1,2};
        int[] example2 = {3,3,1,2,4,1,2};
        int[] example3 = {4,4,1,2,1,3,3};
        int[] example4 = {5,1,6,9,8,1};
        int[] example5 = {1,5,3,2,1,1};
        //when
        //then
        assertEquals(6, Program.calculateWater(example1,0));
        assertEquals(4, Program.calculateWater(example2,0));
        assertEquals(5, Program.calculateWater(example3,0));
        assertEquals(4, Program.calculateWater(example4,0));
        assertEquals(0, Program.calculateWater(example5,0));
    }

    @Test
    void countWaterBlocks() {
        //when
        int waterCount1 = Program.includeWaterBlocks(new int[]{3,2,3},new Edge(0,3),new Edge(2,3));
        int waterCount2 = Program.includeWaterBlocks(new int[]{3,1,1,3},new Edge(0,3),new Edge(3,3));
        int waterCount3 = Program.includeWaterBlocks(new int[]{1,3,2,2,3,1,5},new Edge(1,3),new Edge(4,3));
        int waterCount4 = Program.includeWaterBlocks(new int[]{1,3,2,5,3,1,5},new Edge(1,3),new Edge(3,5));
        int waterCount5 = Program.includeWaterBlocks(new int[]{1,3,2,5,3,1,5},new Edge(3,5),new Edge(6,5));
        int waterCount6 = Program.includeWaterBlocks(new int[]{3,3,5},new Edge(0,3),new Edge(2,3));
        int waterCount7 = Program.includeWaterBlocks(new int[]{2,1,1,3},new Edge(0,2),new Edge(3,3));
        int waterCount8 = Program.includeWaterBlocks(new int[]{2,1,4,2,1},new Edge(0,2),new Edge(2,4));

        //then
        assertEquals(1,waterCount1);
        assertEquals(4,waterCount2);
        assertEquals(2,waterCount3);
        assertEquals(1,waterCount4);
        assertEquals(6,waterCount5);
        assertEquals(0,waterCount6);
        assertEquals(2,waterCount7);
        assertEquals(1,waterCount8);
        Program.clearWaterBlocks();
    }

    @Test
    void findLastEdge() {
        //when
        Edge edge1 = Program.findLastEdge(new int[]{3,2,3},new Edge(0,3));
        Edge edge2 = Program.findLastEdge(new int[]{1,2,3,2,2,1,3},new Edge(2,3));
        Edge edge3 = Program.findLastEdge(new int[]{3,2,3,5,2,1,5},new Edge(3,5));
        Edge edge4 = Program.findLastEdge(new int[]{1,4,3,4,9,1,5,9,3},new Edge(4,9));
        Edge edge5 = Program.findLastEdge(new int[]{1,4,3,4,9,1,5,2,3},new Edge(4,9));
        Edge edge6 = Program.findLastEdge(new int[]{3,3,2},new Edge(0,3));
        Edge edge7 = Program.findLastEdge(new int[]{3,2,5},new Edge(0,3));


        //then
        assertNotNull(edge1); assertTrue(this.edgeObjectComparison(edge1,new Edge(2,3)));
        assertNotNull(edge2); assertTrue(this.edgeObjectComparison(edge2,new Edge(6,3)));
        assertNotNull(edge3); assertTrue(this.edgeObjectComparison(edge3,new Edge(6,5)));
        assertNotNull(edge4); assertTrue(this.edgeObjectComparison(edge4,new Edge(7,9)));
        assertNull(edge5);
        assertNull(edge6);
        assertNotNull(edge7); assertTrue(this.edgeObjectComparison(edge7,new Edge(2,5)));

    }

    @org.junit.jupiter.api.Test
    void findFirstEdge() {
        //when
        Edge edge1 = Program.findFirstEdge(new int[]{3,2,3},0);
        Edge edge2 = Program.findFirstEdge(new int[]{1,2,3,2,2,1,3},0);
        Edge edge3 = Program.findFirstEdge(new int[]{3,2,3,5,2,1,5},2);
        Edge edge4 = Program.findFirstEdge(new int[]{1,4,3,4,9,1,5,9,3},4);
        Edge edge5 = Program.findFirstEdge(new int[]{1,4,3,4,9,1,5,2,3},5);
        Edge edge6 = Program.findFirstEdge(new int[]{1,4,3,4,9,5,5,5,6},5);
        //then
        assertNotNull(edge1); assertTrue(this.edgeObjectComparison(edge1,new Edge(0,3)));
        assertNotNull(edge2); assertTrue(this.edgeObjectComparison(edge2,new Edge(2,3)));
        assertNotNull(edge3); assertTrue(this.edgeObjectComparison(edge3,new Edge(3,5)));
        assertNotNull(edge4); assertTrue(this.edgeObjectComparison(edge4,new Edge(4,9)));
        assertNotNull(edge5); assertTrue(this.edgeObjectComparison(edge5,new Edge(6,5)));
        assertNull(edge6);

    }
    @Test
    void reverseArray() {
        int[] array1 = Program.reverseArray(new int[]{3,2,1,0}); int[] expected1 = new int[]{0,1,2,3};
        int[] array2 = Program.reverseArray(new int[]{5,8,1,0}); int[] expected2 = new int[]{0,1,8,5};
        int[] array3 = Program.reverseArray(new int[]{1,4,3,4,9,1,5,9,3}); int[] expected3 = new int[]{3,9,5,1,9,4,3,4,1};
        int[] array4 = Program.reverseArray(new int[]{0,0,0,1}); int[] expected4 = new int[]{1,0,0,0};
        assertArrayEquals(expected1,array1);
        assertArrayEquals(expected2,array2);
        assertArrayEquals(expected3,array3);
        assertArrayEquals(expected4,array4);
    }
    @Test
    void finalWaterCount() {
        Map<Integer, Integer> waterBlocks1 = new HashMap<>(); waterBlocks1.put(1,2); waterBlocks1.put(2,2);
        int test1 = Program.finalWaterCountTesting(waterBlocks1);
        Map<Integer, Integer> waterBlocks2 = new HashMap<>(); waterBlocks2.put(3,1); waterBlocks2.put(5,2);
        int test2 = Program.finalWaterCountTesting(waterBlocks2);
        Map<Integer, Integer> waterBlocks3 = new HashMap<>(); waterBlocks3.put(2,2); waterBlocks3.put(3,2); waterBlocks3.put(4,8);
        int test3 = Program.finalWaterCountTesting(waterBlocks3);

        assertEquals(4,test1);
        assertEquals(3,test2);
        assertEquals(12,test3);
    }
    private boolean edgeObjectComparison(Edge edge1, Edge edge2) {
        return (edge1.index==edge2.index) && (edge1.terrainLevel==edge2.terrainLevel);
    }
}