package com.example.instagramapp.ui.timeline;

import com.example.instagramapp.data.model.MediaItem;
import com.example.instagramapp.data.model.UserProfile;

import java.util.List;

public interface TimelineContract {
    interface View {
        void showLoading();
        void hideLoading();
        void showMedia(List<MediaItem> items);
        void showProfile(UserProfile profile);
        void showEmpty();
        void showError(String message);
    }

    interface Presenter {
        void attach(View view);
        void detach();
        void loadTimeline(String accessToken);
    }
}