package com.example.lars.larsoverwater_pset2;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


public class FillInScreen extends AppCompatActivity {

    AssetManager assetManager;
    InputStream inputStream;
    Story story;
    EditText editText;
    TextView textView;
    TextView placeHolderCount;
    Button button;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Mad Libs: Fill In");
        setContentView(R.layout.activity_fill_in_screen);
        textView = findViewById(R.id.textView);
        placeHolderCount = findViewById(R.id.placeHolderCount);
        button = findViewById(R.id.nextPlaceholder);
        editText = findViewById(R.id.fillIn);
        intent = getIntent();
        try {
            GetStory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeFillIn(){
        textView.setText("Please, give me a/an " + story.getNextPlaceholder());
        placeHolderCount.setText(story.getPlaceholderRemainingCount() + " remaining words");
    }

    public void clearFillIn(){
        editText.setText(" ");
    }

    public void goToCompletedText(){
        intent = new Intent(this, CompletedText.class);
        intent.putExtra("completedText", story.toString());
        startActivity(intent);
        finish();
    }

    public void GetStory() throws IOException {
        assetManager = getAssets();
        inputStream = assetManager.open(intent.getStringExtra("textFile"));
        story = new Story(inputStream);
        System.out.println(story.toString());
        changeFillIn();
        clearFillIn();
    }

    public void nextPlaceholder(View view) {
        story.fillInPlaceholder(editText.getText().toString());
        if (story.getPlaceholderRemainingCount() == 0) {
            goToCompletedText();
        }
        else if (story.getPlaceholderRemainingCount() == 1) {
            button.setText("Complete");
        }
        changeFillIn();
        clearFillIn();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("text", story.toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String text = savedInstanceState.getString("text");
        try {
            inputStream = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        story = new Story((InputStream) inputStream);
        System.out.println(story.toString());
        changeFillIn();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
