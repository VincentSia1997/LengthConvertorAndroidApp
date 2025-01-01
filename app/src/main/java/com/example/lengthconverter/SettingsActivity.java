package com.example.lengthconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity {

    public static int SETTINGS_REQUEST = 1;
    Spinner changeInput, changeOutput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        changeInput = (Spinner) findViewById(R.id.inputSpinner);
        changeOutput = (Spinner) findViewById(R.id.outputSpinner) ;

        changeInput.setSelection(3);//set default spinner position
        changeOutput.setSelection(1);
    }

    public void doneClicked(View view) {
        String inText = changeInput.getSelectedItem().toString();
        String outText = changeOutput.getSelectedItem().toString();

        Intent intent = new Intent();
        intent.putExtra("changeInput",inText);
        intent.putExtra("changeOutput",outText);
        setResult(RESULT_OK,intent);
        finish();
    }
}