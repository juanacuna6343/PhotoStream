package com.example.instagramapp.ui.timeline;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagramapp.R;
import com.example.instagramapp.data.model.MediaItem;
import com.example.instagramapp.data.model.UserProfile;
import com.example.instagramapp.ui.assign.AssignUserActivity;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.List;

public class TimelineActivity extends AppCompatActivity implements TimelineContract.View {
    private static final String PREFS = "instagram_prefs";
    private static final String KEY_ACCESS_TOKEN = "access_token";

    private TimelinePresenter presenter;
    private TimelineAdapter adapter;
    private ProgressBar progressBar;
    private TextView emptyView;
    private ImageView profileImage;
    private TextView usernameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(this::onMenuItemClick);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TimelineAdapter();
        recyclerView.setAdapter(adapter);

        progressBar = findViewById(R.id.progressBar);
        emptyView = findViewById(R.id.emptyView);

        // Simple header elements for profile (we can place them in toolbar or separate views)
        profileImage = new ImageView(this);
        usernameView = new TextView(this);

        presenter = new TimelinePresenter();
        presenter.attach(this);

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        String token = prefs.getString(KEY_ACCESS_TOKEN, null);
        if (token == null) {
            startActivity(new Intent(this, AssignUserActivity.class));
        } else {
            presenter.loadTimeline(token);
        }
    }

    private boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId() == R.id.action_assign_user) {
            startActivity(new Intent(this, AssignUserActivity.class));
            return true;
        }
        return false;
    }

    @Override
    public void showLoading() { progressBar.setVisibility(View.VISIBLE); }

    @Override
    public void hideLoading() { progressBar.setVisibility(View.GONE); }

    @Override
    public void showMedia(List<MediaItem> items) {
        emptyView.setVisibility(View.GONE);
        adapter.setItems(items);
    }

    @Override
    public void showProfile(UserProfile profile) {
        usernameView.setText(profile.getUsername());
        Glide.with(this)
                .load(profile.getProfile_picture_url())
                .into(profileImage);
    }

    @Override
    public void showEmpty() { emptyView.setVisibility(View.VISIBLE); }

    @Override
    public void showError(String message) { emptyView.setText(message); emptyView.setVisibility(View.VISIBLE); }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}