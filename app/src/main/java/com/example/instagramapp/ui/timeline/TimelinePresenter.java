package com.example.instagramapp.ui.timeline;

import androidx.annotation.Nullable;

import com.example.instagramapp.data.model.MediaResponse;
import com.example.instagramapp.data.model.UserProfile;
import com.example.instagramapp.data.repository.InstagramRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimelinePresenter implements TimelineContract.Presenter {
    private final InstagramRepository repository = new InstagramRepository();
    @Nullable private TimelineContract.View view;

    @Override
    public void attach(TimelineContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void loadTimeline(String accessToken) {
        if (view == null) return;
        view.showLoading();

        // Load profile
        repository.getMe(accessToken).enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (view == null) return;
                if (response.isSuccessful() && response.body() != null) {
                    view.showProfile(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                if (view != null) view.showError("Error perfil: " + t.getMessage());
            }
        });

        // Load media
        repository.getMyMedia(accessToken).enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                if (view == null) return;
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null && response.body().getData() != null && !response.body().getData().isEmpty()) {
                    view.showMedia(response.body().getData());
                } else {
                    view.showEmpty();
                }
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                if (view == null) return;
                view.hideLoading();
                view.showError("Error timeline: " + t.getMessage());
            }
        });
    }
}