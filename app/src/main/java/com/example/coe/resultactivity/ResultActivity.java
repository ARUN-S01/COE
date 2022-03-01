package com.example.coe.resultactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;

public class ResultActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle SavedInstance) {

        super.onCreate(SavedInstance);
        setContentView(R.layout.activity_result);
        ImageView pre = (ImageView) findViewById(R.id.imageViewres);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
