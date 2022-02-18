package com.example.coe.complaints;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coe.LoginActivity;
import com.example.coe.R;

import java.util.ArrayList;

public class RaiseComplaintActivity extends AppCompatActivity {

    private String[] complaint_types = {"complaint type 1","complaint type 2","complaint type 3","complaint type 4","complaint type 5"};

    private AutoCompleteTextView autoCompleteTextView;
    public static String iss_name;
    public static String iss_details;
    public static String date;
    public static String status;

    private ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setContentView(R.layout.activity_raise_complaint);
        ImageView im_back = (ImageView) findViewById(R.id.btnPrevious);

        Button raise = (Button) findViewById(R.id.raise);
        EditText usr_txt = (EditText) findViewById(R.id.txtName);

        EditText complaint_name = (EditText) findViewById(R.id.txtComplaintName);
        EditText complaint_description = (EditText) findViewById(R.id.txtCompalintDescription);

        autoCompleteTextView = findViewById(R.id.listComplaintType);

        adapterItems = new ArrayAdapter<String>(this,R.layout.complaint_type_item,complaint_types);

        autoCompleteTextView.setAdapter(adapterItems);
        final String[] item = {null};
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                item[0] = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), item[0],Toast.LENGTH_LONG).show();
            }
        });
        raise.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                final VibrationEffect vibrationEffect2;
                String txt_name = usr_txt.getText().toString();
                String com_name = complaint_name.getText().toString();
                String com_description = complaint_description.getText().toString();
                if(txt_name.length() ==0 | com_name.length() == 0 | com_description.length() == 0){
                    Toast.makeText(RaiseComplaintActivity.this,"Please Fill All the Fields given",Toast.LENGTH_SHORT).show();
                    raise.startAnimation(AnimationUtils.loadAnimation(RaiseComplaintActivity.this,R.anim.shake));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
                        vibrator.cancel();
                        vibrator.vibrate(vibrationEffect2);
                    }
                }
                else{
                    iss_name = com_name;
                    iss_details = com_description;
                    date = java.time.LocalDate.now().toString();
                    status = "Pending";
                    //finishAffinity();
                    startActivity(new Intent(RaiseComplaintActivity.this,ComplaintsActivity.class));
                }
            }
        });
        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}