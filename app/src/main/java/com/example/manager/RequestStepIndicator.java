package com.example.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.transferwise.sequencelayout.SequenceStep;

public class RequestStepIndicator extends AppCompatActivity {

    SequenceStep sequenceStep1,sequenceStep2,sequenceStep3,sequenceStep4,sequenceStep5;
    Intent intent;
    int status;
    String generatedDate, serviceman, completedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_step_indicator);


        sequenceStep1=findViewById(R.id.step1);
        sequenceStep2=findViewById(R.id.step2);
        sequenceStep3=findViewById(R.id.step3);
        sequenceStep4=findViewById(R.id.step4);
        sequenceStep5=findViewById(R.id.step5);


//        sequenceStep4.setSubtitle(R.string.agent_done);
//        sequenceStep3.setPadding(0,4,0,4);
//
//        sequenceStep4.setTitleTextAppearance(R.style.Title);

        intent = getIntent();
        status = intent.getIntExtra("status",1);
        generatedDate = intent.getStringExtra("generated date");
        serviceman = intent.getStringExtra("serviceman");
        completedDate = intent.getStringExtra("completed date");



        sequenceStep1.setAnchor(generatedDate);

        if(status == 2)
        {
            sequenceStep2.setActive(true);
            sequenceStep2.setAnchor("today");
            sequenceStep2.setTitleTextAppearance(R.style.Title);
            sequenceStep2.setSubtitle("complaint is assigned to "+ serviceman);
            sequenceStep2.setSubtitleTextAppearance(R.style.subTitle);

        }
        else if(status == 3)
        {
            sequenceStep3.setActive(true);

            sequenceStep3.setAnchor("today");
            sequenceStep3.setTitleTextAppearance(R.style.Title);
            sequenceStep3.setSubtitle("request has been generated by "+ serviceman);
            sequenceStep3.setSubtitleTextAppearance(R.style.subTitle);
        }
        else if(status == 5)
        {
            sequenceStep5.setActive(true);
            sequenceStep5.setTitleTextAppearance(R.style.Title);
            sequenceStep5.setAnchor(completedDate);

        }






    }
}
