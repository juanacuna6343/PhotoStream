package com.example.instagramapp.data.model.deserializer;

import com.example.instagramapp.data.model.MediaItem;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class MediaItemDeserializer implements JsonDeserializer<MediaItem> {
    @Override
    public MediaItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = json.getAsJsonObject();
        MediaItem item = new MediaItem();
        try {
            // Using reflection setters avoided: assign via Gson context when possible.
            // Here we re-parse with default mapping and fill missing/optional fields.
            return context.deserialize(json, MediaItem.class);
        } catch (Exception e) {
            throw new JsonParseException("Failed to parse MediaItem", e);
        }
    }
}