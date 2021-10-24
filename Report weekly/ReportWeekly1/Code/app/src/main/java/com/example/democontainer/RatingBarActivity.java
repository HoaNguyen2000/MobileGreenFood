package com.example.democontainer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RatingBarActivity extends AppCompatActivity {
    Button btnSubmit;
    TextView txtResultRatingSubmit, txtCountRating, txtAvgRating;
    RatingBar rtbUser, rtbResult;
    List<Float> allRatings = new ArrayList<Float>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtResultRatingSubmit = (TextView) findViewById(R.id.txtResultRatingSubmit);
        txtCountRating = (TextView) findViewById(R.id.txtCountRating);
        txtAvgRating = (TextView) findViewById(R.id.txtAvgRating);
        rtbUser = (RatingBar) findViewById(R.id.rtbUser);
        rtbResult = (RatingBar) findViewById(R.id.rtbResult);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float rating = rtbUser.getRating();
                allRatings.add(rating);
                int ratingCount = allRatings.size();
                float ratingSum = 0f;
                for(Float r: allRatings)  {
                    ratingSum += r;
                }
                float averageRating = ratingSum / ratingCount;
                txtCountRating.setText("Số lượt đánh giá: " + ratingCount);
                txtAvgRating.setText("Số sao trung bình: " + averageRating);

                float ratingAll = rtbResult.getNumStars() * averageRating / rtbUser.getNumStars() ;
                rtbResult.setRating(ratingAll);
            }
        });
        rtbUser.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                txtResultRatingSubmit.setText("Sao:"+ v);
            }
        });
    }
}