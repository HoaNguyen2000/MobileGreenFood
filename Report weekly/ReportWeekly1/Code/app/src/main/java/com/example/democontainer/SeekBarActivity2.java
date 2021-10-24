package com.example.democontainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarActivity2 extends AppCompatActivity {
    SeekBar sbDemo2;
    ProgressBar prbForDemo2;
    TextView tvNumProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar2);
        tvNumProgress = (TextView) findViewById(R.id.tvNumProgress);
        prbForDemo2 = (ProgressBar) findViewById(R.id.prbForDemo2);
        sbDemo2= (SeekBar) findViewById(R.id.sbDemo2);
        sbDemo2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                prbForDemo2.setProgress(i);
                tvNumProgress.setText(i+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}