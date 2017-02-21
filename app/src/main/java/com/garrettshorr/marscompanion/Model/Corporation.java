package com.garrettshorr.marscompanion.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by g on 2/19/2017.
 */
public class Corporation implements Parcelable{
    private String name;
    private int heatConversion;
    private int plantConversion;
    private int steelConversion;
    private int titaniumConversion;
    private int[] initialValues;
    private int[] initialProduction;

    public Corporation(String name, int heatConversion, int plantConversion, int steelConversion,
                       int titaniumConversion, int[] initialValues, int[] initialProduction) {
        this.name = name;
        this.heatConversion = heatConversion;
        this.plantConversion = plantConversion;
        this.steelConversion = steelConversion;
        this.titaniumConversion = titaniumConversion;
        this.initialValues = initialValues;
        this.initialProduction = initialProduction;
    }

    public int getHeatConversion() {
        return heatConversion;
    }

    public void setHeatConversion(int heatConversion) {
        this.heatConversion = heatConversion;
    }

    public int getPlantConversion() {
        return plantConversion;
    }

    public void setPlantConversion(int plantConversion) {
        this.plantConversion = plantConversion;
    }

    public int getSteelConversion() {
        return steelConversion;
    }

    public void setSteelConversion(int steelConversion) {
        this.steelConversion = steelConversion;
    }

    public int getTitaniumConversion() {
        return titaniumConversion;
    }

    public void setTitaniumConversion(int titaniumConversion) {
        this.titaniumConversion = titaniumConversion;
    }

    public int[] getInitialValues() {
        return initialValues;
    }

    public void setInitialValues(int[] initialValues) {
        this.initialValues = initialValues;
    }

    public int[] getInitialProduction() {
        return initialProduction;
    }

    public void setInitialProduction(int[] initialProduction) {
        this.initialProduction = initialProduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Corporation(Parcel in) {
        name = in.readString();
        heatConversion = in.readInt();
        plantConversion = in.readInt();
        steelConversion = in.readInt();
        titaniumConversion = in.readInt();
        initialValues = new int[in.readInt()];
        in.readIntArray(initialValues);
        initialProduction = new int[in.readInt()];
        in.readIntArray(initialProduction);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(heatConversion);
        dest.writeInt(plantConversion);
        dest.writeInt(steelConversion);
        dest.writeInt(titaniumConversion);
        dest.writeInt(initialValues.length);
        dest.writeIntArray(initialValues);
        dest.writeInt(initialProduction.length);
        dest.writeIntArray(initialProduction);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Corporation> CREATOR = new Parcelable.Creator<Corporation>() {
        @Override
        public Corporation createFromParcel(Parcel in) {
            return new Corporation(in);
        }

        @Override
        public Corporation[] newArray(int size) {
            return new Corporation[size];
        }
    };
}
