package main.java;

import java.util.Arrays;
import java.util.List;

public class program {

    public static void main(String[] args) {

        int[] example1 = {3,2,1,3,1,1,2,1,2};
        int[] example2 = {3,3,1,2,4,1,2};
        int[] example3 = {4,4,1,2,1,3,3};
        int[] example4 = {3,2,1,3,1,1,2,1,2};
        int count = 1;

//        List<int[]> listOfTests = List.of(example1,example2,example3,example4);
//        listOfTests.forEach(example -> System.out.println("Total volume calculated is: "+calculateWater(example)));
        System.out.println("Total volume calculated is: "+calculateWater(example1,0,0));
    }

    public static int calculateWater(int[] terrain, int startIndex, int startingWaterBlocks) {


        Edge firstEdge = findFirstEdge(terrain, startIndex); //returns null if NOT found
        //if there is no firstEdge forward OR startIndex is at the end, end recursive calculation
        if ((firstEdge==null) || (startIndex>terrain.length-2)) {
            return startingWaterBlocks;
        }
        Edge lastEdge = findLastEdge(terrain,firstEdge); //returns null if NOT found
        if (lastEdge==null) { //If there is no lastEdge for this firstEdge, continue calculation for next index
            return calculateWater(terrain, startIndex+1,startingWaterBlocks);
        }
        int waterBlocks = countWaterBlocks(terrain,firstEdge,lastEdge);
        return calculateWater(terrain, lastEdge.index,(waterBlocks+startingWaterBlocks));
    }

    public static int countWaterBlocks(int[] terrain, Edge firstEdge, Edge lastEdge) {
        int topOfWaterHeight = firstEdge.terrainLevel;
        int blocksOfWater =0;
        for (int i = (firstEdge.index+1); i < (lastEdge.index); i++) {
            blocksOfWater+=(topOfWaterHeight-terrain[i]);
        }
        return blocksOfWater;
    }

    public static Edge findLastEdge(int[] terrain, Edge firstEdge) {
        if (firstEdge.index+2>(terrain.length-1)){ //If is outOfBounds
            return null;
        }
        for (int i = (firstEdge.index+2); i < terrain.length; i++) {
            if (firstEdge.terrainLevel==terrain[i]) { //if last edge, for this, was found
                return new Edge(i,terrain[i]);
            }
        }
        //Last edge was NOT found:
        return null;
    }

    public static Edge findFirstEdge(int[] terrain, int startIndex) {
        for (int i = startIndex+1; i < terrain.length; i++) {
            //Data extraction:
            int lastIndex = i-1;
            int lastHeight = terrain[lastIndex];
            int currentHeight = terrain[i];
            //
            if (currentHeight < lastHeight) { //If terrain goes down:
                return new Edge(lastIndex,lastHeight);
            }
        }
        //first edge was NOT found
        return null;
    }
}
