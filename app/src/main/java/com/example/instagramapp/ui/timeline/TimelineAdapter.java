package com.example.instagramapp.ui.timeline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagramapp.R;
import com.example.instagramapp.data.model.MediaItem;

import java.util.ArrayList;
import java.util.List;

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.VH> {
    private final List<MediaItem> items = new ArrayList<>();

    public void setItems(List<MediaItem> newItems) {
        items.clear();
        if (newItems != null) items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_media, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        MediaItem item = items.get(position);
        holder.caption.setText(item.getCaption());
        String url = item.getMedia_url() != null ? item.getMedia_url() : item.getThumbnail_url();
        Glide.with(holder.image.getContext())
                .load(url)
                .centerCrop()
                .into(holder.image);
    }

    @Override
    public int getItemCount() { return items.size(); }

    static class VH extends RecyclerView.ViewHolder {
        ImageView image; TextView caption;
        VH(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            caption = itemView.findViewById(R.id.caption);
        }
    }
}