package main.java;

public class WaterData {
    private int potentialWater;
    private int totalWater;
    private int topOfWaterHeight;
    private int waterTerrainStart;

    public WaterData() {
        this.potentialWater = 0;
        this.totalWater = 0;
        this.topOfWaterHeight = 0;
    }



    public int getPotentialWater() {
        return potentialWater;
    }

    public void setPotentialWater(int potentialWater) {
        this.potentialWater = potentialWater;
    }

    public int getTotalWater() {
        return totalWater;
    }

    public void setTotalWater(int totalWater) {
        this.totalWater = totalWater;
    }

    public int getTopOfWaterHeight() {
        return topOfWaterHeight;
    }

    public void setTopOfWaterHeight(int topOfWaterHeight) {
        this.topOfWaterHeight = topOfWaterHeight;
    }

    public int getWaterTerrainStart() {
        return waterTerrainStart;
    }

    public void setWaterTerrainStart(int waterTerrainStart) {
        this.waterTerrainStart = waterTerrainStart;
    }
    public void poolContinues(int lastHeight) {
        potentialWater+=(topOfWaterHeight-lastHeight);
    }
    public void poolStarts(int currentHeight, int lastHeight,int waterTerrainStart) {
        topOfWaterHeight=lastHeight;
        this.waterTerrainStart=waterTerrainStart;
        this.poolContinues(currentHeight);
    }

    public void endOfPool() {
//        potentialWater+=Math.abs(currentHeight-lastHeight); //adds new column of water
        totalWater+=potentialWater; //adds the pool to the Total
        potentialWater=0; //ends pool
        topOfWaterHeight=0; //ends pool
    }
    public void endOfPoolWithLastCorrection(int currentHeight, int waterTerrainEnds) {
        potentialWater-=(Math.abs(currentHeight-topOfWaterHeight))*(waterTerrainEnds-waterTerrainStart);
        this.endOfPool();
    }
    public void endOfPoolWithCorrection(int currentHeight) {
        potentialWater-=(Math.abs(currentHeight-topOfWaterHeight));
        this.endOfPool();
    }
}
