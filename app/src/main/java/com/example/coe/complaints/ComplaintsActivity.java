package com.example.coe.complaints;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coe.R;
import com.example.coe.homeactivity.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.coe.complaints.Complaint;
import java.util.ArrayList;

public class ComplaintsActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private RecyclerView complaintsRecView;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);

        complaintsRecView = findViewById(R.id.recViewComplaints);
        btnBack =  findViewById(R.id.btnPrevious);
        btnAdd = findViewById(R.id.btnRaiseTicket);

        ArrayList<Complaint> complaints = new ArrayList<>();

        complaints.add(new Complaint("Complaint 1","This is Complaint 1 description","11/12/22","pending"));
        complaints.add(new Complaint("Complaint 2","This is Complaint 2 description","12/12/22","done"));
        complaints.add(new Complaint("Complaint 3","This is Complaint 3 description","13/12/22","done"));
        complaints.add(new Complaint("Complaint 4","This is Complaint 4 description","15/12/22","pending"));
        complaints.add(new Complaint("Complaint 5","This is Complaint 5 description","17/12/22","pending"));
        complaints.add(new Complaint("Complaint 6","This is Complaint 6 description","19/12/22","pending"));

        ComplaintsRecAdapter adapter = new ComplaintsRecAdapter(this);
        adapter.setComplaints(complaints);

        complaintsRecView.setAdapter(adapter);
        complaintsRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintsActivity.this, HomeActivity.class));
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ComplaintsActivity.this, com.example.coe.complaints.RaiseComplaintActivity.class));
            }
        });
    }
}