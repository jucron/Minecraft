package main.java;

public class WaterData {
    private int fillingWater;
    private int totalWater;
    private int topOfWaterHeight;

    public WaterData() {
        this.fillingWater = 0;
        this.totalWater = 0;
        this.topOfWaterHeight = 0;
    }



    public int getFillingWater() {
        return fillingWater;
    }

    public void setFillingWater(int fillingWater) {
        this.fillingWater = fillingWater;
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

    public void poolFills(int heightToConsider) {
        fillingWater +=(topOfWaterHeight-heightToConsider);
    }
    public void poolStarts(int lastHeight) {
        topOfWaterHeight=lastHeight;
    }

    public void endOfPool() {
        totalWater+= fillingWater; //adds the filling to the Total water
        fillingWater =0; //ends filling
        topOfWaterHeight=0; //ends filling
    }
}
