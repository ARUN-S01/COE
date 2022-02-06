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

import com.example.coe.payment.FeePaymentOptionActivity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.bson.Document;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;

public class SignupActivity extends AppCompatActivity {
    static public String name = null;
    @Override
    public void onCreate(Bundle SavedInstance){
        super.onCreate(SavedInstance);
        Realm.init(this); // context, usually an Activity or Application
        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        setContentView(R.layout.signup_activity);
        EditText register_email = (EditText) findViewById(R.id.registeremail);
        EditText register_password = (EditText) findViewById(R.id.registerpassword);
        EditText register_name = (EditText) findViewById(R.id.registername);
        Button register = (Button) findViewById(R.id.registerbtn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final VibrationEffect vibrationEffect2;
                String email = register_email.getText().toString();
                String password = register_password.getText().toString();
                name = register_name.getText().toString();
                if(email.length() == 0 | password.length() == 0 | name.length() == 0){
                    Toast.makeText(SignupActivity.this,"Please Fill All the Fields given",Toast.LENGTH_SHORT).show();
                    register.startAnimation(AnimationUtils.loadAnimation(SignupActivity.this,R.anim.shake));
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
                        vibrator.cancel();
                        vibrator.vibrate(vibrationEffect2);
                    }
                }
                else{
                    String appID = "coe_tce-kacth"; // replace this with your App ID
                    App app = new App(new AppConfiguration.Builder(appID).appName("coe_tce").requestTimeout(30, TimeUnit.SECONDS).build());
                    app.getEmailPassword().registerUserAsync(email, password, it -> {
                        if (it.isSuccess()) {
                            Toast.makeText(SignupActivity.this,"Successfully registered user",Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignupActivity.this,"Please Login To Continue", Toast.LENGTH_SHORT).show();
                            try {
                                TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                            finish();
                            Log.i("EXAMPLE", "Successfully registered user.");
                        } else {
                            Toast.makeText(SignupActivity.this,it.getError().toString(),Toast.LENGTH_SHORT).show();
                            Log.e("EXAMPLE", "Failed to register user: " + it.getError().getErrorMessage());
                        }
                    });
                }
            }
        });
        TextView back_login = (TextView) findViewById(R.id.backlogin);
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
