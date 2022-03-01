package com.example.coe.examactivity;

import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_date;
import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_eli;
import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_fee;
import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_last_date;
import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_name;
import static com.example.coe.examactivity.ExamsAdapter.is_admin;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;
import com.example.coe.examregisterationactivity.ExamRegisterationActivity;

public class ExamDetails extends AppCompatActivity {
    @Override
    public void onCreate(Bundle SavedInstance){
        super.onCreate(SavedInstance);
        setContentView(R.layout.exam_registerationdetails);
        TextView name = (TextView) findViewById(R.id.course_name);
        TextView date = (TextView) findViewById(R.id.exam_date);
        TextView eli = (TextView) findViewById(R.id.eligibility);
        TextView fee = (TextView) findViewById(R.id.exam_fees);
        TextView last_dat = (TextView) findViewById(R.id.last_date);
        name.setText(clicked_exam_name);
        name.setMovementMethod(new ScrollingMovementMethod());
        name.setHorizontallyScrolling(true);
        date.setText(clicked_exam_date);
        eli.setText(clicked_exam_eli);
        eli.setMovementMethod(new ScrollingMovementMethod());
        eli.setHorizontallyScrolling(true);
        fee.setText(clicked_exam_fee);
        last_dat.setText(clicked_exam_last_date);
        Button back_details = (Button) findViewById(R.id.backbutton);
        Button register_details = (Button) findViewById(R.id.registerbutton);
        ImageView btnpre = (ImageView) findViewById(R.id.btnpre);
        TextView title = (TextView) findViewById(R.id.timetable);
        if(is_admin){
            title.setText("Exam Details");
            register_details.setVisibility(View.GONE);
        }
        else{
            register_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(ExamDetails.this, ExamRegisterationActivity.class));
                }
            });
        }


        back_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
