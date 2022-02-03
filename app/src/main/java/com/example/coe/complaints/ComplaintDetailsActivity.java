package com.example.coe.complaints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;
import com.google.android.material.button.MaterialButton;

public class ComplaintDetailsActivity extends AppCompatActivity {

    private Complaint complaint;
    private TextView txtIssueName,txtIssueDescription;
    private MaterialButton btnWithdrawComplaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details);
        ImageView back = (ImageView) findViewById(R.id.btnPrevious);
        complaint = (Complaint) getIntent().getSerializableExtra("complaint");

        txtIssueName = findViewById(R.id.txtIssueName);
        txtIssueDescription = findViewById(R.id.txtIssueDescription);
        btnWithdrawComplaint = findViewById(R.id.btnWithDraw);

        txtIssueName.setText(complaint.getIssueName());
        txtIssueDescription.setText(complaint.getIssueDetails());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(ComplaintDetailsActivity.this,ComplaintsActivity.class));
            }
        });

    }
}