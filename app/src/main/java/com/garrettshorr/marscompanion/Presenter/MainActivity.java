package com.garrettshorr.marscompanion.Presenter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.garrettshorr.marscompanion.Model.MarsGame;
import com.garrettshorr.marscompanion.Model.MarsResource;
import com.garrettshorr.marscompanion.R;
import com.garrettshorr.marscompanion.Widgets.HorizontalNumberPicker;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    private HorizontalNumberPicker megaCreditsV, steelV, titaniumV, plantsV, energyV,
            heatV, megaCreditsP, steelP, titaniumP, plantsP, energyP,
            heatP, rating, globalTemp, globalOceans, globalOxygen;
    private MarsGame game;
    private Button plantConversion, heatConversion;
    private HorizontalNumberPicker[] mainResourceV, mainResourceP;
    public static final int MEGA_CREDITS = 0;
    public static final int PLANTS = 3;
    public static final int ENERGY = 4;
    public static final int HEAT = 5;
    private String fileName = "savedMarsBoard";
    public static final String[] RESOURCE_NAMES
            = {"Mega Credits", "Steel", "Titanium", "Plants", "Energy", "Heat"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
        game = new MarsGame();

        //TODO: Get the attributes for the MarsGame from the Intent

        //TODO: Set the values of all the horizontal number pickers based on
    }

    private void setListeners() {
        plantConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainResourceV[PLANTS].getCurrentValue() >= game.getPlantConversion()) {
                    buildGreeneryDialog();
                } else {
                    Toast.makeText(MainActivity.this, "Not enough resources!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        heatConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mainResourceV[HEAT].getCurrentValue() >= game.getHeatConversion()) {
                    buildHeatDialog();
                } else {
                    Toast.makeText(MainActivity.this, "Not enough resources!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void wireWidgets() {
        megaCreditsV = (HorizontalNumberPicker) findViewById(R.id.hpicker_megacredit_values);
        steelV = (HorizontalNumberPicker) findViewById(R.id.hpicker_steel_values);
        titaniumV = (HorizontalNumberPicker) findViewById(R.id.hpicker_titanium_values);
        plantsV = (HorizontalNumberPicker) findViewById(R.id.hpicker_plants_values);
        energyV = (HorizontalNumberPicker) findViewById(R.id.hpicker_energy_values);
        heatV = (HorizontalNumberPicker) findViewById(R.id.hpicker_heat_values);
        megaCreditsP = (HorizontalNumberPicker) findViewById(R.id.hpicker_megacredit_production);
        steelP = (HorizontalNumberPicker) findViewById(R.id.hpicker_steel_production);
        titaniumP = (HorizontalNumberPicker) findViewById(R.id.hpicker_titanium_production);
        plantsP = (HorizontalNumberPicker) findViewById(R.id.hpicker_plants_production);
        energyP = (HorizontalNumberPicker) findViewById(R.id.hpicker_energy_production);
        heatP = (HorizontalNumberPicker) findViewById(R.id.hpicker_heat_production);
        rating = (HorizontalNumberPicker) findViewById(R.id.hpicker_rating);
        globalTemp = (HorizontalNumberPicker) findViewById(R.id.hpicker_temperature);
        globalOceans = (HorizontalNumberPicker) findViewById(R.id.hpicker_oceans);
        globalOxygen = (HorizontalNumberPicker) findViewById(R.id.hpicker_oxygen);
        plantConversion = (Button) findViewById(R.id.button_plant_conversion);
        heatConversion = (Button) findViewById(R.id.button_heat_conversion);
        mainResourceV =
                new HorizontalNumberPicker[] {megaCreditsV, steelV, titaniumV, plantsV, energyV, heatV};
        mainResourceP =
                new HorizontalNumberPicker[] {megaCreditsP, steelP, titaniumP, plantsP, energyP, heatP};

    }

    private void buildHeatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                buildHeat();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage(R.string.dialog_heat_message)
                .setTitle(R.string.dialog_heat_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void buildOceanDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.special_project, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                buildOceanAdjacencyDialog(0);
                dialog.dismiss();
            }
        });
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }});
        builder.setNegativeButton(R.string.standard_project, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                buildOceanAdjacencyDialog(18);
                dialog.dismiss();
            }
        });
        builder.setMessage(R.string.dialog_ocean_message)
                .setTitle(R.string.dialog_ocean_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void buildGreeneryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                buildGreenery();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage(R.string.dialog_greenery_message)
                .setTitle(R.string.dialog_greenery_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void buildOceanAdjacencyDialog(final int cost) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Number of Adjacent Aquifers");
        builder.setSingleChoiceItems(new String[] {"0","1","2","3"}, 0, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                buildOcean(cost, item);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void buildOcean(int cost, int numAdj) {
        if(mainResourceV[MEGA_CREDITS].getCurrentValue() >= cost) {
            globalOceans.add(1);
            globalOceans.updateText();
            rating.add(1);
            rating.updateText();
            mainResourceV[MEGA_CREDITS].add(-cost);
            mainResourceV[MEGA_CREDITS].add(2*numAdj);
            mainResourceV[MEGA_CREDITS].updateText();
        } else {
            Toast.makeText(MainActivity.this, "Insufficient Funds!", Toast.LENGTH_SHORT).show();
        }

    }

    private void buildHeat() {
        mainResourceV[HEAT].add(-game.getHeatConversion());
        mainResourceV[HEAT].updateText();
        if(globalTemp.getCurrentValue() < globalTemp.getMaxValue()) {
            globalTemp.add(2);
            rating.add(1);
            globalTemp.updateText();
            rating.updateText();
        }
        Toast.makeText(MainActivity.this, "Mars Warmed!", Toast.LENGTH_SHORT).show();
    }

    private void buildGreenery() {
        mainResourceV[PLANTS].add(-game.getPlantConversion());
        mainResourceV[PLANTS].updateText();
        if(globalOxygen.getCurrentValue() < globalOxygen.getMaxValue()) {
            globalOxygen.add(1);
            rating.add(1);
            globalOxygen.updateText();
            rating.updateText();
        }
        Toast.makeText(MainActivity.this, "Greenery Added!", Toast.LENGTH_SHORT).show();
    }

    private void advanceGenerationAreYouSure() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                advanceGeneration();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage(R.string.dialog_generation_message)
                .setTitle(R.string.dialog_generation_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void advanceGeneration() {
        MarsResource[] resources = game.getResources();

        //transfer energy to heat
        mainResourceV[HEAT].setCurrentValue(mainResourceV[ENERGY].getCurrentValue()+
                mainResourceV[HEAT].getCurrentValue());
        mainResourceV[ENERGY].setCurrentValue(0);

        //add Terraforming Rating to Mega Credit value
        mainResourceV[MEGA_CREDITS].add(rating.getCurrentValue());


        //produce
        for(int i = 0; i < resources.length; i++) {
            resources[i].setCurrentValue(mainResourceV[i].getCurrentValue());
            resources[i].setCurrentProduction(mainResourceP[i].getCurrentValue());
            resources[i].produce();
            mainResourceP[i].setCurrentValue(resources[i].getCurrentProduction());
            mainResourceV[i].setCurrentValue(resources[i].getCurrentValue());
            mainResourceP[i].updateText();
            mainResourceV[i].updateText();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_next_generation:
                advanceGenerationAreYouSure();
                return true;
            case R.id.menu_ocean:
                if (globalOceans.getCurrentValue() < globalOceans.getMaxValue()) {
                    buildOceanDialog();
                } else {
                    Toast.makeText(MainActivity.this, "Max Oceans Already Achieved!",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FileInputStream fis = null;
        try {
            fis = openFileInput(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectInputStream is = null;
        if(fis != null) {
            try {
                is = new ObjectInputStream(fis);
                game = (MarsGame) is.readObject();
                is.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        updateGameFromFile();
        Log.d("LOOK AT ME", "onResume: UPDATED?");
    }

    private void updateGameFromFile() {
        for(int i = 0; i < mainResourceV.length; i++) {
            MarsResource[] resources = game.getResources();
            mainResourceP[i].setMinValue(resources[i].getMinProduction());
            mainResourceP[i].setCurrentValue(resources[i].getCurrentProduction());
            mainResourceV[i].setCurrentValue(resources[i].getCurrentValue());
            mainResourceV[i].updateText();
            mainResourceP[i].updateText();
        }
        globalOceans.setCurrentValue(game.getOceans());
        globalOceans.updateText();
        globalTemp.setCurrentValue(game.getTemperature());
        globalTemp.updateText();
        globalOxygen.setCurrentValue(game.getOxygen());
        globalOxygen.updateText();
        rating.setCurrentValue(game.getTerraformingRating());
        rating.updateText();
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateGameFromMemory();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(fos);
            os.writeObject(game);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateGameFromMemory() {
        MarsResource[] resources = new MarsResource[mainResourceV.length];
        for(int i = 0; i < mainResourceV.length; i++) {
            int minProd = mainResourceP[i].getMinValue();
            int currentProd = mainResourceP[i].getCurrentValue();
            int currentVal = mainResourceV[i].getCurrentValue();
            MarsResource mc = new MarsResource(minProd, RESOURCE_NAMES[i], currentVal, currentProd);
            resources[i] = mc;
        }
        game.setResources(resources);
        game.setOceans(globalOceans.getCurrentValue());
        game.setTemperature(globalTemp.getCurrentValue());
        game.setOxygen(globalOxygen.getCurrentValue());
        game.setTerraformingRating(rating.getCurrentValue());
    }


}
