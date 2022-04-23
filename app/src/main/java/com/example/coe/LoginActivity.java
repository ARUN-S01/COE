package com.example.coe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.adminactivity.AdminHomeActivity;
import com.example.coe.homeactivity.HomeActivity;
import static com.example.coe.SignupActivity.name;
import org.bson.Document;
import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class LoginActivity extends AppCompatActivity {
    public static AtomicReference<User> user;
    public static App app;
    boolean erro = false;
    @Override
    public void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);
        Realm.init(this);
        setContentView(R.layout.login_activity);
       // Toast.makeText(LoginActivity.this,name,Toast.LENGTH_SHORT)
        try{
            //System.out.println(name);
            String sample = name.toLowerCase();
           // Toast.makeText(LoginActivity.this,name,Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            erro = true;
        }
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        EditText login_email = (EditText) findViewById(R.id.loginemail);
        EditText login_password = (EditText) findViewById(R.id.loginpassword);
        Button login = (Button) findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final VibrationEffect vibrationEffect2;
                String email = login_email.getText().toString();
                String password = login_password.getText().toString();
                if(email.length() == 0 | password.length() == 0){
                    Toast.makeText(LoginActivity.this,"Please Fill All the Fields given",Toast.LENGTH_SHORT).show();
                    login.startAnimation(AnimationUtils.loadAnimation(LoginActivity.this,R.anim.shake));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
                        vibrator.cancel();
                        vibrator.vibrate(vibrationEffect2);
                    }
                }
                else{
                    String appID = "coe_tce-kacth"; // replace this with your App ID
                    app = new App(new AppConfiguration.Builder(appID).build());

                    Credentials emailPasswordCredentials = Credentials.emailPassword(email, password);

                    user = new AtomicReference<User>();
                    app.loginAsync(emailPasswordCredentials, it -> {
                        if (it.isSuccess()) {
                            Log.v("AUTH", "Successfully authenticated using an email and password.");
                            user.set(app.currentUser());
                          //  Toast.makeText(LoginActivity.this,customUserData.toString(), Toast.LENGTH_SHORT).show();
                            if(erro){

                            }
                            else{
                                createField();
                            }
                            Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                            if(app.currentUser().getId().equals("62507e2b8039fe7bc176d9f3")){
                                startActivity(new Intent(LoginActivity.this,AdminHomeActivity.class));
                            }
                            else{
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }
                            finish();
                            //Toast.makeText(LoginActivity.this, app.currentUser().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,it.getError().toString(),Toast.LENGTH_SHORT).show();
                            Log.e("AUTH", it.getError().toString());
                        }
                    });

                }
            }
        });
        TextView have_no_account = (TextView) findViewById(R.id.backregister);
        have_no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(signup);
            }
        });
    }
    public void createField(){
        if(name.length() != 0 | name != null){
            User user = app.currentUser();
            MongoClient mongoClient = user.getMongoClient("mongodb-atlas");
            MongoDatabase mongoDatabase = mongoClient.getDatabase("coe");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("data");
            mongoCollection.insertOne(
                    new Document("user-id-field", user.getId()).append("name",name))
                    .getAsync(result -> {
                        if (result.isSuccess()) {
                            //Toast.makeText(LoginActivity.this,"Inserted custom user data document. _id of inserted document: "
                                //    + result.get().getInsertedId(),Toast.LENGTH_SHORT).show();
                            User user1 = app.currentUser();
                            Document customUserData = user1.getCustomData();
                            //Toast.makeText(LoginActivity.this,customUserData.toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,result.getError().toString(),Toast.LENGTH_SHORT).show();
                            Log.e("EXAMPLE", "Unable to insert custom user data. Error: " + result.getError());
                        }
                    });
            MongoCollection<Document> mongoCollection1 = mongoDatabase.getCollection("exam_registeration");
            mongoCollection1.insertOne(
                    new Document("user-id-field", user.getId()).append("course_name","nil").append("exam_date","nil").append("exam_fees","nil").append("status","Not Completed").append("result","Nil"))
                    .getAsync(result -> {
                        if (result.isSuccess()) {
                            //Toast.makeText(LoginActivity.this,"Inserted custom user data document. _id of inserted document: "
                            //    + result.get().getInsertedId(),Toast.LENGTH_SHORT).show();
                           // User user1 = app.currentUser();
                           // Document customUserData = user1.getCustomData();
                            //Toast.makeText(LoginActivity.this,customUserData.toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,result.getError().toString(),Toast.LENGTH_SHORT).show();
                            Log.e("EXAMPLE", "Unable to insert custom user data. Error: " + result.getError());
                        }
                    });
            MongoCollection<Document> mongoCollection2 = mongoDatabase.getCollection("complaints");
            mongoCollection2.insertOne(
                    new Document("user-id-field", user.getId()).append("complaint",false).append("complaint_name","nil").append("complaint_description","nil").append("complaint_date","0/0/0").append("complaint_status","nil"))
                    .getAsync(result -> {
                        if (result.isSuccess()) {
                            //Toast.makeText(LoginActivity.this,"Inserted custom user data document. _id of inserted document: "
                            //    + result.get().getInsertedId(),Toast.LENGTH_SHORT).show();
                            User user1 = app.currentUser();
                            Document customUserData = user1.getCustomData();
                            //Toast.makeText(LoginActivity.this,customUserData.toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this,result.getError().toString(),Toast.LENGTH_SHORT).show();
                            Log.e("EXAMPLE", "Unable to insert custom user data. Error: " + result.getError());
                        }
                    });
        }
        else{
            return;
        }
    }
}
