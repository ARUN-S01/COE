package com.example.coe.homeactivity;



import static com.example.coe.LoginActivity.app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coe.LogoutActivity;
import com.example.coe.R;
import com.example.coe.complaints.ComplaintsActivity;
import com.example.coe.examactivity.Exam;
import com.example.coe.examactivity.ExamsAdapter;
import com.example.coe.examactivity.OnLoadMoreListener;
import com.example.coe.notificationactivity.Notification;
import com.example.coe.notificationactivity.NotificationActivity;
import com.example.coe.resultactivity.ResultActivity;
import com.example.coe.seatallotmentactivity.SeatAllotmentActivity;
import com.example.coe.timetableactivity.TimeTableActivity;
import com.google.android.material.navigation.NavigationView;


import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.FindIterable;
import io.realm.mongodb.mongo.iterable.MongoCursor;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
  //  public long count = 0;
  //  public long or_count = 0;
    Toolbar toolbar;
    TextView user_name;
    public static ArrayList<Notification> notification =  null;
    private ArrayList<Exam> exams;
    private RecyclerView recyclerView;
    private ExamsAdapter contactAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        drawerLayout = findViewById(R.id.drawerview);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.topBar);



        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);
        exams = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.examsRecyclerView);

        User user1 = app.currentUser();
        //Map<String, User> user2 = app.allUsers();
        //for(User map : user2.values()){
          //  Toast.makeText(HomeActivity.this,map.getProfile().getEmail(),Toast.LENGTH_SHORT).show();
        //}
        //Toast.makeText(HomeActivity.this,user2.toString(),Toast.LENGTH_SHORT).show();
        Document doc = new Document("user-id-field",user1.getId());
        MongoClient mongoClient = user1.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("coe");

        //Exams Collection
        Document docs = new Document();
        docs.put("is_available","true");


       if(notification == null){
            notifications(mongoDatabase);
       }

        MongoCollection<Document> admin = mongoDatabase.getCollection("admin_exams");
        //admin.count().getAsync(task -> {
          //  if (task.isSuccess()) {
            //    count = task.get();
              //  Log.v("EXAMPLE",
                //        "successfully counted, number of documents in the collection: " +
                  //              count);
            //} else {
              //  Log.e("EXAMPLE", "failed to count documents with: ", task.getError());
           // }
        //});
        RealmResultTask<MongoCursor<Document>> findTask = admin.find(docs).iterator();
        findTask.getAsync(task -> {
            if (task.isSuccess()) {
                MongoCursor<Document> results = null;
                results = task.get();
                while(results.hasNext()){
                    Exam exam = new Exam();
                    Document ds = results.next();
                    exam.setExamDate(ds.getString("exam_date"));
                    exam.setEligibility(ds.get("eligibility").toString());
                    exam.setFee(ds.get("exam_fee").toString());
                    exam.setExamName(ds.get("exam_name").toString());
                    exam.setLastDate(ds.get("last_date").toString());
                    exam.setRegistered(false);
                    exams.add(exam);
                }
            } else {
                Toast.makeText(HomeActivity.this, task.getError().toString(),Toast.LENGTH_SHORT).show();
            }
        });



        View headerView = navigationView.getHeaderView(0);
        user_name = (TextView) headerView.findViewById(R.id.username_home);

        //UserName Collection
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("data");
        mongoCollection.findOne(doc).getAsync(task -> {
            if (task.isSuccess()) {
                Document result = task.get();
                //Toast.makeText(HomeActivity.this,result.get("name").toString(),Toast.LENGTH_SHORT).show();
                //to_update = result.get("name").toString();
                user_name.setText(result.get("name").toString());
                Log.v("EXAMPLE", "successfully found a document: " + result);
            } else {
                Log.e("EXAMPLE", "failed to find document with: ", task.getError());
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ExamsAdapter((androidx.recyclerview.widget.RecyclerView) recyclerView, exams, this);
        recyclerView.setAdapter(contactAdapter);


        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //Toast.makeText(HomeActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navHome:
                // Add your navigational page code

                break;
            case R.id.navAnnouncements:
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                // Add your navigational page code
                break;
            case R.id.navTimeTable:
                startActivity(new Intent(HomeActivity.this, TimeTableActivity.class));
                // Add your navigational page code
                break;
            case R.id.navResult:
                startActivity(new Intent(HomeActivity.this, ResultActivity.class));
                // Add your navigational page code
                break;
            case R.id.navComplaints:

                startActivity(new Intent(HomeActivity.this, ComplaintsActivity.class));

                break;
            case R.id.navSeating:
                startActivity(new Intent(HomeActivity.this, SeatAllotmentActivity.class));
                break;
            case R.id.navLogout:
                startActivity(new Intent(HomeActivity.this, LogoutActivity.class));
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    private String feeGeneration() {
        return "Rs.1200" ;
    }
    public void notifications(MongoDatabase mongo){
        notification = new ArrayList<>();
        MongoCollection<Document> mongo_collection = mongo.getCollection("notifications");

        Document docs = new Document();
        docs.put("visible","true");
        RealmResultTask<MongoCursor<Document>> findTask = mongo_collection.find(docs).iterator();

        findTask.getAsync(task -> {
            if(task.isSuccess()){
                MongoCursor<Document> result = task.get();
                while(result.hasNext()){
                    //or_count += 1;
                    Notification notifi = new Notification();
                    Document ds = result.next();
                    notifi.setTitle(ds.get("title").toString());
                    notifi.setBody(ds.get("body").toString());
                    notification.add(notifi);
                }
            }
        });
        return;
    }

}

