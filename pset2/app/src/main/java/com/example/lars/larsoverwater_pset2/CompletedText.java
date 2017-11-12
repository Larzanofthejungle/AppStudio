package com.example.lars.larsoverwater_pset2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CompletedText extends AppCompatActivity {

    TextView textView;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mad Libs: Completed Text");
        setContentView(R.layout.activity_completed_text);
        textView = findViewById(R.id.completedText);
        intent = getIntent();
        textView.setText(intent.getStringExtra("completedText"));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
