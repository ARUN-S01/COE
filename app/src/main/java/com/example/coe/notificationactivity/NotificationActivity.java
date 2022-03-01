package com.example.coe.notificationactivity;

import static com.example.coe.LoginActivity.app;
import static com.example.coe.homeactivity.HomeActivity.notification;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coe.R;
import com.example.coe.examactivity.Exam;
import com.example.coe.examactivity.ExamsAdapter;
import com.example.coe.notificationactivity.OnLoadMoreListener;
import com.example.coe.homeactivity.HomeActivity;

import org.bson.Document;

import java.util.ArrayList;

import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class NotificationActivity extends AppCompatActivity {
   // private ArrayList<Notification> notificati;
    private NotificationAdapter contactAdapter;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.notification_activity);
        ImageView btn = (ImageView) findViewById(R.id.btnPrevious1);

      //  User user1 = app.currentUser();
        recyclerView = (RecyclerView) findViewById(R.id.recNotification);

        //notificati = new ArrayList<>();
       // MongoClient mongoClient = user1.getMongoClient("mongodb-atlas");
        //MongoDatabase mongoDatabase = mongoClient.getDatabase("coe");

        //Document docs = new Document();
        //docs.put("visible","true");
        //MongoCollection<Document> noti = mongoDatabase.getCollection("notifications");
        //RealmResultTask<MongoCursor<Document>> findTask = noti.find(docs).iterator();
        //findTask.getAsync(task -> {
          //  if (task.isSuccess()) {
            //    MongoCursor<Document> results = null;
              //  results = task.get();
             //   while(results.hasNext()){
               //     Notification not = new Notification();
               //     Document ds = results.next();
               //     not.setTitle(ds.getString("title"));
                //    not.setBody(ds.getString("body"));
                //    notificati.add(not);
                //    Toast.makeText(NotificationActivity.this,"Found",Toast.LENGTH_SHORT).show();
                //}
            //} else {
              //  Toast.makeText(NotificationActivity.this, task.getError().toString(),Toast.LENGTH_SHORT).show();
            //}
        //});

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new NotificationAdapter((androidx.recyclerview.widget.RecyclerView) recyclerView, notification, this);
        recyclerView.setAdapter(contactAdapter);

        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                //Toast.makeText(HomeActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //notification = null;
                finish();
            }
        });
    }
}
