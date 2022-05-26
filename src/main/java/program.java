package main.java;

import java.util.List;

public class program {

    public static void main(String[] args) {

        int[] example1 = {3,2,1,3,1,1,2,1,2};
        int[] example2 = {3,3,1,2,4,1,2};
        int[] example3 = {4,4,1,2,1,3,3};
        int[] example4 = {3,2,1,3,1,1,2,1,2};
        int count = 1;

        List<int[]> listOfTests = List.of(example1,example2,example3,example4);
        listOfTests.forEach(example -> System.out.println("Total volume calculated is: "+calculateWater(example)));
//        System.out.println("Total volume calculated is: "+calculateWater(example2));
    }

    public static int calculateWater(int[] terrain) {
        WaterData waterData = new WaterData();
        int lastHeight;
        int currentHeight=0;
        for (int i=1;i<terrain.length;i++) {
            //Heights extraction:
            lastHeight=terrain[i-1];
            currentHeight=terrain[i];

            if (currentHeight==lastHeight) { //If terrain stays the same:
                if (waterData.getTopOfWaterHeight()!=0) { //If topOfWaterHeight assigned (pool continues)
                    waterData.poolContinues(lastHeight);
                }
                //If topOfWaterHeight not assigned ((does nothing))
            } else if (currentHeight>lastHeight) { //If terrain goes up:
                if (waterData.getTopOfWaterHeight()!=0) { //If topOfWaterHeight assigned: (potential end of pool)
                    if (currentHeight<waterData.getTopOfWaterHeight()) { //If (currentHeight<topOfWaterHeight) - ((pool continues)):
                        waterData.poolContinues(lastHeight);
                    } else if (currentHeight==waterData.getTopOfWaterHeight()) { //If (currentHeight=topOfWaterHeight) - ((end of pool)):
                        waterData.endOfPool();
                    } else if (currentHeight>waterData.getTopOfWaterHeight()) { //If (currentHeight>topOfWaterHeight) - ((end of pool with correction)):
                        waterData.poolContinues(lastHeight);
                        waterData.endOfPoolWithCorrection(currentHeight);
                    }
                }
                //If topOfWaterHeight not assigned ((does nothing))
            } else { //last possible choice - If terrain goes down:
                if (waterData.getTopOfWaterHeight()!=0) { //If topOfWaterHeight assigned ((pool continues)):
                    waterData.poolContinues(lastHeight);
                } else { //If topOfWaterHeight not assigned, ((pool starts)):
                    waterData.poolStarts(currentHeight,lastHeight,i-1);
                }
            }
        }
        //terrain ends: (end of loop)
        if (waterData.getTopOfWaterHeight()!=0) { //If topOfWaterHeight assigned ((potential last end of pool)) removing ((pool continues))
            if(currentHeight==waterData.getTopOfWaterHeight()) { //If (currentHeight=topOfWaterHeight) - ((end of pool))
                waterData.endOfPool();
            } else { //If (currentHeight < OR > topOfWaterHeight) - ((last correction of potential water)) and ((end of pool))
                waterData.endOfPoolWithLastCorrection(currentHeight,terrain.length-1);
            }
        } // If topOfWaterHeight not assigned ((does nothing))
        return waterData.getTotalWater();
    }
}
