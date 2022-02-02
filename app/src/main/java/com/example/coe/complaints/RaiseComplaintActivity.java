package com.example.coe.complaints;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;

public class RaiseComplaintActivity extends AppCompatActivity {

    private String[] complaint_types = {"complaint type 1","complaint type 2","complaint type 3","complaint type 4","complaint type 5"};

    private AutoCompleteTextView autoCompleteTextView;

    private ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_complaint);

        autoCompleteTextView = findViewById(R.id.listComplaintType);

        adapterItems = new ArrayAdapter<String>(this,R.layout.complaint_type_item,complaint_types);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),item,Toast.LENGTH_LONG).show();
            }
        });

    }
}