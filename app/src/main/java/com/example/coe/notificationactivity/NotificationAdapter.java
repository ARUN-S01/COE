package com.example.coe.notificationactivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coe.R;
import com.google.android.material.card.MaterialCardView;

import org.w3c.dom.Text;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {


    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;

    private boolean isLoading;
    private Activity activity;
    private List<Notification> exams;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    public static String clicked_exam_name = null;
    public static String clicked_exam_date = null;
    public static String clicked_exam_eli = null;
    public static String clicked_exam_fee = null;
    public static String clicked_exam_last_date = null;


    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }

    private class ExamViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView body;

        public MaterialCardView cardNotification;


        public ExamViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.notification_txt);
            body = (TextView) view.findViewById(R.id.bodyNotification);
            cardNotification =  view.findViewById(R.id.cardNotification);
        }
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    public NotificationAdapter(RecyclerView recyclerView, List<Notification> exams, Activity activity) {

        this.exams = exams;
        this.activity = activity;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return exams.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(activity).inflate(R.layout.notification_items, parent, false);
            return new ExamViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(activity).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ExamViewHolder) {
            Notification exam = exams.get(position);
            ExamViewHolder examViewHolder = (ExamViewHolder) holder;
            examViewHolder.title.setText(exam.getTitle());
            examViewHolder.body.setText(exam.getBody());


            examViewHolder.cardNotification.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button btn = (Button) v.findViewById(R.id.btnnew);
                    btn.setVisibility(View.GONE);
                   // Toast.makeText(activity.getApplicationContext(),"clicked "+exam.getTitle(),Toast.LENGTH_SHORT).show();
                }
            });


        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return exams == null ? 0 : exams.size();
    }

    public void setLoaded() {
        isLoading = false;
    }
}
