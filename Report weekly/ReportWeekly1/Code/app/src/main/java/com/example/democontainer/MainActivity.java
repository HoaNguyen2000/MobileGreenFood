package com.example.democontainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgbStyle11, pgbStyle12, pgbStyle13, pgbStyle2;
    EditText etProcess;
    Button btnSetProcess, btnThread, btnIndeterminateMode, btnDialogRing, btnDiaglogProcess;
    AlertDialog alert;
    int progress = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgbStyle11 = (ProgressBar) findViewById(R.id.pgbStyle11);
        pgbStyle12 = (ProgressBar) findViewById(R.id.pgbStyle12);
        pgbStyle13 = (ProgressBar) findViewById(R.id.pgbStyle13);
        pgbStyle2 = (ProgressBar) findViewById(R.id.pgbStyle2);
        etProcess = (EditText) findViewById(R.id.etProcess);
        btnSetProcess = (Button) findViewById(R.id.btnSetProcess);
        btnThread = (Button) findViewById(R.id.btnThread);
        btnIndeterminateMode = (Button) findViewById(R.id.btnIndeterminateMode);
        btnDialogRing = (Button) findViewById(R.id.btnDialogRing);
        btnDiaglogProcess = (Button) findViewById(R.id.btnDiaglogProcess);

        //Event Click
        //Set Progress to pgbStyle2
        btnSetProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgbStyle2.setIndeterminate(false);
                progress = Integer.parseInt( etProcess.getText().toString() );
                pgbStyle2.setProgress(progress);
                Toast.makeText(MainActivity.this, "Progress: "+ progress +"/100", Toast.LENGTH_LONG).show();
            }
        });
        //Start thread loop to pgbStyle2
        btnThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress = 0;
                pgbStyle2.setIndeterminate(false);
                setProgressValue(progress);
            }
        });
        //On mode Indeterminate
        btnIndeterminateMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pgbStyle2.setIndeterminate(true);
                Toast.makeText(MainActivity.this, "On mode Indeterminate", Toast.LENGTH_LONG).show();
            }
        });
        btnDialogRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnDiaglogProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.setTitle("Alert Progress");
                alert.setMessage("Loading...");
                alert.show();
            }
        });
    }
    private void setProgressValue(final int progress) {

        // set the progress
        pgbStyle2.setProgress(progress);
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                setProgressValue(progress + 5);

            }
        });
        thread.start();
    }

}