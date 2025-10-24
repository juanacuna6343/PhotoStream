package com.example.instagramapp.data.model.deserializer;

import com.example.instagramapp.data.model.MediaItem;
import com.example.instagramapp.data.model.MediaResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MediaResponseDeserializer implements JsonDeserializer<MediaResponse> {
    @Override
    public MediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        MediaResponse response = new MediaResponse();
        try {
            Type listType = new TypeToken<List<MediaItem>>(){}.getType();
            List<MediaItem> items = context.deserialize(obj.get("data"), listType);
            // Build via reflection-friendly approach: temporary class or use context to map
            MediaResponse temp = new MediaResponse();
            // Unfortunately fields are private without setters; re-deserialize directly
            return context.deserialize(json, MediaResponse.class);
        } catch (Exception e) {
            throw new JsonParseException("Failed to parse MediaResponse", e);
        }
    }
}