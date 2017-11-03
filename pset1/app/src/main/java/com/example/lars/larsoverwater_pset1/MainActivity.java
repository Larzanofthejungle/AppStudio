package com.example.lars.larsoverwater_pset1;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    String[] bodyparts = {"arms", "ears", "eyes", "eyebrows", "glasses", "hat", "mouth", "mustache", "nose", "shoes"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mr. Potato Head");
        setContentView(R.layout.activity_main);
    }

    public void onCheckboxClicked(View view) {

        // sets the visibility that is needed dependent on whether the checkbox was checked or not
        int visibility = ((CheckBox) view).isChecked() ? View.VISIBLE : View.INVISIBLE;

        // gets id of the view that changes visibility
        int bodypart = getResources().getIdentifier(((CheckBox) view).getText().toString(),
                "id", getPackageName());

        // changes visibility of the view
        findViewById(bodypart).setVisibility(visibility);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i=0; i<bodyparts.length; i++) {
            outState.putInt(bodyparts[i], (findViewById(getResources().getIdentifier(bodyparts[i],
                    "id", getPackageName())).getVisibility()));
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for(int i=0; i<bodyparts.length; i++) {
            findViewById(getResources().getIdentifier(bodyparts[i], "id",
                    getPackageName())).setVisibility(savedInstanceState.getInt(bodyparts[i]));
        }
    }
}

