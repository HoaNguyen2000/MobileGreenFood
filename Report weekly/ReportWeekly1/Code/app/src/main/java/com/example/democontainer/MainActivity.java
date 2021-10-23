package com.example.democontainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgbStyle11, pgbStyle12, pgbStyle13, pgbStyle2;
    EditText etProcess;
    Button btnSetProcess, btnThread, btnIndeterminateMode, btnDialogRing, btnDiaglogProcess;
    int progess;
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
                progess = Integer.parseInt( etProcess.getText().toString() );
                pgbStyle2.setProgress(progess);

            }
        });
    }
}