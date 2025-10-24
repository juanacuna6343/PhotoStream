package com.example.instagramapp.data.model.deserializer;

import com.example.instagramapp.data.model.UserProfile;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UserProfileDeserializer implements JsonDeserializer<UserProfile> {
    @Override
    public UserProfile deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        // Inject a simulated profile picture if not present.
        if (!obj.has("profile_picture_url")) {
            obj.addProperty("profile_picture_url", "https://via.placeholder.com/128x128.png?text=IG");
        }
        return context.deserialize(obj, UserProfile.class);
    }
}