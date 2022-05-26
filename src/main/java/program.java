package main.java;

public class program {
    public static void main(String[] args) {
        int[] example = {3,2,1,3,1,1,2,1,2};

        System.out.println(calculateWater(example));
    }

    /*
    PSEUDOCODE:
    - Loop thought Array of terrain heights
        Analise behaviour: goes down, same or up
        If terrain goes down:
            If 'topOfWater' not assigned, (pool starts):
                topOfWaterHeight = lastHeight;
                topOfWaterTerrainStart = i;
                Adds to PotentialWater lastHeight-currentHeight (new column of water)
                lastHeight = currentHeight (passing value to the next iteration)
            If 'TopOfWater' assigned (pool continues to deepen):
                Adds to PotentialWater lastHeight-currentHeight (new column of water)
                Adds to PotentialWater topOfWaterHeight-lastHeight (column of water above)
                lastHeight = currentHeight (passing value to the next iteration)
        If terrain goes up:
            If 'topOfWater' not assigned: does nothing
            If 'TopOfWater' assigned: (potential end of pool) todo
                If (currentHeight=topOfWaterHeight) - end of pool:
                If (currentHeight>topOfWaterHeight) - end of pool and correction:
                If (currentHeight<topOfWaterHeight) - pool continues:


        If terrain stays the same:
            If 'topOfWater' not assigned does nothing
            If 'topOfWater' assigned (pool continues)
                Adds to PotentialWater topOfWaterHeight-currentHeight (column of water from the top)
                lastHeight = currentHeight (passing value to the next iteration)

     */


    public static int calculateWater(int[] terrain) {

        int totalWaterColumn = 0;
        int lastWaterColumn = 0;
        int topOfWater = 0;
        for (int i=1;i<terrain.length;i++) {
            if (terrain[i]<terrain[i-1]) { //if terrain goes down: check if topOfWater, add currentColumn+lastColumn
                int currentWaterColumn = (terrain[i-1]-terrain[i])+lastWaterColumn;
                totalWaterColumn+=currentWaterColumn; //updating total columns
                lastWaterColumn=currentWaterColumn; //updating lastColumn
                topOfWater = Math.max(topOfWater,terrain[i-1]); //updating topOfWater
            } else if (terrain[i]==terrain[i-1]){ //if terrain stays the same: add lastColumn
                totalWaterColumn+= lastWaterColumn; //updating total columns
            } else if (terrain[i]>terrain[i-1]){ //if terrain goes up:

            }
        }
        return totalWaterColumn;
    }
}
