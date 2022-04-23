package com.example.coe.adminactivity;

import static com.example.coe.LoginActivity.app;

import com.example.coe.LogoutActivity;
import com.example.coe.adminactivity.examsactivity.AdminExamsActivity;
import com.example.coe.adminactivity.managecomplaintsactivity.ManageComplaints;
import com.example.coe.adminactivity.useractivity.AdminUser;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coe.R;
import com.example.coe.adminactivity.admincreateexamactivity.CreateExamActivity;
import com.example.coe.adminactivity.useractivity.UsersListActivity;
import com.example.coe.examactivity.Exam;
import com.example.coe.examactivity.ExamsAdapter;
import com.example.coe.examactivity.OnLoadMoreListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Map;

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class AdminHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    TextView user_name;
    private ArrayList<Exam> exams;
    private RecyclerView recyclerView;
    private FloatingActionButton btnCreateExam;
    public static ArrayList<AdminUser> users = new ArrayList<AdminUser>();
    private ExamsAdapter contactAdapter;

    //private Realm backgroundThreadRealm;
    //public static boolean dataAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homeactivity);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        drawerLayout = findViewById(R.id.admindrawerview);
        navigationView = findViewById(R.id.admin_navigation_view);
        toolbar = findViewById(R.id.admin_topBar);
        btnCreateExam = findViewById(R.id.btnAddExam);

        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);
        exams = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.adminExamsRecyclerView);

        User user1 = app.currentUser();

        MongoClient mongoClient = user1.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("coe");
        //Exams Collection
        Document docs = new Document();
        docs.put("is_available","true");

        MongoCollection<Document> admin = mongoDatabase.getCollection("admin_exams");

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
                Toast.makeText(AdminHomeActivity.this, task.getError().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        Map<String,User> user2 = app.allUsers();
        for(User map : user2.values()){
            users.add(new AdminUser(map.getProfile().getEmail(),map.getId()));
        }
        View headerView = navigationView.getHeaderView(0);
        //user_name = (TextView) headerView.findViewById(R.id.username_home);
        //user_name.setText("Admin");

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

        btnCreateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this, CreateExamActivity.class));
            }
        });

    }

//    private void addSampleData() {
//
//        if(!dataAdded)
//        {
//            for (int i = 1; i < 30; i++) {
//
//                int finalI = i;
//                backgroundThreadRealm.executeTransaction(realm->{
//                    Number num = realm.where(Exam.class).max("_id");
//                    int nextID;
//                    if(num == null) {
//                        nextID = 1;
//                    } else {
//                        nextID = num.intValue() + 1;
//                    }
//                    Exam exam = realm.createObject(Exam.class,nextID);
//                    exam.setExamFee(feeGeneration());
//                    exam.setEligibility("2nd year");
//                    exam.setExamName("Course" + finalI);
//                    exam.setExamDate("01/04/23");
//                    exam.setLastDate("12/12/22");
//                    exam.setLastDateWithFine("15/12/22");
//                    if(finalI %3==0)
//                        exam.setRegistered(true);
//                    realm.insert(exam);
//                });
//            }
//        }
//
//
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navHome:
                // Add your navigational page code
                break;
            case R.id.navAnnouncements:
                // Add your navigational page code
                break;
            case R.id.navTimeTable:
                // Add your navigational page code
                break;
            case R.id.navResult:
                startActivity(new Intent(AdminHomeActivity.this,PublishResultActivity.class));
                // Add your navigational page code
                break;
            case R.id.navAdminExam:
                startActivity(new Intent(AdminHomeActivity.this, AdminExamsActivity.class));
                break;
            case R.id.navComplaints:
                startActivity(new Intent(AdminHomeActivity.this, ManageComplaints.class));
                //startActivity(new Intent(AdminHomeActivity.this, ComplaintsActivity.class));
                break;
            case R.id.navAdmin:
                //startActivity(new Intent(AdminHomeActivity.this,AdminActivity.class));
                //startActivity(new Intent(AdminHomeActivity.this, AdminExamsActivity.class));
                break;

            case R.id.navUsers:
                startActivity(new Intent(AdminHomeActivity.this, UsersListActivity.class));
                break;

            case R.id.navAdminSeat:
                startActivity(new Intent(AdminHomeActivity.this,PublishSeatAllotmentActivity.class));
                break;

            case R.id.AdminNavLogout:
                startActivity(new Intent(AdminHomeActivity.this, LogoutActivity.class));
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
}
