package com.example.coe.createnotification;



import static com.example.coe.adminactivity.admincreateexamactivity.CreateExamActivity.exam_name_txt;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CreateNotificationActivity {
	private static final String AUTH_KEY = "key=AAAAs2Hu63U:APA91bFVW5s3sS-Zn8vngItXldMvELjewMWlpSc9xWo3lRzF-EeABlgL-az1b7uHEQz8VQ0MZ9RnNTV1GdexIQFMTaQZiHp0xT3OygtobTxK7Vto3e4j_u9_zfUk0hANd_v9H6ZGevV0";
	private TextView mTextView;
	private String token;
	private Activity activity;

	//@Override
	public void CreateNotification(Activity activity) {
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//mTextView = findViewById(txt);
		this.activity = activity;
		Bundle bundle = activity.getIntent().getExtras();
		if (bundle != null) {
			String tmp = "";
			for (String key : bundle.keySet()) {
				Object value = bundle.get(key);
				tmp += key + ": " + value + "\n\n";
			}
			//mTextView.setText(tmp);
		}

		FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
			@Override
			public void onComplete(@NonNull Task<InstanceIdResult> task) {
				if (!task.isSuccessful()) {
					token = task.getException().getMessage();
					Log.w("FCM TOKEN Failed", task.getException());
				} else {
					token = task.getResult().getToken();
					Log.i("FCM TOKEN", token);
				}
			}
		});
	}




	public void sendTopic(View view) {
		FirebaseMessaging.getInstance().subscribeToTopic("coe");
		//mTextView.setText(R.string.subscribed);
		sendWithOtherThread("topic");
	}

	private void sendWithOtherThread(final String type) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				pushNotification(type);
			}
		}).start();
	}

	private void pushNotification(String type) {
		JSONObject jPayload = new JSONObject();
		JSONObject jNotification = new JSONObject();
		JSONObject jData = new JSONObject();
		try {
			jNotification.put("title", "Exam Added");
			jNotification.put("body", "New Exam "+exam_name_txt+" is Added");
			jNotification.put("sound", "default");
			jNotification.put("badge", "1");
			jNotification.put("icon", "tce");

			jData.put("picture", "https://upload.wikimedia.org/wikipedia/en/3/32/Thiagarajar_College_of_Engineering_logo.png");

			switch(type) {
				case "topic":
					jPayload.put("to", "/topics/coe");
					break;
			}

			jPayload.put("priority", "high");
			jPayload.put("notification", jNotification);
			jPayload.put("data", jData);

			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", AUTH_KEY);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			// Send FCM message content.
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(jPayload.toString().getBytes());

			// Read FCM response.
			InputStream inputStream = conn.getInputStream();
			final String resp = convertStreamToString(inputStream);

			Handler h = new Handler(Looper.getMainLooper());
			h.post(new Runnable() {
				@Override
				public void run() {
					//mTextView.setText(resp);
				}
			});
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
	}

	private String convertStreamToString(InputStream is) {
		Scanner s = new Scanner(is).useDelimiter("\\A");
		return s.hasNext() ? s.next().replace(",", ",\n") : "";
	}
}