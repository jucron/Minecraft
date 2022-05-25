package main.java;

public class program {
    public static void main(String[] args) {
        int[] example = {3,2,1,3,1,1,2,1,2};

        System.out.println(calculateWater(example));
    }



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
