package com.example.batallanaval;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private int[] imageIds;
    private String[] titles;
    private String[] descriptions;

    public ImageAdapter(Context context, int[] imageIds, String[] titles, String[] descriptions) {
        this.context = context;
        this.imageIds = imageIds;
        this.titles = titles;
        this.descriptions = descriptions;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.imageView.setImageResource(imageIds[position]);
        holder.titleTextView.setText(titles[position]);
        holder.descriptionTextView.setText(descriptions[position]);
    }

    @Override
    public int getItemCount() {
        return imageIds.length;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleTextView;
        TextView descriptionTextView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}