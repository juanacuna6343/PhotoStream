package com.example.instagramapp.data.repository;

import com.example.instagramapp.data.api.InstagramClient;
import com.example.instagramapp.data.api.InstagramService;
import com.example.instagramapp.data.model.MediaResponse;
import com.example.instagramapp.data.model.UserProfile;

import retrofit2.Call;

public class InstagramRepository {
    private final InstagramService service = InstagramClient.getService();

    public Call<UserProfile> getMe(String accessToken) {
        String fields = "id,username,account_type"; // profile_picture_url simulated by deserializer
        return service.getMe(fields, accessToken);
    }

    public Call<MediaResponse> getMyMedia(String accessToken) {
        String fields = "id,caption,media_type,media_url,thumbnail_url,timestamp,permalink";
        return service.getMyMedia(fields, accessToken);
    }
}