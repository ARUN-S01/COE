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

import com.example.coe.homeactivity.HomeActivity;

import org.w3c.dom.Text;

import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class LoginActivity extends AppCompatActivity {
    public static AtomicReference<User> user;
    public static  App app;
    @Override
    public void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);
        Realm.init(this);
        setContentView(R.layout.login_activity);
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
                    String appID = "application-0-zlrxg"; // replace this with your App ID
                    app = new App(new AppConfiguration.Builder(appID)
                            .build());

                    Credentials emailPasswordCredentials = Credentials.emailPassword(email, password);

                    user = new AtomicReference<User>();
                    app.loginAsync(emailPasswordCredentials, it -> {
                        if (it.isSuccess()) {
                            Log.v("AUTH", "Successfully authenticated using an email and password.");
                            user.set(app.currentUser());
                            Toast.makeText(LoginActivity.this,app.currentUser().getProfile().getUser().toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
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
}
