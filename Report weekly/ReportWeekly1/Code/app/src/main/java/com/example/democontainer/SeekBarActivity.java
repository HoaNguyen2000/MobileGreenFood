package com.example.democontainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class SeekBarActivity extends AppCompatActivity {
    SeekBar sbDemo;
    EditText etSeekProcess;
    Button btnSetSeekProcess;
    int progressSeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar);
        sbDemo = (SeekBar) findViewById(R.id.sbDemo);
        etSeekProcess = (EditText) findViewById(R.id.etSeekProcess);
        btnSetSeekProcess = (Button) findViewById(R.id.btnSetSeekProcess);

        btnSetSeekProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sbDemo.setIndeterminate(false);
                progressSeek = Integer.parseInt( etSeekProcess.getText().toString() );
                sbDemo.setProgress(progressSeek);
                Toast.makeText(SeekBarActivity.this, "Progress: "+ progressSeek +"/100", Toast.LENGTH_LONG).show();
            }
        });
        sbDemo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int num = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                num  = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(SeekBarActivity.this, "Progress: "+ num +"/100", Toast.LENGTH_LONG).show();
            }
        });
    }
}