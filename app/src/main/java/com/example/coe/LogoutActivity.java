package com.example.coe;

import static com.example.coe.LoginActivity.app;
import static com.example.coe.complaints.RaiseComplaintActivity.date;
import static com.example.coe.complaints.RaiseComplaintActivity.iss_details;
import static com.example.coe.complaints.RaiseComplaintActivity.iss_name;
import static com.example.coe.complaints.RaiseComplaintActivity.status;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.mongodb.User;

public class LogoutActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle SavedInstance){
        super.onCreate(SavedInstance);
        User user = app.currentUser();
        app.currentUser().logOutAsync(it ->{
            if(it.isSuccess()){
                Toast.makeText(LogoutActivity.this,"Logged Out",Toast.LENGTH_SHORT).show();
                iss_name = null;iss_details = null; date = null; status = null;
                startActivity(new Intent(LogoutActivity.this,LoginActivity.class));
                System.exit(0);
            }
            else{
                Toast.makeText(LogoutActivity.this,it.getError().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
