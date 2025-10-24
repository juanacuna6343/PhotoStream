package com.example.instagramapp.data.api;

import com.example.instagramapp.data.model.MediaResponse;
import com.example.instagramapp.data.model.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit interface for Instagram Basic Display API endpoints
 * Docs: https://developers.facebook.com/docs/instagram-basic-display-api/reference
 */
public interface InstagramService {

    // Get the authenticated user's profile (me)
    @GET("me")
    Call<UserProfile> getMe(
            @Query("fields") String fields,
            @Query("access_token") String accessToken
    );

    // Get media for the authenticated user
    @GET("me/media")
    Call<MediaResponse> getMyMedia(
            @Query("fields") String fields,
            @Query("access_token") String accessToken
    );
}