package com.example.lengthconverter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText inputLength;
    private TextView outputLength, inputUnit, outputUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputLength = (EditText) findViewById(R.id.inputLengthEditText);
        inputUnit = (TextView) findViewById(R.id.inputUnitText);
        outputLength = (TextView) findViewById(R.id.outputLengthText);
        outputUnit = (TextView) findViewById(R.id.outputUnitText);


        if (savedInstanceState != null) {
            String savedinputValue = savedInstanceState.getString("inputValue");
            inputLength.setText(savedinputValue);
            String savedinputUnit = savedInstanceState.getString("inputUnit");
            inputUnit.setText(savedinputUnit);
            String savedoutputValue = savedInstanceState.getString("outputValue");
            outputLength.setText(savedoutputValue);
            String savedoutputUnit = savedInstanceState.getString("outputUnit");
            outputUnit.setText(savedoutputUnit);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("inputValue", inputLength.getText().toString());
        outState.putString("inputUnit", inputUnit.getText().toString());
        outState.putString("outputValue", outputLength.getText().toString());
        outState.putString("outputUnit", outputUnit.getText().toString());
    }

    /*Calling conversion method at LengthConverter class, then set output length text*/
    public void convertClick(View view) {
        LengthConverter converter = new LengthConverter();

        String oldUnit = inputUnit.getText().toString();
        String newUnit = outputUnit.getText().toString();

        if(inputLength.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this,"Please input a length",Toast.LENGTH_SHORT).show();
        }
        else {
            double bufferLength = converter.toM(oldUnit, Integer.parseInt(inputLength.getText().toString()));
            double finalLength = converter.fromM(newUnit, bufferLength);
            String outputText = String.valueOf(finalLength);
            outputLength.setText(outputText);
        }
    }

    public void settingsClick(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivityForResult(intent, SettingsActivity.SETTINGS_REQUEST);
    }

    public void clearClick(View view) {
        inputLength.setText("");
        outputLength.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SettingsActivity.SETTINGS_REQUEST) {
            if (resultCode == RESULT_OK) {
                if(data != null) {
                    String changeInput = data.getStringExtra("changeInput");
                    inputUnit.setText(changeInput);
                    String changeOutput = data.getStringExtra("changeOutput");
                    outputUnit.setText(changeOutput);

                    /*Redo calculations with new unit if input edit text is not empty*/
                    if(!inputLength.getText().toString().equals("")) {
                        String oldUnit = inputUnit.getText().toString();
                        String newUnit = outputUnit.getText().toString();
                        LengthConverter converter = new LengthConverter();
                        double bufferUnit = converter.toM(oldUnit, Integer.parseInt(inputLength.getText().toString()));
                        double finalLength = converter.fromM(newUnit, bufferUnit);
                        String outputText = String.valueOf(finalLength);
                        outputLength.setText(outputText);
                    }

                }
            }
        }
    }
}