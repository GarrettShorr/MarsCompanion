package com.garrettshorr.marscompanion.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.garrettshorr.marscompanion.Model.Corporation;
import com.garrettshorr.marscompanion.R;

public class StartActivity extends AppCompatActivity {

    private Corporation[] corporations;
    public static final String EXTRA_HEAT_CONV = "HEAT CONV";
    public static final String EXTRA_PLANT_CONV = "PLANT CONV";
    public static final String EXTRA_STEEL_CONV = "STEEL CONV";
    public static final String EXTRA_TITANIUM_CONV = "TITANIUM CONV";
    public static final String EXTRA_INITIAL_VALS = "INITIAL VALS";
    public static final String EXTRA_INITIAL_PROD = "INITIAL PROD";
    public static final String EXTRA_CORPORATION = "CORPORATION";

    public static final String EXTRA_RESUME = "RESUME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        corporations = new Corporation[12];
        corporations[0] = new Corporation("Credicor",8,8,2,3,new int[] {57, 0, 0, 0, 0, 0}, new int[] {0,0,0,0,0,0});
        corporations[1] = new Corporation("Ecoline",8,7,2,3,new int[] {36,0,0,3,0,0}, new int[] {0,0,0,2,0,0});
        corporations[2] = new Corporation("Helion",8,8,2,3,new int[] {42,0,0,0,0,0}, new int[] {0,0,0,0,0,3});
        corporations[3] = new Corporation("Interplanetary Cinematics",8,8,2,3, new int[] {30,20,0,0,0,0}, new int[] {0,0,0,0,0,0});
        corporations[4] = new Corporation("Inventrix",8,8,2,3,new int[] {45,0,0,0,0,0}, new int[] {0,0,0,0,0,0});
        corporations[5] = new Corporation("Mining Guild",8,8,2,3,new int[] {30,5,0,0,0,0}, new int[] {0,1,0,0,0,0});
        corporations[6] = new Corporation("Phobolog", 8,8,2,4, new int[] {23, 0, 10, 0,0 ,0}, new int[] {0,0,0,0,0,0});
        corporations[7] = new Corporation("Saturn Systems", 8,8,2,3,new int[] {42,0,0,0,0,0}, new int[] {0,0,1,0,0,0});
        corporations[8] = new Corporation("Teractor", 8,8,2,3, new int[] {60,0,0,0,0,0}, new int[] {0,0,0,0,0,0});
        corporations[9] = new Corporation("Tharsis Republic", 8,8,2,3, new int[] {40,0,0,0,0,0}, new int[] {0,0,0,0,0,0});
        corporations[10] = new Corporation("Thorgate",8,8,2,3, new int[] {48,0,0,0,0,0}, new int[] {0,0,0,0,1,0});
        corporations[11] = new Corporation("United Nations Mars Initiative", 8,8,2,3, new int[] {40,0,0,0,0,0}, new int[] {0,0,0,0,0,0});

        String[] corpNames = new String[12];
        for(int i = 0; i < corporations.length; i++) {
            corpNames[i] = corporations[i].getName();
        }

        final Spinner spinner = (Spinner) findViewById(R.id.spinner_corps);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, corpNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button startTerraforming = (Button) findViewById(R.id.button_start);
        startTerraforming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = spinner.getSelectedItemPosition();
                Corporation corp = corporations[pos];
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                //intent.putExtra(EXTRA_CORPORATION, corporations[spinner.getSelectedItemPosition()]);
                intent.putExtra(EXTRA_HEAT_CONV, corp.getHeatConversion());
                intent.putExtra(EXTRA_PLANT_CONV, corp.getPlantConversion());
                intent.putExtra(EXTRA_STEEL_CONV, corp.getSteelConversion());
                intent.putExtra(EXTRA_TITANIUM_CONV, corp.getTitaniumConversion());
                intent.putExtra(EXTRA_INITIAL_VALS, corp.getInitialValues());
                intent.putExtra(EXTRA_INITIAL_PROD, corp.getInitialProduction());
                intent.putExtra(EXTRA_RESUME, false);
                startActivity(intent);
            }
        });

        Button resumeGame = (Button) findViewById(R.id.button_resume);
        resumeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                intent.putExtra(EXTRA_RESUME, true);
                startActivity(intent);
            }
        });


    }
}
