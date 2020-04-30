public class Plant {
    int energy; //energy plant will provide after being eaten
    int growth; //growth rate of plant
    String name; // name of palnt
    boolean harvestable; // boolean to test if the plant will grow back and be harvestable again
    boolean ripe; //if the plant is ready to be harvested
    int plantCount;

    public Plant(int energy, int  growth, String name, boolean harvestable, boolean ripe){
        this.energy = energy;
        this.growth = growth;
        this.name = name;
        this.harvestable = harvestable;
        this.ripe = ripe;

        plantCount = growth;
    }

    public int getGrowth() {
        return growth;
    }
    public int getEnergy(){
        return energy;
    }
    public int getPlantCount(){
        return plantCount;
    }
}
