package com.example.coe.adminactivity.admincreateexamactivity;

import static com.example.coe.LoginActivity.app;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;
import com.example.coe.createnotification.CreateNotificationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class CreateExamActivity extends AppCompatActivity {
    final Calendar myCalendar= Calendar.getInstance();
    EditText examdate,exam_lastdate,exam_lastdate_fine;
    EditText exam_name,exam_fee,exam_eligibility;
    public static String exam_name_txt=null,exam_fee_txt=null,exam_eli_txt=null;
    String examdate_txt=null,exam_lastdate_txt=null,exam_lastdate_fine_txt=null;
    Button create_exam = null;
    ImageView pre;

    @Override
    public void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);
        setContentView(R.layout.activity_create_exam);
        examdate = (EditText) findViewById(R.id.txtExamDate);
        exam_lastdate= (EditText) findViewById(R.id.txtLastDate);
        exam_lastdate_fine = (EditText) findViewById(R.id.txtLastDateWithFine);
        exam_name = (EditText) findViewById(R.id.txtAdminExamName);
        exam_fee = (EditText) findViewById(R.id.txtAdminFee);
        exam_eligibility = (EditText) findViewById(R.id.txtAdminEligibility);

        create_exam = (Button) findViewById(R.id.btnCreateExam) ;

        pre = (ImageView) findViewById(R.id.btnPrevious);

        DatePickerDialog.OnDateSetListener date_ =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel_();
            }
        };
        DatePickerDialog.OnDateSetListener date_1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel_1();
            }
        };
        DatePickerDialog.OnDateSetListener date_2 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel_2();
            }
        };
        examdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(CreateExamActivity.this,date_,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        exam_lastdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateExamActivity.this,date_1,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        exam_lastdate_fine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateExamActivity.this,date_2,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        create_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exam_name_txt = exam_name.getText().toString();
                exam_fee_txt = exam_fee.getText().toString();
                exam_eli_txt = exam_eligibility.getText().toString();
                CreateExam();
                CreateNotificationActivity cre = new CreateNotificationActivity();
                cre.CreateNotification(CreateExamActivity.this);
                cre.sendTopic(v);
            }
        });
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void updateLabel_(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        examdate.setText(dateFormat.format(myCalendar.getTime()));
        examdate_txt = dateFormat.format(myCalendar.getTime());
    }
    private void updateLabel_1(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        exam_lastdate.setText(dateFormat.format(myCalendar.getTime()));
        exam_lastdate_txt = dateFormat.format(myCalendar.getTime());
    }
    private void updateLabel_2(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        exam_lastdate_fine.setText(dateFormat.format(myCalendar.getTime()));
        exam_lastdate_fine_txt = dateFormat.format(myCalendar.getTime());
    }
    public void CreateExam(){
        User user = app.currentUser();
        MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("coe");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("admin_exams");

        mongoCollection.insertOne(new Document("exam_name",exam_name_txt).append("exam_fee",exam_fee_txt).append("last_date",exam_lastdate_txt).append("is_available","true").append("eligibility",exam_eli_txt).append("exam_date",examdate_txt)).getAsync(result ->{
            if(result.isSuccess()){
                Toast.makeText(CreateExamActivity.this,"Exam Added",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(CreateExamActivity.this,"Error Obtained :" + result.getError().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        MongoCollection<Document> mongo_col = mongoDatabase.getCollection("notifications");
        mongo_col.insertOne(new Document("title","Exam Added").append("body","New Exam Added").append("visible","true")).getAsync(result -> {
            if(result.isSuccess()){

            }
            else{

            }
        });
    }

    //Create Notification
}
