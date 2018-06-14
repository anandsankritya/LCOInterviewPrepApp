package com.interviewprepapp.lco.lcointerviewprepapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Bundle bundle = getIntent().getExtras();

        TextView answerTv = findViewById(R.id.answer_tv);
        TextView questionTv = findViewById(R.id.questions_tv);
        if (bundle != null) {
            String answer = bundle.getString("answer");
            String question = bundle.getString("question");
            questionTv.setText(question);
            answerTv.setText(answer);
        }
        ImageView bannerImage = findViewById(R.id.banner_image);
        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://courses.learncodeonline.in/");
                Intent learnCodeOnlineIntent = new Intent(Intent.ACTION_VIEW, webpage);
                if (learnCodeOnlineIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(learnCodeOnlineIntent);
                }
            }
        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
