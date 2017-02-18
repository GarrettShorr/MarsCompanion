package com.garrettshorr.marscompanion.Model;

/**
 * Created by g on 2/18/2017.
 */
public class MarsResource {
    private int minProduction;
    private String name;
    private int currentValue;
    private int currentProduction;

    //region Constructors
    public MarsResource() {
        this.minProduction = 0;
        this.name = "Generic Resource";
        this.currentValue = 0;
        this.currentProduction = 0;
    }

    public MarsResource(int minProduction, String name, int currentValue, int currentProduction) {
        this.minProduction = minProduction;
        this.name = name;
        this.currentValue = currentValue;
        this.currentProduction = currentProduction;
    }
    //endregion

    public void produce() {
        currentValue += currentProduction;
        if(currentValue < 0) //can't go below 0 resources
            currentValue = 0;
    }

    public boolean canDecrement() {
        return currentValue > minProduction;
    }

    public void increment() {
        currentValue++;
    }

    /**
     * Decrements the resource if possible
     * @return true if able to decrement, false if not
     */
    public boolean decrement() {
        if(!canDecrement())
            return false;
        currentValue--;
        return true;
    }

    //region getters & setters
    public int getMinProduction() {
        return minProduction;
    }

    public void setMinProduction(int minProduction) {
        this.minProduction = minProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getCurrentProduction() {
        return currentProduction;
    }

    public void setCurrentProduction(int currentProduction) {
        this.currentProduction = currentProduction;
    }
    //endregion
}
