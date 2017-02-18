package com.garrettshorr.marscompanion.Model;

/**
 * Created by g on 2/18/2017.
 */
public class MarsGame {
    private MarsResource[] resources;
    private int cities; //TODO: come up with delineation of martian cities vs all cities
    private int oceans;
    private int temperature;
    private int oxygen;
    private int terraformingRating;
    private int generation;

    public MarsGame() {
        MarsResource megaCredits = new MarsResource(-5,"MegaCredits",0,0);
        MarsResource steel = new MarsResource(0,"Steel",0,0);
        MarsResource titanium = new MarsResource(0, "Titanium", 0,0);
        MarsResource plants = new MarsResource(0, "Plants", 0, 0);
        MarsResource energy = new MarsResource(0, "Energy", 0, 0);
        MarsResource heat = new MarsResource(0, "Heat", 0, 0);
        resources = new MarsResource[] {megaCredits, steel, titanium, plants, energy, heat};
        cities = 0;
        oceans = 0;
        temperature = -30;
        oxygen = 0;
        terraformingRating = 20;
        generation = 0;
    }

    public void nextGeneration() {
        for(MarsResource resource : resources) {
            resource.produce();
        }
        generation++;
    }

    //region Getters & Setters
    public MarsResource[] getResources() {
        return resources;
    }

    public void setResources(MarsResource[] resources) {
        this.resources = resources;
    }

    public int getCities() {
        return cities;
    }

    public void setCities(int cities) {
        this.cities = cities;
    }

    public int getOceans() {
        return oceans;
    }

    public void setOceans(int oceans) {
        this.oceans = oceans;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public int getTerraformingRating() {
        return terraformingRating;
    }

    public void setTerraformingRating(int terraformingRating) {
        this.terraformingRating = terraformingRating;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    //endregion
}
