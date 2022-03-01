package com.example.coe.adminactivity.useractivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coe.R;

import java.util.ArrayList;

public class UsersRecAdapter extends RecyclerView.Adapter<UsersRecAdapter.UsersViewHolder> {

    private Context context;

    private ArrayList<AdminUser> users;

    public UsersRecAdapter(Context context)
    {
        this.context = context;
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item,parent,false);
        UsersViewHolder viewHolder = new UsersViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        AdminUser user = users.get(position);
        holder.txtUserName.setText(user.getUserName());
        holder.txtUserEmail.setText(user.getUserEmail());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(ArrayList<AdminUser> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        private TextView txtUserName,txtUserEmail;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtUserEmail = itemView.findViewById(R.id.txtUserEmail);
        }
    }
}
