package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Program {
    private static boolean reverse = false;
    private static Map<Integer, Integer> waterBlocks = new HashMap<>(); //Index vs WaterBlocks
    //Main method:
    public static int calculateWater(int[] terrain, int startIndex) {
        //Pre-calculation: parameters validation:
        if (!areParametersValid(terrain,startIndex)) {
            return -1; //If validations don't pass, return a representation of error
        }
        //Step 1: Find a height variation going DOWN (firstEdge), from this startIndex forward
        Edge firstEdge = findFirstEdge(terrain, startIndex); //returns null if NOT found
        //Step 2: Check if there is no firstEdge OR startIndex is at the end, finish calculation or reverse
        if ((firstEdge==null) || (startIndex>terrain.length-2)) {
            //If first round, start the second round: calculation in reverse
            if (!reverse) {
                reverse=true; //start reverse calculation
                return calculateWater(reverseTerrain(terrain),0);
            } else {
                //If second round, return final count
                reverse=false;
                return finalWaterCount();
            }
        }
        //Step 3:If firstEdge is found, find a height variation going UP (lastEdge)
        Edge lastEdge = findLastEdge(terrain,firstEdge); //returns null if NOT found
        //Step 4:If there is no lastEdge for this firstEdge, continue calculation for next index in this list
        if (lastEdge==null) {
            return calculateWater(terrain, startIndex+1);
        }
        //Step 5:If firstEdge and lastEdge are found, include waterBlocks and continue calculation from the lastEdge forward
        includeWaterBlocks(terrain,firstEdge,lastEdge);
        return calculateWater(terrain, lastEdge.index);
    }
    //Secondary methods:
    private static boolean areParametersValid(int[] terrain, int startIndex) {
        if (terrain==null || terrain.length==0 || startIndex<0 || startIndex>(terrain.length-1)) {
            return false;
        }
        return true;
    }
    public static Edge findFirstEdge(int[] terrain, int startIndex) {
        //Validation to end round: (when the index is close to the end)
        //If two indexes forward is surpass the last array's index, return null
        if ((startIndex+2)>(terrain.length-1)) {
            return null;
        }
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
    public static Edge findLastEdge(int[] terrain, Edge firstEdge) {
        //From 2 indexes forward, check if there are terrainLVLs equals OR highers
        for (int i = (firstEdge.index+2); i < terrain.length; i++) {
            //first-round: if terrainLVL is equal or higher than firstEdge, found lastEdge
            if (!reverse && firstEdge.terrainLevel<=terrain[i]) {
                return new Edge(i, terrain[i]);
            //second-round: if terrainLVL equals to firstEdge, break loop cause this one was already included in first-round
            } else if (firstEdge.terrainLevel==terrain[i]) {
                break;  //does not count this Edge (already included in first round)
            //second-round: if terrainLVL higher than firstEdge, found lastEdge
            //NOTE: Even so, this might already been included in first-round (submerged terrains) - will be checked in includeWaterBlocks method
            } else if (firstEdge.terrainLevel<terrain[i]) {
                return new Edge(i,terrain[i]);
            }
        }
        //If Last edge was NOT found:
        return null;
    }
    public static int includeWaterBlocks(int[] terrain, Edge firstEdge, Edge lastEdge) {
        int topOfWaterHeight = firstEdge.terrainLevel;
        int blocksOfWater =0;
        int totalBlocksCount=0;
        for (int i = (firstEdge.index+1); i < (lastEdge.index); i++) { //loop through first to last edge
            blocksOfWater=(topOfWaterHeight-terrain[i]);
            totalBlocksCount+=blocksOfWater;
            //If It's first-round, include everything in waterBlocks
            if (!reverse) {
                waterBlocks.put(i, blocksOfWater);
            }else { //If It's second-round, check if index is already included
                int notReversedIndex = terrain.length-1-i;
                Optional<Integer> waterBlocksIncluded = getWaterBlockIncludedInThisIndex(notReversedIndex);
                //If this index is not listed yet OR old value is smaller, replace with the new one
                if (waterBlocksIncluded.isEmpty() || waterBlocksIncluded.get()<blocksOfWater) {
                    waterBlocks.put(notReversedIndex,blocksOfWater);
                }
            }
        }
        return totalBlocksCount;
    }
    private static Optional<Integer> getWaterBlockIncludedInThisIndex(int notReversedIndex) {
        if (waterBlocks.get(notReversedIndex)==null) {
            return Optional.empty();
        } else {
            return Optional.of(waterBlocks.get(notReversedIndex));
        }
    }
    private static int finalWaterCount() {
        int totalWaterCount = waterBlocks.values().stream().mapToInt(Integer::intValue).sum();
        clearWaterBlocks();
        return totalWaterCount;
    }
    //Support methods:
    public static int[] reverseTerrain(int[] terrain) {
        int[] reversedTerrain = new int[terrain.length];
        for (int i=0;i<terrain.length;i++) {
            reversedTerrain[terrain.length-1-i]=terrain[i];
        }
        return reversedTerrain;
    }
    public static void clearWaterBlocks() {
        waterBlocks.clear();
    }
    //For testing:
    public static int finalWaterCountTesting(Map<Integer, Integer> waterBlocksTest) {
        waterBlocks.putAll(waterBlocksTest);
        return finalWaterCount();
    }
}
