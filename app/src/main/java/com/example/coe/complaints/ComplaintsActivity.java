package com.example.coe.complaints;

import static com.example.coe.LoginActivity.app;
import static com.example.coe.complaints.RaiseComplaintActivity.date;
import static com.example.coe.complaints.RaiseComplaintActivity.iss_details;
import static com.example.coe.complaints.RaiseComplaintActivity.iss_name;
import static com.example.coe.complaints.RaiseComplaintActivity.status;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coe.R;
import com.example.coe.homeactivity.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.bson.Document;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class ComplaintsActivity extends AppCompatActivity {

    private FloatingActionButton btnAdd;
    private RecyclerView complaintsRecView;
    private ImageView btnBack;
    ComplaintsRecAdapter adapter;
    String already = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        User user1 = app.currentUser();
        Document to_find = new Document("user-id-field",user1.getId());
        MongoClient mongoClient = user1.getMongoClient("mongodb-atlas");
        MongoDatabase mongoDatabase = mongoClient.getDatabase("coe");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("complaints");

        complaintsRecView = findViewById(R.id.recViewComplaints);
        btnBack =  findViewById(R.id.btnPrevious);
        btnAdd = findViewById(R.id.btnRaiseTicket);


        TextView no_comp = (TextView) findViewById(R.id.no_complaints);
        Boolean excep = false;
        ArrayList<Complaint> complaints = new ArrayList<>();

        //to find whether present
        //in_db = "false";
        final String[] if_present = {"false"};

        mongoCollection.findOne(to_find).getAsync(task -> {
            if (task.isSuccess()) {
                Document result = task.get();
                //Toast.makeText(ComplaintsActivity.this,result.getString("complaint_name"),Toast.LENGTH_SHORT).show();
                if(result.getString("complaint_name").equals("nil") | result.getString("complaint_status").equals("nil")){
                    no_comp.setVisibility(View.VISIBLE);
                }
                else{
                    already = "hel";
                    if_present[0] = "true";
                    no_comp.setVisibility(View.GONE);
                    complaints.add(new Complaint(result.getString("complaint_name"),result.getString("complaint_description"),result.getString("complaint_date"),result.getString("complaint_status")));
                    adapter = new ComplaintsRecAdapter(this);
                    adapter.setComplaints(complaints);
                    complaintsRecView.setAdapter(adapter);
                    complaintsRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                }

                //to_update = result.get("name").toString();
                Log.v("EXAMPLE", "successfully found a document: " + result.toString());
            } else {
                Log.e("EXAMPLE", "failed to find document with: ", task.getError());
            }
        });

        //complaints.add(new Complaint("Complaint 1","This is Complaint 1 description","11/12/22","pending"));
        //complaints.add(new Complaint("Complaint 2","This is Complaint 2 description","12/12/22","done"));
        //complaints.add(new Complaint("Complaint 3","This is Complaint 3 description","13/12/22","done"));
        //complaints.add(new Complaint("Complaint 4","This is Complaint 4 description","15/12/22","pending"));
        //complaints.add(new Complaint("Complaint 5","This is Complaint 5 description","17/12/22","pending"));
        //complaints.add(new Complaint("Complaint 6","This is Complaint 6 description","19/12/22","pending"));
       // Toast.makeText(ComplaintsActivity.this,in_db,Toast.LENGTH_SHORT).show();
        try{
            Toast.makeText(ComplaintsActivity.this,iss_name,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            excep = true;
        }
        //Toast.makeText(ComplaintsActivity.this,if_present[0],Toast.LENGTH_SHORT).show();
        if(excep == true && if_present[0].equals("true")){
            no_comp.setVisibility(View.VISIBLE);
            complaintsRecView.setVisibility(View.INVISIBLE);

            //Toast.makeText(ComplaintsActivity.this,"not Found in db",Toast.LENGTH_SHORT).show();
        }
        else if(excep == true && if_present[0].equals("false")){
           // Toast.makeText(ComplaintsActivity.this,comp_status,Toast.LENGTH_SHORT).show();


            //complaints.add(new Complaint(comp_name,comp_desc,comp_date,comp_status));
        }
        else{

            Document updateDocument = new Document("$set", new Document("complaint",true));
            mongoCollection.updateOne(to_find, updateDocument).getAsync(task -> {
                if (task.isSuccess()) {
                    long count = task.get().getModifiedCount();
                    if (count == 1) {
                        Log.v("EXAMPLE", "successfully updated a document.");
                    } else {
                        Log.v("EXAMPLE", "did not update a document.");
                    }
                } else {
                    Log.e("EXAMPLE", "failed to update document with: ", task.getError());
                }
            });

            Document updateDocument1 = new Document("$set", new Document("complaint_name",iss_name));
            mongoCollection.updateOne(to_find, updateDocument1).getAsync(task -> {
                if (task.isSuccess()) {
                    long count = task.get().getModifiedCount();
                    if (count == 1) {
                        Log.v("EXAMPLE", "successfully updated a document.");
                    } else {
                        Log.v("EXAMPLE", "did not update a document.");
                    }
                } else {
                    Log.e("EXAMPLE", "failed to update document with: ", task.getError());
                }
            });
            Document updateDocument2 = new Document("$set", new Document("complaint_description",iss_details));
            mongoCollection.updateOne(to_find, updateDocument2).getAsync(task -> {
                if (task.isSuccess()) {
                    long count = task.get().getModifiedCount();
                    if (count == 1) {
                        Log.v("EXAMPLE", "successfully updated a document.");
                    } else {
                        Log.v("EXAMPLE", "did not update a document.");
                    }
                } else {
                    Log.e("EXAMPLE", "failed to update document with: ", task.getError());
                }
            });
            Document updateDocument3 = new Document("$set", new Document("complaint_date",date));
            mongoCollection.updateOne(to_find, updateDocument3).getAsync(task -> {
                if (task.isSuccess()) {
                    long count = task.get().getModifiedCount();
                    if (count == 1) {
                        Log.v("EXAMPLE", "successfully updated a document.");
                    } else {
                        Log.v("EXAMPLE", "did not update a document.");
                    }
                } else {
                    Log.e("EXAMPLE", "failed to update document with: ", task.getError());
                }
            });
            Document updateDocument4 = new Document("$set", new Document("complaint_status",status));
            mongoCollection.updateOne(to_find, updateDocument4).getAsync(task -> {
                if (task.isSuccess()) {
                    long count = task.get().getModifiedCount();
                    if (count == 1) {
                        Log.v("EXAMPLE", "successfully updated a document.");
                    } else {
                        Log.v("EXAMPLE", "did not update a document.");
                    }
                } else {
                    Log.e("EXAMPLE", "failed to update document with: ", task.getError());
                }
            });
            no_comp.setVisibility(View.GONE);
            complaints.add(new Complaint(iss_name,iss_details,date,status));
            //complaints.add(new Complaint(iss_name,iss_details,date,status));
        }
        adapter = new ComplaintsRecAdapter(this);
        adapter.setComplaints(complaints);
        complaintsRecView.setAdapter(adapter);
        complaintsRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(ComplaintsActivity.this, HomeActivity.class));
                iss_name = null;iss_details = null; date = null; status = null;
                //startActivity(new Intent(ComplaintsActivity.this, HomeActivity.class));

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