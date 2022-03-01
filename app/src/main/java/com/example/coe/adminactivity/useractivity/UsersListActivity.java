package com.example.coe.adminactivity.useractivity;

import static com.example.coe.adminactivity.AdminHomeActivity.users;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coe.R;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    private RecyclerView recUsersView;
    private ImageView btnPrev;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_users_list);

       // addDummyData();

        recUsersView = findViewById(R.id.usersRecView);
        btnPrev = findViewById(R.id.btnPrevious_user);

        UsersRecAdapter adapter = new UsersRecAdapter(this);

        adapter.setUsers(users);

        recUsersView.setAdapter(adapter);
        recUsersView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

//    private void addDummyData() {
//
//        users.add(new AdminUser("User Name 1","username1@gmail.com"));
//        users.add(new AdminUser("User Name 2","username2@gmail.com"));
//        users.add(new AdminUser("User Name 3","username3@gmail.com"));
//        users.add(new AdminUser("User Name 4","username4@gmail.com"));
//        users.add(new AdminUser("User Name 5","username5@gmail.com"));
//        users.add(new AdminUser("User Name 6","username6@gmail.com"));
//
//    }
}