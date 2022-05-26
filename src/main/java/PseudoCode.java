package main.java;

public class PseudoCode {
    /*
    PSEUDOCODE:
    - Loop thought Array of terrain heights
        (Analise behaviour: goes down, same or up)
         If terrain stays the same:
            If topOfWaterHeight not assigned ((does nothing))
            If topOfWaterHeight assigned (pool continues)
                Adds to potentialWater the topOfWaterHeight-currentHeight (column of water from the top)
        If terrain goes up:
            If topOfWaterHeight not assigned: ((does nothing))
            If topOfWaterHeight assigned: (potential end of pool)
                If (currentHeight<topOfWaterHeight) - ((pool continues)):
                        Adds to potentialWater the topOfWaterHeight-currentHeight (column of water from the top)
                If (currentHeight=topOfWaterHeight) - ((end of pool)):

                    Adds to totalWater the potentialWater
                    potentialWater = 0 (passing value to the next iteration)
                    topOfWaterHeight = null (passing value to the next iteration)
                If (currentHeight>topOfWaterHeight) - ((correction of potential water)) and ((end of pool)):
                    Removes from potentialWater the 'column of water not supported':
                        potentialWater-=(currentHeight-topOfWaterHeight)* (i-waterTerrainStart)

                    Adds to totalWater the potentialWater
                    potentialWater = 0 (passing value to the next iteration)
                    topOfWaterHeight = null (passing value to the next iteration)
         If terrain goes down:
            If topOfWaterHeight assigned ((pool continues)):
                Adds to potentialWater the topOfWaterHeight-currentHeight (column of water from the top)
            If topOfWaterHeight not assigned, ((pool starts)) and ((pool continues)):
                topOfWaterHeight = lastHeight;
                waterTerrainStart = (last terrain x axis);
                Adds to potentialWater the topOfWaterHeight-currentHeight (new column of water)

        lastHeight = currentHeight (passing value to the next iteration)

        If terrain ends: (end of loop) todo
            If topOfWaterHeight not assigned ((does nothing))
            If topOfWaterHeight assigned ((potential last end of pool)):
                If (currentHeight=topOfWaterHeight) - ((end of pool))
                If (currentHeight<topOfWaterHeight) - ((correction of potential water)) and ((end of pool))
                If (currentHeight>topOfWaterHeight) - ((correction of potential water)) and ((end of pool))
     */
}
